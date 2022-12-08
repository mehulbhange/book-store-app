package com.bridgelabz.bookstore.dto;

import com.bridgelabz.bookstore.entity.Book;
import lombok.*;

import javax.persistence.ElementCollection;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {
    private String bookName;
    private String author;
    private String description;
    private Double price;
    private int quantity;
}
