package io.jum8.e_commerce_cart.repos;

import io.jum8.e_commerce_cart.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
