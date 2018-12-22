package com.tp1act1.bookstore.Service;


import com.tp1act1.bookstore.Domain.Cart;
import com.tp1act1.bookstore.Domain.Customer;
import com.tp1act1.bookstore.Repository.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CartService {

    @Autowired
    ICustomerRepository customerRepository;

    private float calculerPromotion(Cart c){
        int numberOfBooks=c.getBooks().size();
        if(numberOfBooks<3){
            return 0;
        }
        if(numberOfBooks<5)
            return 0.2f;
        if(numberOfBooks<10)
            return 0.5f;
        else
            return 0.6f;

    }

    public void commander(Long customerId){
        Customer c=customerRepository.findById(customerId).get();
        if(c!=null){
            Cart cart=c.getCart();
            calculerPromotion(cart);
        }

    }
}
