package io.jum8.e_commerce_cart.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;


@Entity
@DiscriminatorValue("VIP")
public class VipCart extends Cart {

    @Override
    public Double calculateTotal() {
        double total = cartItems.stream().mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity()).sum();

        int totalQuantity = cartItems.stream().mapToInt(CartItem::getQuantity).sum();

        if (totalQuantity == 4) {
            total *= 0.75;
        } else if (totalQuantity > 10) {
            double cheapestItemPrice = cartItems.stream()
                    .mapToDouble(item -> item.getProduct().getPrice()).min().orElse(0);
            total -= (500 + cheapestItemPrice);
        }
        return total;
    }
}
