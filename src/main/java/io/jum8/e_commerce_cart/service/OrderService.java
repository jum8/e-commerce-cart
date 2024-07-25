package io.jum8.e_commerce_cart.service;

import io.jum8.e_commerce_cart.domain.Order;
import io.jum8.e_commerce_cart.domain.OrderItem;
import io.jum8.e_commerce_cart.domain.Customer;
import io.jum8.e_commerce_cart.model.OrderDTO;
import io.jum8.e_commerce_cart.repos.OrderItemRepository;
import io.jum8.e_commerce_cart.repos.OrderRepository;
import io.jum8.e_commerce_cart.repos.CustomerRepository;
import io.jum8.e_commerce_cart.util.NotFoundException;
import io.jum8.e_commerce_cart.util.ReferencedWarning;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final OrderItemRepository orderItemRepository;

    public OrderService(final OrderRepository orderRepository, final CustomerRepository customerRepository,
            final OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.orderItemRepository = orderItemRepository;
    }

    public List<OrderDTO> findAll() {
        final List<Order> orders = orderRepository.findAll(Sort.by("id"));
        return orders.stream()
                .map(order -> mapToDTO(order, new OrderDTO()))
                .toList();
    }

    public OrderDTO get(final Long id) {
        return orderRepository.findById(id)
                .map(order -> mapToDTO(order, new OrderDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final OrderDTO orderDTO) {
        final Order order = new Order();
        mapToEntity(orderDTO, order);
        return orderRepository.save(order).getId();
    }

    public void update(final Long id, final OrderDTO orderDTO) {
        final Order order = orderRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(orderDTO, order);
        orderRepository.save(order);
    }

    public void delete(final Long id) {
        orderRepository.deleteById(id);
    }

    private OrderDTO mapToDTO(final Order order, final OrderDTO orderDTO) {
        orderDTO.setId(order.getId());
        orderDTO.setUser(order.getCustomer() == null ? null : order.getCustomer().getId());
        return orderDTO;
    }

    private Order mapToEntity(final OrderDTO orderDTO, final Order order) {
        final Customer customer = orderDTO.getUser() == null ? null : customerRepository.findById(orderDTO.getUser())
                .orElseThrow(() -> new NotFoundException("customer not found"));
        order.setCustomer(customer);
        return order;
    }

    public ReferencedWarning getReferencedWarning(final Long id) {
        final ReferencedWarning referencedWarning = new ReferencedWarning();
        final Order order = orderRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        final OrderItem orderOrderItem = orderItemRepository.findFirstByOrder(order);
        if (orderOrderItem != null) {
            referencedWarning.setKey("order.orderItem.order.referenced");
            referencedWarning.addParam(orderOrderItem.getId());
            return referencedWarning;
        }
        return null;
    }

}
