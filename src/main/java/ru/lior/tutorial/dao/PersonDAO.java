package ru.lior.tutorial.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.lior.tutorial.models.Book;
import ru.lior.tutorial.models.Person;
import java.util.List;
import java.util.Optional;

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

    public List<Book> indexPersonBooks(int person_id){
        return jdbcTemplate.query("select * from book where person_id = ?", new Integer[]{person_id},
                new BeanPropertyRowMapper<>(Book.class));
    }

    public Person show(int id) {
        return jdbcTemplate.query("SELECT * FROM person where person_id = ?", new Object[]{id},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }

    public Optional<Person> show(String name) {
        return jdbcTemplate.query("SELECT * FROM person where name = ?", new Object[]{name},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }

    public void save(Person person){
        jdbcTemplate.update("INSERT INTO person (name, year) values (?,?)",
                person.getName(), person.getYear());
    }

    public void update(int id, Person updPerson){
        jdbcTemplate.update("UPDATE person set name=?, year = ?  where person_id=?",
                updPerson.getName(), updPerson.getYear(), new Object[]{id});

    }

    public void delete(int id){
        jdbcTemplate.update("DELETE from person where id=?", id);

    }


}
