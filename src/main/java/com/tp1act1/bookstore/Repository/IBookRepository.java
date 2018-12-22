package com.tp1act1.bookstore.Repository;

import com.tp1act1.bookstore.Domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IBookRepository extends JpaRepository<Book,Long> {
}
