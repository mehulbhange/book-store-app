package com.bridgelabz.bookstore.controller;

import com.bridgelabz.bookstore.dto.CartDTO;
import com.bridgelabz.bookstore.dto.ResponseDTO;
import com.bridgelabz.bookstore.entity.Cart;
import com.bridgelabz.bookstore.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin( allowedHeaders = "*", origins = "*")
@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    ICartService cartService;

    @PostMapping("/add-to-cart/{userId}")
    public ResponseEntity<ResponseDTO> addToCart(@PathVariable("userId") Long userId, @RequestBody CartDTO cartDTO){
        Cart cart = cartService.addToCart(userId,cartDTO);
        ResponseDTO responseDTO = new ResponseDTO("Cart Added  Successfully!!",cart);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{cartId}")
    public ResponseEntity<ResponseDTO> deleteById(@PathVariable Long cartId) {
        cartService.deleteById(cartId);
        ResponseDTO reponseDTO = new ResponseDTO("cart Data deleted successfully ", "deleted id " + cartId);
        return new ResponseEntity(reponseDTO, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete-cart/{userId}")
    public ResponseEntity<ResponseDTO> deleteAllCartItems(@PathVariable Long userId) {
        cartService.deleteAllCartItems(userId);
        ResponseDTO reponseDTO = new ResponseDTO("cart items deleted successfully ", "deleted id for user id " + userId);
        return new ResponseEntity(reponseDTO, HttpStatus.ACCEPTED);
    }

    @PutMapping("/update-quantity/{userId}/{cartId}")
    public ResponseEntity<ResponseDTO> updateBookQuantity(@PathVariable Long userId, @PathVariable Long cartId,@RequestParam int quantity) {

        String cart = cartService.updateQuantity(userId,cartId,quantity);
        ResponseDTO responseDTO = new ResponseDTO("quantity of Cart Updated successfully", cart);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);

    }

    @GetMapping("/get-all")
    public ResponseEntity<ResponseDTO> findAllDetail() {
        List<Cart> cartList = cartService.findAll();
        ResponseDTO responseDTO = new ResponseDTO("All CartList Retrieved Successfully", cartList);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/user-cart/{userId}")
    public ResponseEntity<ResponseDTO> getCartDataByUserID(@PathVariable Long userId){
        List<Cart> userCartDetails = cartService.getCartDetailsByUserId(userId);
        ResponseDTO responseDTO = new ResponseDTO("Cart Details of Given ID are Retrieved Successfully", userCartDetails);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

}
