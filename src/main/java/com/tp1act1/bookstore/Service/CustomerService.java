package com.tp1act1.bookstore.Service;

import com.tp1act1.bookstore.Domain.Book;
import com.tp1act1.bookstore.Domain.Cart;
import com.tp1act1.bookstore.Domain.Customer;
import com.tp1act1.bookstore.Repository.IBookRepository;
import com.tp1act1.bookstore.Repository.ICartRepository;
import com.tp1act1.bookstore.Repository.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerService {
    @Autowired
    ICustomerRepository customerRepository;
    @Autowired
    IBookRepository bookRepository;
    @Autowired
    ICartRepository cartRepository;

    public void createCustomer(Customer c){
        c.setId(null);
        Cart panier=new Cart();
        c.setCart(panier);
        customerRepository.save(c);
    }
    public void deleteCustomerById(Long id){
        customerRepository.deleteById(id);
    }
    public boolean updateCustomer(Customer c){
        if(customerRepository.existsById(c.getId())) {

            customerRepository.save(c);
            return true;
        }
        return false;
    }
    public List<Customer> findAll(){
        return customerRepository.findAll();
    }
    public Customer getCustomerByCin(Long cin){return customerRepository.getCustomerByCin(cin);}
    public Customer getById(Long id) {
        Optional<Customer> result = customerRepository.findById(id);
        return result.orElse(null);
    }
    public boolean addBookToCart(Long customerId,Long bookId){
        Customer customer=customerRepository.findById(customerId).get();
        Book book=bookRepository.findById(bookId).get();
        if(customer!=null && book != null){
            customer.getCart().getBooks().add(book);
            return true;
        }
        else
            return false;
    }
    public List<Book> getCartBooksByCustomerId(long id){
         Cart c=customerRepository.getCartByCustomerId(id);
         if(c != null)
             return c.getBooks();
         return null;

    }
    public void removeBookFromCart(long customerId,long bookId){
        Cart c=customerRepository.getCartByCustomerId(customerId);
        for(int i=0; i<c.getBooks().size();i++){
            if(c.getBooks().get(i).getId()==bookId){
                c.getBooks().remove(i);
                break;
            }
            cartRepository.save(c);
        }
    }
    public List<Customer> getAllCustomer(){
        return customerRepository.findAll();
    }
    public Cart getCartByCustomerId(long id){
        Customer c =customerRepository.findById(id).get();
        if(c != null){
            return c.getCart();
        }
        return null;
    }
}
