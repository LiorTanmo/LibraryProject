package ru.lior.tutorial.models;

import jakarta.validation.constraints.NotEmpty;

public class Book {
    private int id;
    @NotEmpty(message = "Name can't be empty")
    private String name;

    @NotEmpty(message = "Author can't be empty")
    private String author;

    @NotEmpty(message = "Enter the year")
    private int year;

    public Book(String name, String author, int year) {
        this.name = name;
        this.author = author;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
