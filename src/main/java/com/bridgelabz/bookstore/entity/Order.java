package com.bridgelabz.bookstore.entity;

import com.bridgelabz.bookstore.dto.OrderDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;
    private LocalDate orderDate;
    private Double orderPrice;
    private int quantity;
    //private Address address;
    private String address;
    @JoinColumn(name = "user_id")
    @OneToOne(fetch = FetchType.LAZY)
    private User user;
    @JoinColumn(name = "book_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Book book;
    private Boolean cancel = false;

    public Order(User user, Book book,double orderPrice, OrderDTO orderDTO) {
        this.orderDate = LocalDate.now();
        this.quantity = orderDTO.getQuantity();
        this.address = orderDTO.getAddress();
        this.orderPrice = orderPrice;
        this.user = user;
        this.book = book;
    }

}
