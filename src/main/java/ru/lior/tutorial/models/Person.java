package ru.lior.tutorial.models;

import jakarta.validation.constraints.*;


public class Person {
    private int person_id;
    @NotEmpty(message = "Имя не может быть пустым")
    @Size(min = 2, max = 100,message = "Имя должно быть 2-100 символов")
    private String name;

    @Min(value = 1900, message = "Введите действительный год рождения")
    private int year;

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int id) {
        this.person_id = id;
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

    public Person(int person_id, String name, int year) {
        this.person_id = person_id;
        this.name = name;
        this.year = year;
    }
}
