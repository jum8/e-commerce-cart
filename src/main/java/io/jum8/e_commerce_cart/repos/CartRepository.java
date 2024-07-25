package io.jum8.e_commerce_cart.repos;

import io.jum8.e_commerce_cart.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CartRepository extends JpaRepository<Cart, Long> {
}
