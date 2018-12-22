package com.tp1act1.bookstore.Service;

import com.tp1act1.bookstore.Domain.Author;
import com.tp1act1.bookstore.Domain.Book;
import com.tp1act1.bookstore.Repository.IAuthorRepository;
import com.tp1act1.bookstore.Repository.IBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AuthorService {
    @Autowired
    IAuthorRepository authorRepository;
    @Autowired
    IBookRepository bookRepository;

    public void createAuthor(Author a) {
        a.setId(null);
        authorRepository.save(a);
    }

    public void deleteAuthorById(Long id) {
        authorRepository.deleteById(id);
    }

    public boolean updateAuthor(Author a) {
        if (authorRepository.existsById(a.getId())) {
            authorRepository.save(a);
            return true;
        }
        return false;

    }

    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    public Author getById(Long id) {
        return authorRepository.findById(id).orElse(null);
    }

    public Author getAuthorByCin(Long cin) {
        return authorRepository.getAuthorsByCin(cin);
    }

    public void addBookToAuthor(Author a, Book b) {

        Author author = getById(a.getId());
        if (author != null) {
            if (author.getBooks() == null) {
                author.setBooks(new ArrayList<Book>());
            }
            List<Book> books = author.getBooks();
            books.add(b);
            authorRepository.save(author);
        }

    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public List<Book> getBooksByAuthorId(long id){
        Author a=getById(id);
        if(a!=null){
            return a.getBooks();
        }
        return null;
    }

}
