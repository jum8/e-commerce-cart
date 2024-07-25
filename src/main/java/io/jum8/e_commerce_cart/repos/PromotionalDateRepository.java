package io.jum8.e_commerce_cart.repos;

import io.jum8.e_commerce_cart.domain.PromotionalDate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;


public interface PromotionalDateRepository extends JpaRepository<PromotionalDate, Long> {
    boolean existsByPromotionalDate(LocalDate promotionalDate);
}
