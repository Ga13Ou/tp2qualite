package com.tp1act1.bookstore.Domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.*;
import java.util.List;


@Entity
public class Customer extends Person {
    @JsonIgnore
    @OneToOne(cascade = CascadeType.PERSIST, optional = false)
    private Cart cart;
    private float solde;

    public float getSolde() {
        return solde;
    }

    public void setSolde(float solde) {
        if (solde >= 0)
            this.solde = solde;
    }

    public Customer(String lastName, String firstName, Long cin, float solde) {
        super(lastName, firstName, cin);
        this.solde = solde;
    }

    public Customer(String lastName, String firstName, Long cin) {
        super(lastName, firstName, cin);
        this.solde = 0f;
    }

    public Customer() {
        super();
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
