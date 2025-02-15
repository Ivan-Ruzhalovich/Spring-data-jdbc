package com.ivan.spring_data_jdbc.repository;

import com.ivan.spring_data_jdbc.entity.Book;
import org.springframework.jdbc.core.JdbcTemplate;

public interface BookRepository  {
    void addNewBook(Book book);

    Book getBook(Long id);

    void updateInfoAboutBook(Book updateForBook);

    void deleteBook(Long id);
}
