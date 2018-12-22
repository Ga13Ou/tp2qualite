package com.tp1act1.bookstore;

import com.tp1act1.bookstore.Domain.Author;
import com.tp1act1.bookstore.Domain.Book;
import com.tp1act1.bookstore.Domain.Customer;
import com.tp1act1.bookstore.Repository.ICustomerRepository;
import com.tp1act1.bookstore.Service.AuthorService;
import com.tp1act1.bookstore.Service.BookService;
import com.tp1act1.bookstore.Service.CommandService;
import com.tp1act1.bookstore.Service.CustomerService;
import org.h2.tools.Server;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class BookstoreApplication {

    @Autowired
    CustomerService customerService;
    @Autowired
    AuthorService  authorService;
    @Autowired
    BookService bookService;
    @Autowired
    CommandService commandService;

    public static void main(String[] args) {

        SpringApplication.run(BookstoreApplication.class, args);
    }
    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server inMemoryH2DatabaseaServer() throws SQLException {
        return Server.createTcpServer(
                "-tcp", "-tcpAllowOthers", "-tcpPort", "9090");
    }
    @Bean
    public CommandLineRunner Demo(){
        return (args -> {
            customerService.createCustomer(new Customer("Rofus","seddik",5588826L,500f));
            customerService.createCustomer(new Customer("Gastli","Oussama",5562746L,600f));
            System.out.println(customerService.findAll());
            Customer c=customerService.getCustomerByCin(5588826L);
            c.setLastName("Rofussette");
            c.setCin(4584L);
            customerService.createCustomer(c);
            authorService.createAuthor(new Author("author1","authorfirst",147855L));
            Author a=authorService.getAuthorByCin(147855L);
            Book b=new Book("girl in the train","thriller","Fr",new Date("02/12/1995"),55.21f);
            Book b2=new Book("Hello Worldd","Java","Fr",new Date("02/12/1995"),75.21f);
            bookService.createBook(b);
            bookService.createBook(b2);
            b=bookService.getById(8L);
            b2=bookService.getById(9L);
            c=customerService.getCustomerByCin(5588826L);
            authorService.addBookToAuthor(a,b);
            customerService.addBookToCart(c.getId(),b.getId());
            customerService.addBookToCart(c.getId(),b2.getId());
//            customerService.removeBookFromCart(c.getId(),b.getId());
//            commandService.confirmCommand(c.getId());
//            System.out.println(rep.findAll());
        });
    }

}
