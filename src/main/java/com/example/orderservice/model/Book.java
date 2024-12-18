package com.example.orderservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * The Book entity
 */
@Data
@AllArgsConstructor
@Builder
public class Book {
    @Id
    private String id;
    private String title;
    private String author;
    private String description;
    private int price;
}
