package io.jum8.e_commerce_cart.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.jum8.e_commerce_cart.domain.CartItem;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;


@Getter
@Setter
public class CartDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    private Long customer;
    private Set<@Valid CartItemDTO> items;
}
