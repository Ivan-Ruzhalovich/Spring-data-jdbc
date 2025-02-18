package com.ivan.spring_data_jdbc.service;

import com.ivan.spring_data_jdbc.entity.Book;
import com.ivan.spring_data_jdbc.repository.BookRepositoryImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookServiceImpl implements BookService{

    BookRepositoryImpl bookRepository;

    public BookServiceImpl(BookRepositoryImpl bookRepository){
        this.bookRepository = bookRepository;
    }

    @Transactional
    @Override
    public void addNewBook(Book book) {
        bookRepository.addNewBook(book);
    }

    @Transactional
    @Override
    public Book getBook(Long id) {
        return bookRepository.getBook(id);
    }

    @Transactional
    @Override
    public void updateInfoAboutBook(Book updateForBook) {
        bookRepository.updateInfoAboutBook(updateForBook);
    }

    @Transactional
    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteBook(id);
    }
}
