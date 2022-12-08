package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.BookDTO;
import com.bridgelabz.bookstore.entity.Book;

import java.util.List;

public interface IBookService {
    Book addBook(BookDTO bookDTO);

    List<Book> findAll();

    Book FindById(Long bookId);

    String deleteById(Long bookId);

    Book updateBookData(Long bookId, BookDTO bookDTO);

    Book updateBookQuantity(Long bookId, int quantity);

    List<Book> getBooksSortedByPriceAsc();

    List<Book> getBooksSortedByPriceDesc();

    List<Book> getBooksSortedByName();

}
