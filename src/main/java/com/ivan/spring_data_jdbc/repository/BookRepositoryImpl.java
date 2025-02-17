package com.ivan.spring_data_jdbc.repository;

import com.ivan.spring_data_jdbc.entity.Book;
import com.ivan.spring_data_jdbc.mapper.BookMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

@Repository
public class BookRepositoryImpl implements BookRepository {

    private static final String insertBook = "INSERT INTO book (title,author,publicationYear) VALUES (?,?,?)";
    private static final String getBookById = "SELECT * FROM book WHERE id = ?";
    private static final String updateInfoAboutBook = "UPDATE book SET title = ?, author = ?, publicationYear = ? WHERE id = ?";
    private static final String deleteBookById = "DELETE FROM book WHERE id = ?";

    private final JdbcTemplate template;

    public BookRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.template = jdbcTemplate;
    }

    @Override
    public void addNewBook(Book book) {
        template.update(insertBook, book.getTitle(), book.getAuthor()
                , book.getPublicationYear());
    }

    @Override
    public Book getBook(Long id) {
        try {
            return template.queryForObject(getBookById, new BookMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Книна не найдена!");
        }
    }

    @Override
    public void updateInfoAboutBook(Book updateForBook) {
        int num = template.update(updateInfoAboutBook, updateForBook.getTitle(), updateForBook.getAuthor()
                , updateForBook.getPublicationYear(), updateForBook.getId());
        if (num == 0)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Книна не найдена!");
    }

    @Override
    public void deleteBook(Long id) {
        int num = template.update(deleteBookById, id);
        if (num == 0)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Книна не найдена!");
    }
}