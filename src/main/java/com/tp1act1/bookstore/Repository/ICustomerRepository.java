package com.tp1act1.bookstore.Repository;

import com.tp1act1.bookstore.Domain.Author;
import com.tp1act1.bookstore.Domain.Cart;
import com.tp1act1.bookstore.Domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ICustomerRepository extends JpaRepository<Customer,Long> {
    public Customer getCustomerByCin(Long cin);

    @Query("select c.cart from Customer c where c.id=?1")
    public Cart getCartByCustomerId(long id);

}
