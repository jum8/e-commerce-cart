package io.jum8.e_commerce_cart.service;

import io.jum8.e_commerce_cart.domain.*;
import io.jum8.e_commerce_cart.model.CartDTO;
import io.jum8.e_commerce_cart.model.CartItemDTO;
import io.jum8.e_commerce_cart.repos.*;
import io.jum8.e_commerce_cart.util.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class CartService {

    private final CartRepository cartRepository;
    private final CustomerRepository customerRepository;
    private final CartItemRepository cartItemRepository;

    private final PromotionalDateRepository promotionalDateRepository;
    private final ProductRepository productRepository;

    public CartService(final CartRepository cartRepository, final CustomerRepository customerRepository,
                       final CartItemRepository cartItemRepository, PromotionalDateRepository promotionalDateRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.customerRepository = customerRepository;
        this.cartItemRepository = cartItemRepository;
        this.promotionalDateRepository = promotionalDateRepository;
        this.productRepository = productRepository;
    }



    public CartDTO get(final Long id) {
        return cartRepository.findById(id)
                .map(cart -> mapToDTO(cart, new CartDTO()))
                .orElseThrow(NotFoundException::new);
    }


    public Long createCart(Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(NotFoundException::new);
        Cart cart;

        if(promotionalDateRepository.existsByPromotionalDate(LocalDate.now())) {
            cart = new SpecialDateCart();
        } else if (customer.isVip()) {
            cart = new VipCart();
        } else {
            cart = new CommonCart();
        }

        cart.setCustomer(customer);
        cart.setExpirationDate(OffsetDateTime.now().plusDays(0));

        return cartRepository.save(cart).getId();
    }

    @Transactional
    public void update(final Long id, final CartDTO cartDTO) {
        final Cart cart = cartRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(cartDTO, cart);
        cartRepository.save(cart);
    }

    public void delete(final Long id) {
        cartRepository.deleteById(id);
    }


    private CartDTO mapToDTO(final Cart cart, final CartDTO cartDTO) {
        cartDTO.setId(cart.getId());
        cartDTO.setCustomer(cart.getCustomer() == null ? null : cart.getCustomer().getId());
        final Set<CartItemDTO> items = cart.getCartItems().stream()
                .map(item -> mapCartItemToDTO(item, new CartItemDTO()))
                .collect(Collectors.toSet());
        cartDTO.setItems(items);
        return cartDTO;
    }

    private Cart mapToEntity(final CartDTO cartDTO, final Cart cart) {
        final Customer customer = cartDTO.getCustomer() == null ? null : customerRepository.findById(cartDTO.getCustomer())
                .orElseThrow(() -> new NotFoundException("customer not found"));
        cart.setCustomer(customer);
        final Set<CartItem> items = cartDTO.getItems().stream()
                .map(itemDTO -> mapCartItemDTOToEntity(itemDTO, new CartItem()))
                .collect(Collectors.toSet());
        cart.getCartItems().clear();
        cart.getCartItems().addAll(items);
        items.forEach(item -> item.setCart(cart));
        return cart;
    }

    private CartItem mapCartItemDTOToEntity(final CartItemDTO itemDTO, final CartItem item) {
        Product product = itemDTO.getProduct() == null ? null : productRepository.findById(itemDTO.getProduct())
                .orElseThrow(() -> new NotFoundException("product not found"));
        item.setProduct(product);
        item.setQuantity(itemDTO.getQuantity());
        return item;
    }

    private CartItemDTO mapCartItemToDTO(final CartItem cartItem, final CartItemDTO cartItemDTO) {
        cartItemDTO.setId(cartItem.getId());
        cartItemDTO.setQuantity(cartItem.getQuantity());
        cartItemDTO.setCart(cartItem.getCart() == null ? null : cartItem.getCart().getId());
        cartItemDTO.setProduct(cartItem.getProduct() == null ? null : cartItem.getProduct().getId());
        return cartItemDTO;
    }


}
