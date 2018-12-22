package com.tp1act1.bookstore.controller;


import com.tp1act1.bookstore.Domain.Author;
import com.tp1act1.bookstore.Domain.Book;
import com.tp1act1.bookstore.Domain.Customer;
import com.tp1act1.bookstore.Service.AuthorService;
import com.tp1act1.bookstore.template.PersonTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController
{
    @Autowired
    AuthorService authorService;
    @PostMapping("/create")
    public String createCustomer(@RequestBody PersonTemplate personTemplate){
        Author newAuthor=new Author(personTemplate.getLastName(), personTemplate.getFirstName(), personTemplate.getCin());
        try {
            authorService.createAuthor(newAuthor);
        }
        catch(Exception e){
            return "CIN already exists!";
        }
        return "success";
    }

    @GetMapping
    public List<Author> getAllCustomer(){
        return  authorService.getAllAuthors();
    }

    @GetMapping("/{id}")
    public Author getCustomerById(@PathVariable long id){
        return authorService.getById(id);
    }
    @GetMapping("/books/{id}")
    public List<Book> getAuthorBooks(@PathVariable long id){
        return authorService.getBooksByAuthorId(id);
    }
}
