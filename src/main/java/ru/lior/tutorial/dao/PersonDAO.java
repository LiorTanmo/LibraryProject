package ru.lior.tutorial.dao;

import org.springframework.stereotype.Component;
import ru.lior.tutorial.models.Person;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private List<Person> people;
    private static int PEOPLE_COUNT;
    {
        people = new ArrayList<>();

        people.add(new Person(++PEOPLE_COUNT, "SkinBag1"));
        people.add(new Person(++PEOPLE_COUNT, "SkinBag2"));
        people.add(new Person(++PEOPLE_COUNT, "SkinBag3"));
        people.add(new Person(++PEOPLE_COUNT, "SkinBag4"));
        people.add(new Person(++PEOPLE_COUNT, "SkinBag5"));
    }

    public List<Person> index(){
        return people;
    }

    public void save(Person person){
        person.setId(++PEOPLE_COUNT);
        people.add(person);
    }

    public Person show(int id){
        return people.stream().filter(person ->person.getId()==id).findAny().orElse(null);
    }
}
