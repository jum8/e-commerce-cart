package io.jum8.e_commerce_cart.repos;

import io.jum8.e_commerce_cart.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderRepository extends JpaRepository<Order, Long> {
}
