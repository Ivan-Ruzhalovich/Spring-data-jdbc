package com.ivan.spring_data_jdbc.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ivan.spring_data_jdbc.entity.Book;
import com.ivan.spring_data_jdbc.service.BookServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
public class BookControllerTest {

    @MockitoBean
    private BookServiceImpl bookService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void addNewBookTest() throws Exception {
        Book book = new Book(25L,"For Test","Test Author",2025);
        String bookJSON = objectMapper.writeValueAsString(book);
        mockMvc.perform(post("/books/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookJSON))
                .andExpect(status().isOk());
        verify(bookService,times(1)).addNewBook(book);

    }
    @Test
    void getBookTest() throws Exception {
        Book book = new Book(25L,"For Test","Test Author",2025);
        when(bookService.getBook(25L)).thenReturn(book);
        mockMvc.perform(get("/books/{id}",25L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(25L))
                .andExpect(jsonPath("$.title").value("For Test"))
                .andExpect(jsonPath("$.author").value("Test Author"))
                .andExpect(jsonPath("$.publicationYear").value(2025));
        verify(bookService,times(1)).getBook(25L);
    }

    @Test
    void updateInfoAboutBookTest() throws Exception {
        Book book = new Book(25L,"For Test","Test Author",2025);
        String bookJSON = objectMapper.writeValueAsString(book);
        mockMvc.perform(put("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(bookJSON)).andExpect(status().isOk());
        verify(bookService,times(1)).updateInfoAboutBook(book);
    }

    @Test
    void deleteBookTest() throws Exception {
        mockMvc.perform(delete("/books/{id}",25L))
                .andExpect(status().isOk());
        verify(bookService,times(1)).deleteBook(25L);
    }
}
