package io.jum8.e_commerce_cart.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.OffsetDateTime;
import java.util.Set;


@Entity
@DiscriminatorValue("COMMON")
public class CommonCart extends Cart {

    @Override
    public Double calculateTotal() {
        double total = cartItems.stream().mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity()).sum();

        int totalQuantity = cartItems.stream().mapToInt(CartItem::getQuantity).sum();

        if (totalQuantity == 4) {
            total *= 0.75;
        } else if (totalQuantity > 10) {
            total -= 100;
        }
        return total;
    }
}
