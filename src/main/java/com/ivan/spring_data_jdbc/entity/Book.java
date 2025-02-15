package com.ivan.spring_data_jdbc.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "book")
public class Book {
    @Id
    private Long id;
    private String title;
    private String author;
    private Integer publicationYear;

}
