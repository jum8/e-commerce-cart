package io.jum8.e_commerce_cart.rest;

import io.jum8.e_commerce_cart.model.CartDTO;
import io.jum8.e_commerce_cart.service.CartService;
import io.jum8.e_commerce_cart.util.ReferencedException;
import io.jum8.e_commerce_cart.util.ReferencedWarning;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/carts", produces = MediaType.APPLICATION_JSON_VALUE)
public class CartResource {

    private final CartService cartService;

    public CartResource(final CartService cartService) {
        this.cartService = cartService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<CartDTO> getCart(@PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(cartService.get(id));
    }

    @PostMapping("/customer/{customerId}")
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createCart(@PathVariable(name = "customerId") final Long customerId) {
        final Long createdId = cartService.createCart(customerId);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateCart(@PathVariable(name = "id") final Long id,
            @RequestBody @Valid final CartDTO cartDTO) {
        cartService.update(id, cartDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteCart(@PathVariable(name = "id") final Long id) {
        cartService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
