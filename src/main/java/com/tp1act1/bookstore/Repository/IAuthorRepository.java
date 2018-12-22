package com.tp1act1.bookstore.Repository;

import com.tp1act1.bookstore.Domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAuthorRepository extends JpaRepository<Author,Long> {
    public Author getAuthorsByCin(Long cin);
}