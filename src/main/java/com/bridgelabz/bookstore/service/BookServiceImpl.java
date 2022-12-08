package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.BookDTO;
import com.bridgelabz.bookstore.entity.Book;
import com.bridgelabz.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements IBookService{

    @Autowired
    private BookRepository bookRepository;

    /**
     *
     * @param bookDTO
     * @return
     */
    @Override
    public Book addBook(BookDTO bookDTO) {
        Book addData = new Book(bookDTO);
        return bookRepository.save(addData);
    }

    /**
     *
     *
     * @return
     */
    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    /**
     *
     * @param bookId
     * @return
     */
    @Override
    public Book FindById(Long bookId) {
        return bookRepository.findById(bookId).orElse(null);
    }

    /**
     *
     * @param bookId
     * @return
     */
    @Override
    public String deleteById(Long bookId) {
        Book book = bookRepository.findById(bookId).orElse(null);
        if (book != null){
            bookRepository.deleteById(bookId);
            return "Book deleted : "+ bookId;
        }
        return null;
    }

    /**
     *
     * @param bookId
     * @param bookDTO
     * @return
     */
    @Override
    public Book updateBookData(Long bookId, BookDTO bookDTO) {
        Book editbook = bookRepository.findById(bookId).orElse(null);
        if (editbook != null) {
            editbook.setBookName(bookDTO.getBookName());
            editbook.setAuthor(bookDTO.getAuthor());
            editbook.setDescription(bookDTO.getDescription());
            editbook.setPrice(bookDTO.getPrice());
            editbook.setQuantity(bookDTO.getQuantity());

            return bookRepository.save(editbook);
        }
        return null;
    }

    @Override
    public Book updateBookQuantity(Long bookId, int quantity) {
        Book editbook = bookRepository.findById(bookId).orElse(null);
        if (editbook != null) {
            editbook.setQuantity(quantity);
            return bookRepository.save(editbook);
        }
        return null;
    }

    @Override
    public List<Book> getBooksSortedByPriceAsc() {
        return bookRepository.findByOrderByPriceAsc();
    }

    @Override
    public List<Book> getBooksSortedByPriceDesc() {
        return bookRepository.findByOrderByPriceDesc();
    }

    @Override
    public List<Book> getBooksSortedByName() {
        return bookRepository.findByOrderByBookNameAsc();
    }
}
