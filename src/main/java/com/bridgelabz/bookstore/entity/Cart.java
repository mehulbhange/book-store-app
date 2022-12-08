package com.bridgelabz.bookstore.entity;

import com.bridgelabz.bookstore.dto.CartDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cartId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;
    private int quantity;
    private Double totalPrice;

    public Cart(User user , Book book,double cartPrice, CartDTO cartDTO) {
        this.user = user;
        this.book = book;
        this.quantity = cartDTO.getQuantity();
        this.totalPrice = cartPrice;
    }

}
