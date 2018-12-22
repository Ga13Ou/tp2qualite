package com.tp1act1.bookstore.controller;


import com.tp1act1.bookstore.Domain.Cart;
import com.tp1act1.bookstore.Domain.Customer;
import com.tp1act1.bookstore.Service.CustomerService;
import com.tp1act1.bookstore.template.PersonTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping("/create")
    public String createCustomer(@RequestBody PersonTemplate personTemplate){
        Customer newCustomer=new Customer(personTemplate.getLastName(), personTemplate.getFirstName(), personTemplate.getCin());
        try {
            customerService.createCustomer(newCustomer);
        }
        catch(Exception e){
            return "CIN already exists!";
        }
        return "success";
    }

    @GetMapping
    public List<Customer> getAllCustomer(){
       return  customerService.getAllCustomer();
    }

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable long id){
        return customerService.getById(id);
    }
    @GetMapping("/cart/{id}")
    public Cart getCustomerCart(@PathVariable long id){
        return customerService.getCartByCustomerId(id);
    }

}
