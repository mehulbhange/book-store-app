package com.bridgelabz.bookstore.repository;

import com.bridgelabz.bookstore.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(value = "select * from orders where user_id = :userId", nativeQuery = true)
    List<Order> findAllByUserId(Long userId);

}
