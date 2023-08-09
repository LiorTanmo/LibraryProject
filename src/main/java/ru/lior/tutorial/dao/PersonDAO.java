package ru.lior.tutorial.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.lior.tutorial.models.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<Person> index(){
        return jdbcTemplate.query("select * from person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int id) {
        return jdbcTemplate.query("SELECT * FROM person where id = ?", new Object[]{id},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }

    public void save(Person person){
        jdbcTemplate.update("INSERT INTO person  values (1,?,?,?)",
                person.getName(), person.getAge(), person.getEmail());
    }

    public void update(int id, Person updPerson){

        jdbcTemplate.update("UPDATE person set name=?, age = ?, email = ? where id=?",
                updPerson.getName(), updPerson.getAge(), updPerson.getEmail(), new Object[]{id});

    }

    public void delete(int id){
        jdbcTemplate.update("DELETE from person where id=?", new Object[]{id});

    }




    /////////////////////////BATCH UPDATE PERFORMANCE TEST ////////////////////////////////

    public void testMultiUpdate(){
        List<Person> people = create1000people();

        long b4 = System.currentTimeMillis();

        for (Person person: people) {
            jdbcTemplate.update("INSERT INTO person  values (1,?,?,?)",
                    person.getName(), person.getAge(), person.getEmail());
        }

        long after = System.currentTimeMillis();

        System.out.println("Time: " + (after-b4));
    }

    private List<Person> create1000people(){
        List<Person> people = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            people.add(new Person(i, "MeatBag"+i, i%40 , i+"@mail.ru"));
        }
        return people;
    }

    public void testBatchUpdate(){
        List<Person> people = create1000people();

        long b4 = System.currentTimeMillis();

        jdbcTemplate.batchUpdate("INSERT INTO Person values (?,?,?,?)",
        new BatchPreparedStatementSetter(){
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setInt(1,people.get(i).getId());
                ps.setString(2,people.get(i).getName());
                ps.setInt(3,people.get(i).getAge());
                ps.setString(4,people.get(i).getEmail());
            }

            @Override
            public int getBatchSize() {
                return people.size();
            }
        });

        long after = System.currentTimeMillis();

        System.out.println("Time: " + (after-b4));
    }
}
