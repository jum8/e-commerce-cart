package io.jum8.e_commerce_cart.rest;

import io.jum8.e_commerce_cart.model.CartItemDTO;
import io.jum8.e_commerce_cart.service.CartItemService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api/cartItems", produces = MediaType.APPLICATION_JSON_VALUE)
public class CartItemResource {

    private final CartItemService cartItemService;

    public CartItemResource(final CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }


    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createCartItem(@RequestBody @Valid final CartItemDTO cartItemDTO) {
        final Long createdId = cartItemService.create(cartItemDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateCartItem(@PathVariable(name = "id") final Long id,
                                               @RequestBody @Valid final CartItemDTO cartItemDTO) {
        cartItemService.update(id, cartItemDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteCartItem(@PathVariable(name = "id") final Long id) {
        cartItemService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
