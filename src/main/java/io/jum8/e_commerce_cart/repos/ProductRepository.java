package io.jum8.e_commerce_cart.repos;

import io.jum8.e_commerce_cart.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product, Long> {
}
