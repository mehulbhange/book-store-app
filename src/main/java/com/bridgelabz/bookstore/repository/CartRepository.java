package com.bridgelabz.bookstore.repository;

import com.bridgelabz.bookstore.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    @Query(value = "select * from cart where user_id=:id", nativeQuery = true)
    List<Cart> getCartListByUserId(Long id);
}
