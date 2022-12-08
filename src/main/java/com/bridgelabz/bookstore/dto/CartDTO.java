package com.bridgelabz.bookstore.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO {
    private Long bookId;
    private int quantity;
}
