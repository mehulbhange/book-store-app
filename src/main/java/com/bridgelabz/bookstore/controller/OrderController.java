package com.bridgelabz.bookstore.controller;

import com.bridgelabz.bookstore.dto.OrderDTO;
import com.bridgelabz.bookstore.dto.ResponseDTO;
import com.bridgelabz.bookstore.entity.Order;
import com.bridgelabz.bookstore.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin( allowedHeaders = "*", origins = "*")
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    IOrderService orderService;

    @PostMapping("/place-order/{userId}")
    public ResponseEntity<ResponseDTO> placeOrder(@PathVariable("userId") Long userId,@RequestBody String address) {
        List<Order> orders = orderService.placeOrder(userId,address);
        ResponseDTO response= new ResponseDTO("Your order placed successfully ",  orders);
        return new ResponseEntity<> (response, HttpStatus.OK);
    }

    @PutMapping("/cancel-order/{orderId}/{userId}")
    public ResponseEntity<ResponseDTO> cancelOrder(@PathVariable("orderId") Long orderId,@PathVariable("userId") Long userId) {
        String order = orderService.cancelOrder(orderId,userId);
        ResponseDTO response= new ResponseDTO("Order cancelled successfully ",  order);
        return new ResponseEntity<> (response,HttpStatus.OK);
    }

    @GetMapping("/user-orders/{userId}")
    public ResponseEntity<ResponseDTO> getUserOrders(@PathVariable("userId") Long userId){
        List<Order> userOrders = orderService.getOrdersByUser(userId);
        ResponseDTO response = new ResponseDTO("Orders of user ", userOrders);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/get-all")
    public ResponseEntity<ResponseDTO> getAllOrders(){
        ResponseDTO responseDTO = new ResponseDTO("Getting all orders", orderService.getAllOrders());
        return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.OK);
    }

}
