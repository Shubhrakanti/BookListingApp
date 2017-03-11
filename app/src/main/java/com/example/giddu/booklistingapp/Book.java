package com.example.giddu.booklistingapp;

/**
 * Created by giddu on 3/10/17.
 */

public class Book {

    private String title;
    private String[] authors;

    public Book(String title, String[] authors) {
        this.title = title;
        this.authors = authors;
    }

    public String getTitle() {
        return title;
    }

    public String[] getAuthors() {
        return authors;
    }

}
