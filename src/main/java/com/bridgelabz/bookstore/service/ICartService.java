package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.CartDTO;
import com.bridgelabz.bookstore.entity.Cart;

import java.util.List;

public interface ICartService {

    Cart addToCart(Long userId, CartDTO cartDTO);

    String deleteById(Long cartid);

    String updateQuantity(Long userId, Long cartId, int cartQuantity);

    List<Cart> findAll();

    List<Cart> getCartDetailsByUserId(Long userId);

    String deleteAllCartItems(Long userId);
}
