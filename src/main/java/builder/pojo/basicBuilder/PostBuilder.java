package builder.pojo.basicBuilder;

import builder.pojo.noBuilder.Posts;

// flexible paramteres, readability at Test layer

public class PostBuilder {
    private int id;
    private String title;
    private String author;
    private String platform;

    private PostBuilder(){
    }

    public static PostBuilder getPostBuilder(){
        return new PostBuilder();
    }


    public PostBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public PostBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public PostBuilder setAuthor(String author) {
        this.author = author;
        return this;
    }

    public PostBuilder setPlatform(String platform) {
        this.platform = platform;
        return this;
    }

    public Posts build() {
        return new Posts(id, title, author, platform);
    }
}
