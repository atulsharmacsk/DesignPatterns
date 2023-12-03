package tests;

import builder.pojo.basicBuilder.PostBuilder;
import builder.pojo.innerClass.Posts_;
import builder.pojo.lombok.Posts__;
import builder.pojo.noBuilder.Posts;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.util.List;

public class TestBuilder {

    private static RequestSpecification requestSpecification;
    private static Response response;

    static {
        requestSpecification = RestAssured.given()
                .baseUri("http://localhost:3000")
                .contentType(ContentType.JSON);
    }

    @Test
    public void createNewPost() {
        Posts post = new Posts(3, "title3", "author3", "platform3");
        requestSpecification.body(post);
        response = requestSpecification.post("/posts");

        Assertions.assertThat(response.statusCode())
                .isEqualTo(201);
    }

    @Test
    public void createNewPostUsingBaseBuilder() {
        Posts post = PostBuilder.getPostBuilder()
                .setAuthor("test"+getRandomString())
                .setTitle("title"+getRandomString())
                .build();

        requestSpecification.body(post);
        response = requestSpecification.post("/posts");

        Assertions.assertThat(response.statusCode())
                .isEqualTo(201);
    }

    @Test
    public void createNewPostUsingInnerBuilder() {
        Posts_ post = Posts_.getBuilder()
                .setAuthor("test"+getRandomString())
                .setTitle("title"+getRandomString())
                .build();

        requestSpecification.body(post);
        response = requestSpecification.post("/posts");

        Assertions.assertThat(response.statusCode())
                .isEqualTo(201);
    }

    @Test
    public void createNewPostUsingLombokBuilder() {
        Posts__ post = Posts__.builder()
                .author("test"+getRandomString())
                .title("title"+getRandomString())
                .build();

        requestSpecification.body(post);
        response = requestSpecification.post("/posts");

        Assertions.assertThat(response.statusCode())
                .isEqualTo(201);
    }

    @AfterTest
    public void getPosts(){
        response = requestSpecification.get("/posts");
        System.out.println(response.asString());

        List<Integer> ids = response.jsonPath().getList("id");
        requestSpecification.delete("/posts/"+ids.get(ids.size()-1));
    }

    public static String getRandomString(){
        return RandomStringUtils.random(5);
    }
}
