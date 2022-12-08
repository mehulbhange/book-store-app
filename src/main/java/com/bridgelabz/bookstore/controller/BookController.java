package com.bridgelabz.bookstore.controller;

import com.bridgelabz.bookstore.dto.BookDTO;
import com.bridgelabz.bookstore.dto.ResponseDTO;
import com.bridgelabz.bookstore.entity.Book;
import com.bridgelabz.bookstore.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin( allowedHeaders = "*", origins = "*")
@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private IBookService bookService;

    @PostMapping("/book")
    public ResponseEntity<ResponseDTO> addBook(@RequestBody BookDTO bookDTO) {
        Book book = bookService.addBook(bookDTO);
        ResponseDTO responseDTO = new ResponseDTO("Add Book Success", book);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    //  Ability to get all book data by findAll() method
    @GetMapping("/books")
    public ResponseEntity<ResponseDTO> getAllBooks() {
        List<Book> userList = bookService.findAll();
        ResponseDTO responseDTO = new ResponseDTO("All books List", userList);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    //Ability to get book data by id
    @GetMapping("/book/{bookId}")
    public ResponseEntity<ResponseDTO> getBookById(@PathVariable Long bookId) {
        Book response = bookService.FindById(bookId);
        ResponseDTO responseDto = new ResponseDTO("Get book by id ", response);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
    // Ability to delete book data for particular id
    @DeleteMapping("/delete/{bookId}")
    public ResponseEntity<ResponseDTO> deleteBookById(@PathVariable Long bookId) {
        bookService.deleteById(bookId);
        ResponseDTO reponseDTO = new ResponseDTO("book Data deleted successfully ", "deleted id " + bookId);
        return new ResponseEntity(reponseDTO, HttpStatus.ACCEPTED);
    }
    //Ability to update book data for particular id
    @PutMapping("/update/{bookId}")
    public ResponseEntity<ResponseDTO> updateBook(@PathVariable Long bookId, @RequestBody BookDTO bookDTO) {
        Book bookData = bookService.updateBookData(bookId, bookDTO);
        ResponseDTO responseDTO = new ResponseDTO("Updated Book Details Successfully", bookData);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);

    }

    @PutMapping("/update-quantity")
    public ResponseEntity<ResponseDTO> updateBookQuantity(@RequestParam Long bookId, @RequestParam int quantity){
        Book bookData = bookService.updateBookQuantity(bookId, quantity);
        ResponseDTO responseDTO = new ResponseDTO("Updated Book quantity Successfully", bookData);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/get-books-sorted-by-price-asc")
    public ResponseEntity<ResponseDTO> getBooksSortedByPriceAsc(){
        List<Book> books = bookService.getBooksSortedByPriceAsc();
        ResponseDTO responseDTO = new ResponseDTO("Fetched books sorted by price in ascending order", books);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/get-books-sorted-by-price-desc")
    public ResponseEntity<ResponseDTO> getBooksSortedByPriceDesc(){
        List<Book> books = bookService.getBooksSortedByPriceDesc();
        ResponseDTO responseDTO = new ResponseDTO("Fetched books sorted by price in descending order", books);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/get-books-sorted-by-name")
    public ResponseEntity<ResponseDTO> getBooksSortedByName(){
        List<Book> books = bookService.getBooksSortedByName();
        ResponseDTO responseDTO = new ResponseDTO("Fetched books sorted by name", books);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

}
