package com.tp1act1.bookstore.Repository;

import com.tp1act1.bookstore.Domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICartRepository extends JpaRepository<Cart,Long> {
}
