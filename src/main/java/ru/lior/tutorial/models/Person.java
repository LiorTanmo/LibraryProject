package ru.lior.tutorial.models;

import jakarta.validation.constraints.*;

public class Person {
    private int id;
    @NotEmpty(message = "Name can't be empty")
    @Pattern(regexp = "[А-Я]\\w+ \\s [А-Я]\\w+ \\s [А-Я]\\w+", message = "Enter proper name")
    private String name;

    @Min(value = 1900, message = "Enter a valid year")
    @NotEmpty(message = "Year of birth can't be empty")
    private int year;

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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Person() {
    }

    public Person(int id, String name, int year) {
        this.id = id;
        this.name = name;
        this.year = year;
    }
}
