package com.example.giddu.booklistingapp;

/**
 * Created by giddu on 3/10/17.
 */

public class Book {

    private String title;
    private String[] authors;
    private String publishDate;
    private String subtitle;

    public Book(String title, String[] authors, String publishDate, String subtitle) {
        this.title = title;
        this.authors = authors;
        this.publishDate = publishDate;
        this.subtitle = subtitle;
    }

    public String getTitle() {
        return title;
    }

    public String[] getAuthors() {
        return authors;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public String getSubtitle() {
        return subtitle;
    }
}
