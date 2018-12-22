package com.tp1act1.bookstore.Service;


import com.tp1act1.bookstore.Domain.Book;
import com.tp1act1.bookstore.Domain.Cart;
import com.tp1act1.bookstore.Domain.CommandHistory;
import com.tp1act1.bookstore.Domain.Customer;
import com.tp1act1.bookstore.Repository.ICommandHistoryRepository;
import com.tp1act1.bookstore.Repository.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

@Service
@Transactional
public class CommandService {
    @Autowired
    private ICommandHistoryRepository commandHistoryRepository;
    @Autowired
    private ICustomerRepository customerRepository;

    public void saveCommand(CommandHistory command) {
        commandHistoryRepository.save(command);
    }

    public List<CommandHistory> getCommandByCustomerId(Long id) {
        return commandHistoryRepository.findCommandHistoriesByCustomer_Id(id);
    }

    private float calculerPromotion(Cart c) {
        int numberOfBooks = c.getBooks().size();
        if (numberOfBooks < 3) {
            return 1f;
        }
        if (numberOfBooks < 5)
            return 1-0.2f;
        if (numberOfBooks < 10)
            return 1-0.5f;
        else
            return 1-0.6f;

    }

    public void confirmCommand(Long customerId) {
        Customer customer = customerRepository.findById(customerId).get();
        if (customer != null) {
            if (customer.getCart().getBooks().size() > 0) {
                float promotion = calculerPromotion(customer.getCart());
                float getPrice = 0;
                for (Book b : customer.getCart().getBooks()) {
                    getPrice += b.getPrice();

                }
                float finalPrice=getPrice*promotion;
                if(finalPrice<=customer.getSolde()){
                    customer.setSolde(customer.getSolde()-finalPrice);
                    CommandHistory command=new CommandHistory(customer,customer.getCart().getBooks(),new Date(Calendar.getInstance().getTimeInMillis()),finalPrice);
                    customer.getCart().setBooks(null);
                    customerRepository.save(customer);

                    commandHistoryRepository.save(command);


                }
                //todo work on the else part, when the sold is not sufficient, throw an exception or something
            }
        }

    }
}
