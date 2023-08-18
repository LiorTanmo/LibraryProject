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
public class BookDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index(){
        return jdbcTemplate.query("select * from book", new BeanPropertyRowMapper<>(Book.class));
    }

    public Book show(int id) {
        return jdbcTemplate.query("SELECT * FROM book where book_id = ?", new Object[]{id},
                new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
    }


    public void save(Book book){
        jdbcTemplate.update("INSERT INTO book (name, author, year) values (?,?,?)",
                book.getName(), book.getAuthor(), book.getYear());
    }

    public void update(int id, Book updBook){
        jdbcTemplate.update("UPDATE book set name=?, author = ?, year = ?  where book_id=?",
                updBook.getName(), updBook.getAuthor(), updBook.getYear(), id);

    }

    public  Optional<Person> getOwner(int book_id){
       return jdbcTemplate.query("Select p.* from book join public.person p on p.person_id = book.person_id " +
                       "where book_id = ?", new Integer[]{book_id},
               new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }

    public void assignBook(Integer person_id, int book_id){
        jdbcTemplate.update("UPDATE book set person_id=?  where book_id=?", person_id, book_id);
    }

    public void delete(int id){
        jdbcTemplate.update("DELETE from book where book_id=?", id);

    }

}
