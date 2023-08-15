package ru.lior.tutorial.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
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

    public Optional<Person> show(String email){
        return jdbcTemplate.query("Select * from Person where email=?",new Object[]{email},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }
    public Person show(int id) {
        return jdbcTemplate.query("SELECT * FROM person where id = ?", new Object[]{id},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }

    public void save(Person person){
        jdbcTemplate.update("INSERT INTO person (name, age, email, address) values (?,?,?,?)",
                person.getName(), person.getAge(), person.getEmail(), person.getAddress());
    }

    public void update(int id, Person updPerson){

        jdbcTemplate.update("UPDATE person set name=?, age = ?, email = ?, adress = ? where id=?",
                updPerson.getName(), updPerson.getAge(), updPerson.getEmail(), updPerson.getAddress(), new Object[]{id});

    }

    public void delete(int id){
        jdbcTemplate.update("DELETE from person where id=?", id);

    }


    /////////////////////////BATCH UPDATE PERFORMANCE TEST ////////////////////////////////

   /* public void testMultiUpdate(){
        List<Person> people = create1000people();

        long b4 = System.currentTimeMillis();

        for (Person person: people) {
            jdbcTemplate.update("INSERT INTO person  values (?,?,?)",
                    person.getName(), person.getAge(), person.getEmail());
        }

        long after = System.currentTimeMillis();

        System.out.println("Time: " + (after-b4));
    }

    private List<Person> create1000people(){
        List<Person> people = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            people.add(new Person( "MeatBag"+i, i%40 , i+"@mail.ru", address));
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
    }*/
}
