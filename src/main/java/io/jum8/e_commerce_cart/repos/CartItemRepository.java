package io.jum8.e_commerce_cart.repos;

import io.jum8.e_commerce_cart.domain.Cart;
import io.jum8.e_commerce_cart.domain.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CartItemRepository extends JpaRepository<CartItem, Long> {

}
