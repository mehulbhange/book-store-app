package com.bridgelabz.bookstore.repository;

import com.bridgelabz.bookstore.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByOrderByBookNameAsc();

    List<Book> findByOrderByPriceAsc();
    List<Book> findByOrderByPriceDesc();


}
