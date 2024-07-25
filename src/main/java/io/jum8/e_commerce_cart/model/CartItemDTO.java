package io.jum8.e_commerce_cart.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CartItemDTO {

    private Long id;

    @NotNull
    private Long product;

    @NotNull
    @Positive
    private Integer quantity;

    @NotNull
    private Long cart;


}
