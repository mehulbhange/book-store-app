package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.CartDTO;
import com.bridgelabz.bookstore.entity.Book;
import com.bridgelabz.bookstore.entity.Cart;
import com.bridgelabz.bookstore.entity.User;
import com.bridgelabz.bookstore.repository.BookRepository;
import com.bridgelabz.bookstore.repository.CartRepository;
import com.bridgelabz.bookstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CartServiceImpl implements ICartService{

    @Autowired
    CartRepository cartRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BookRepository bookRepository;

    @Override
    public Cart addToCart(Long userId, CartDTO cartDTO) {

        User user = userRepository.findById(userId).orElse(null);
        Book book = bookRepository.findById(cartDTO.getBookId()).orElse(null);
        if(user != null && book != null){
            double cartPrice = book.getPrice() * cartDTO.getQuantity();
            Cart cart = new Cart(user,book,cartPrice,cartDTO);
            return cartRepository.save(cart);
        }
        return null;
    }

    @Override
    public String deleteById(Long cartId) {

        Cart cart = cartRepository.findById(cartId).orElse(null);
        if(cart != null) {
            cartRepository.deleteById(cartId);
            return "Cart Removed";
        }
        return null;
    }

    @Override
    public String updateQuantity(Long userId, Long cartId, int cartQuantity) {
        User user = userRepository.findById(userId).orElse(null);
        Cart cart = cartRepository.findById(cartId).orElse(null);

        if(cart != null && user != null){
            Book book = bookRepository.findById(cart.getBook().getBookId()).orElse(null);
            if(book != null){
                cart.setQuantity(cartQuantity);
                cart.setTotalPrice(book.getPrice() * cartQuantity);
                cartRepository.save(cart);
                return "Updated with quantity : "+ cartQuantity;
            }
        }
        return null;
    }

    @Override
    public List<Cart> findAll() {
        return cartRepository.findAll();
    }

    @Override
    public List<Cart> getCartDetailsByUserId(Long userId) {
        List<Cart> userCartList = cartRepository.getCartListByUserId(userId);
        if(userCartList.isEmpty()){
            return null;
        }else
            return userCartList;
    }

    @Override
    public String deleteAllCartItems(Long userId) {
        List<Cart> cartItems = cartRepository.getCartListByUserId(userId);
        cartRepository.deleteAll(cartItems);
        return "Cart items removed";
    }
}
