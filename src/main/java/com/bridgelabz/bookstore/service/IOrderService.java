package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.OrderDTO;
import com.bridgelabz.bookstore.entity.Order;

import java.util.List;

public interface IOrderService {
    //Order placeOrder(Long userId, OrderDTO orderDTO);

    List<Order> placeOrder(Long userId,String address);
    String cancelOrder(Long orderId, Long userId);

    List<Order> getOrdersByUser(Long userId);

    List<Order> getAllOrders();
}
