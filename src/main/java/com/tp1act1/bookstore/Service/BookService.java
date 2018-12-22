package com.tp1act1.bookstore.Service;

import com.tp1act1.bookstore.Domain.Book;
import com.tp1act1.bookstore.Repository.IBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    IBookRepository bookRepository;

    public void createBook(Book b) {
        b.setId(null);
        bookRepository.save(b);
    }

    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }

    public boolean updateBook(Book b) {
        if (bookRepository.existsById(b.getId())) {

            bookRepository.save(b);
            return true;
        }
        return false;
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book getById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

}
