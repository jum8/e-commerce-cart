
package io.jum8.e_commerce_cart.service;

import io.jum8.e_commerce_cart.domain.Cart;
import io.jum8.e_commerce_cart.domain.CartItem;
import io.jum8.e_commerce_cart.domain.Product;
import io.jum8.e_commerce_cart.model.CartItemDTO;
import io.jum8.e_commerce_cart.repos.CartItemRepository;
import io.jum8.e_commerce_cart.repos.CartRepository;
import io.jum8.e_commerce_cart.repos.ProductRepository;
import io.jum8.e_commerce_cart.util.NotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CartItemService {

    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public CartItemService(final CartItemRepository cartItemRepository,
                           final CartRepository cartRepository, final ProductRepository productRepository) {
        this.cartItemRepository = cartItemRepository;
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }


    public Long create(final CartItemDTO cartItemDTO) {
        final CartItem cartItem = new CartItem();
        mapToEntity(cartItemDTO, cartItem);
        return cartItemRepository.save(cartItem).getId();
    }

    public void update(final Long id, final CartItemDTO cartItemDTO) {
        final CartItem cartItem = cartItemRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(cartItemDTO, cartItem);
        cartItemRepository.save(cartItem);
    }

    public void delete(final Long id) {
        cartItemRepository.deleteById(id);
    }

    private CartItemDTO mapToDTO(final CartItem cartItem, final CartItemDTO cartItemDTO) {
        cartItemDTO.setId(cartItem.getId());
        cartItemDTO.setQuantity(cartItem.getQuantity());
        cartItemDTO.setCart(cartItem.getCart() == null ? null : cartItem.getCart().getId());
        cartItemDTO.setProduct(cartItem.getProduct() == null ? null : cartItem.getProduct().getId());
        return cartItemDTO;
    }

    private CartItem mapToEntity(final CartItemDTO cartItemDTO, final CartItem cartItem) {
        cartItem.setQuantity(cartItemDTO.getQuantity());
        final Cart cart = cartItemDTO.getCart() == null ? null : cartRepository.findById(cartItemDTO.getCart())
                .orElseThrow(() -> new NotFoundException("cart not found"));
        cartItem.setCart(cart);
        final Product product = cartItemDTO.getProduct() == null ? null : productRepository.findById(cartItemDTO.getProduct())
                .orElseThrow(() -> new NotFoundException("product not found"));
        cartItem.setProduct(product);
        return cartItem;
    }

}
