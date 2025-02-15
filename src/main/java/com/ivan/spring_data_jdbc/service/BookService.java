package com.ivan.spring_data_jdbc.service;

import com.ivan.spring_data_jdbc.entity.Book;

public interface BookService {
    void addNewBook(Book book);

    Book getBook(Long id);

    void updateInfoAboutBook(Book updateForBook);

    void deleteBook(Long id);
}
