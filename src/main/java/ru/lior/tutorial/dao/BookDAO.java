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
        return jdbcTemplate.query("select name, author, year from book", new BeanPropertyRowMapper<>(Book.class));
    }

    public Optional<Book> show(int id) {
        return jdbcTemplate.query("SELECT * FROM book where book_id = ?", new Object[]{id},
                new BeanPropertyRowMapper<>(Book.class)).stream().findAny();
    }


    public void save(Book book){
        jdbcTemplate.update("INSERT INTO book (name, year) values (?,?,?)",
                book.getName(), book.getAuthor(), book.getYear());
    }

    public void update(int id, Book updBook){
        jdbcTemplate.update("UPDATE book set name=?, author = ?, year = ?  where book_id=?",
                updBook.getName(),updBook.getAuthor(), updBook.getYear(), id);

    }

    public List<Book> indexFreeBooks(){
        return jdbcTemplate.query("select name, author, year from book where person_id = null",
                new BeanPropertyRowMapper<>(Book.class));
    }

    public void delete(int id){
        jdbcTemplate.update("DELETE from book where book_id=?", id);

    }

}
