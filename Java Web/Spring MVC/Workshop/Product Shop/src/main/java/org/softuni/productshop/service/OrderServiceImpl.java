package org.softuni.productshop.service;

import org.modelmapper.ModelMapper;
import org.softuni.productshop.common.CustomException;
import org.softuni.productshop.domain.entity.Order;
import org.softuni.productshop.domain.entity.Product;
import org.softuni.productshop.domain.entity.User;
import org.softuni.productshop.domain.model.binding.OrderProductBindingModel;
import org.softuni.productshop.domain.model.service.ProductServiceModel;
import org.softuni.productshop.domain.model.service.UserServiceModel;
import org.softuni.productshop.domain.model.view.order.OrderDetailsViewModel;
import org.softuni.productshop.domain.model.view.order.OrderViewModel;
import org.softuni.productshop.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

import static org.softuni.productshop.common.ExceptionsMessages.ORDER_NOT_FOUND;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ProductService productService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, ProductService productService, UserService userService, ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.productService = productService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }


    @Override
    public void create(String productId, OrderProductBindingModel orderProduct, String customerName) throws CustomException {
        ProductServiceModel productServiceModel = this.productService.findById(productId);
        UserServiceModel userServiceModel = this.userService.findByUsername(customerName);

        Product product = this.modelMapper.map(productServiceModel, Product.class);
        User customer = this.modelMapper.map(userServiceModel, User.class);

        Integer quantity = orderProduct.getQuantity();
        Order order = setOrderDetails(quantity, product, customer);
        this.orderRepository.save(order);

    }

    @Override
    public Set<OrderViewModel> findAll() {
        Set<OrderViewModel> allOrders = this.orderRepository.findAll()
                .stream()
                .map(order -> this.modelMapper.map(order, OrderViewModel.class))
                .collect(Collectors.toSet());
        return allOrders;
    }

    @Override
    public Set<OrderViewModel> findAllOrdersByUser(String name) {
        UserServiceModel byUsername = this.userService.findByUsername(name);
        User customer = this.modelMapper.map(byUsername, User.class);
        Set<OrderViewModel> allByCustomer = this.orderRepository.findAllByCustomer(customer)
                .stream()
                .map(order -> this.modelMapper.map(order, OrderViewModel.class))
                .collect(Collectors.toSet());
        return allByCustomer;
    }

    @Override
    public OrderDetailsViewModel findOrderById(String id) throws CustomException {
        Order order = this.orderRepository.findById(id).orElseThrow(() -> new CustomException(ORDER_NOT_FOUND));
        return this.modelMapper.map(order, OrderDetailsViewModel.class);
    }

    private Order setOrderDetails(Integer quantity, Product product, User customer) {
        Order order = new Order();

        order.setCustomer(customer);
        order.setProduct(product);
        order.setQuantity(quantity);
        BigDecimal totalPrice = product.getPrice().multiply(BigDecimal.valueOf(order.getQuantity()));
        order.setTotalPrice(totalPrice);
        order.setOrderDate(LocalDateTime.now());
        return order;
    }
}
