package com.pojo.innerClass;

public class Posts_ {
    private int id;
    private String title;
    private String author;
    private String platform;

    private Posts_(int id, String title, String author, String platform) {
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

    public static PostBuilder getBuilder(){
        return new PostBuilder();
    }

    public static class PostBuilder{
        private int id;
        private String title;
        private String author;
        private String platform;

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

        public Posts_ build(){
            return new Posts_(id,title,author,platform);
        }
    }
}
