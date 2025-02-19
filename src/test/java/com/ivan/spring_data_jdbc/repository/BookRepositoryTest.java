package com.ivan.spring_data_jdbc.repository;

import com.ivan.spring_data_jdbc.entity.Book;
import org.apache.catalina.core.ApplicationContext;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@JdbcTest
@SpringJUnitConfig(BookRepositoryImpl.class)
public class BookRepositoryTest {

    @Autowired
    private JdbcTemplate template;

    @Autowired
    BookRepositoryImpl repository;

    @Test
    @Sql(scripts = "/create-Table.sql")
    @Sql(scripts = "/test-data.sql")
    void getBookTest() {
        Book book = repository.getBook(1L);
        assertThat(book).isNotNull();
        assertThat(book.getTitle()).isEqualTo("Test Title User");
        assertThat(book.getId()).isEqualTo(1);
        assertThat(book.getPublicationYear()).isEqualTo(2025);
        assertThat(book.getAuthor()).isEqualTo("Test Author");

    }

    @Test
    @Sql(scripts = "/create-Table.sql")
    void addNewBookTest() {
        Book newBook = new Book("Two Test Title", "Two Test Author", 2025);
        repository.addNewBook(newBook);
        Book testBook = repository.getBook(1L);
        assertThat(testBook).isNotNull();
        assertThat(testBook.getTitle()).isEqualTo("Two Test Title");
        assertThat(testBook.getId()).isEqualTo(1);
        assertThat(testBook.getPublicationYear()).isEqualTo(2025);
        assertThat(testBook.getAuthor()).isEqualTo("Two Test Author");
    }

    @Test
    @Sql(scripts = "/create-Table.sql")
    void updateInfoAboutBookTest() {
        Book newBook = new Book("Two Test Title", "Two Test Author", 2025);
        repository.addNewBook(newBook);
        Book testBook = repository.getBook(1L);
        assertThat(testBook).isNotNull();
        assertThat(testBook.getTitle()).isEqualTo("Two Test Title");
        assertThat(testBook.getId()).isEqualTo(1);
        assertThat(testBook.getPublicationYear()).isEqualTo(2025);
        assertThat(testBook.getAuthor()).isEqualTo("Two Test Author");
        testBook.setAuthor("Test Update Author");
        repository.updateInfoAboutBook(testBook);
        testBook = repository.getBook(1L);
        assertThat(testBook).isNotNull();
        assertThat(testBook.getTitle()).isEqualTo("Two Test Title");
        assertThat(testBook.getId()).isEqualTo(1);
        assertThat(testBook.getAuthor()).isEqualTo("Test Update Author");
    }
}
