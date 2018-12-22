package com.tp1act1.bookstore.Domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
public class Author extends Person {
    @JsonIgnore
    @ManyToMany
    private List<Book> books;

    public Author(String lastName, String firstName, Long cin) {
        super(lastName, firstName, cin);
    }
    public Author(){
        super();
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
