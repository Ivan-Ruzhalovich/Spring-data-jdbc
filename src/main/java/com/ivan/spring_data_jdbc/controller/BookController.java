package com.ivan.spring_data_jdbc.controller;

import com.ivan.spring_data_jdbc.entity.Book;
import com.ivan.spring_data_jdbc.service.BookServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookController {

    BookServiceImpl service;

    public BookController(BookServiceImpl bookService){
        this.service = bookService;
    }

    @PostMapping("/books/new")
    public ResponseEntity<String> addNewBook(@RequestBody Book book){
        service.addNewBook(book);
        return new ResponseEntity<>("Книга добавлена!", HttpStatus.OK);
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBook(@PathVariable Long id){
        return new ResponseEntity<>(service.getBook(id),HttpStatus.OK);
    }

    @PutMapping("/books")
    public ResponseEntity<String> updateInfoAboutBook(@RequestBody Book updateForBook){
        service.updateInfoAboutBook(updateForBook);
        return new ResponseEntity<>("Данные о книге обновлены!",HttpStatus.OK);
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id){
        service.deleteBook(id);
        return new ResponseEntity<>("Книга удалена!",HttpStatus.OK);
    }

//    создание, чтение, обновление, удаление
}
