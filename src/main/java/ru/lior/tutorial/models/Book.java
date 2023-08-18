package ru.lior.tutorial.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Book {
    private int book_id;
    @NotEmpty(message = "Имя не может быть пустым")
    @Size(min = 2,max = 100,message = "Название должно быть 2-100 символов")
    private String name;

    @NotEmpty(message = "Автор не может быть пустым")
    @Size(min = 2,max = 100,message = "Имя автора должно быть 2-100 символов")
    private String author;

    @Min(value = 0, message = "Год должен быть положительным")
    private int year;

    public Book (String name, String author, int year) {
        this.name = name;
        this.author = author;
        this.year = year;
    }

    public Book(){}

    public int getBook_id() {
        return book_id;
    }



    public void setBook_id(int book_id) {
        this.book_id = book_id;
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
