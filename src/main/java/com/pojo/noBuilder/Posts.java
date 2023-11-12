package com.pojo.noBuilder;

public class Posts {
    private int id;
    private String title;
    private String author;
    private String platform;

    //MORE NO OF PARAMS MORE CONSTRCUTOR TO MAKE PARAMS OPTIONAL
    public Posts(int id, String title, String author, String platform) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.platform = platform;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getPlatform() {
        return platform;
    }
}
