package com.ivan.spring_data_jdbc.mapper;

import com.ivan.spring_data_jdbc.entity.Book;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Book(rs.getLong("id")
                ,rs.getString("title")
                ,rs.getString("author"),
                rs.getInt("publicationYear"));
    }
}