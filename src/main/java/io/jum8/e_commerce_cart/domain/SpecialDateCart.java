package io.jum8.e_commerce_cart.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;


@Entity
@DiscriminatorValue("SPECIAL_DATE")
public class SpecialDateCart extends Cart {

    @Override
    public Double calculateTotal() {
        double total = cartItems.stream().mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity()).sum();

        int totalQuantity = cartItems.stream().mapToInt(CartItem::getQuantity).sum();

        if (totalQuantity == 4) {
            total *= 0.75;
        } else if (totalQuantity > 10) {
            total -= 300;
        }
        return total;
    }
}
