package io.jum8.e_commerce_cart.repos;

import io.jum8.e_commerce_cart.domain.Order;
import io.jum8.e_commerce_cart.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    OrderItem findFirstByOrder(Order order);

}
