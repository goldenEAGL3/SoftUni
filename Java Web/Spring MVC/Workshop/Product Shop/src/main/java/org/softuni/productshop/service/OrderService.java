package org.softuni.productshop.service;

import org.softuni.productshop.common.CustomException;
import org.softuni.productshop.domain.model.binding.OrderProductBindingModel;
import org.softuni.productshop.domain.model.view.order.OrderDetailsViewModel;
import org.softuni.productshop.domain.model.view.order.OrderViewModel;

import java.util.Set;

public interface OrderService {
    void create(String productId, OrderProductBindingModel order, String name) throws CustomException;

    Set<OrderViewModel> findAll();

    Set<OrderViewModel> findAllOrdersByUser(String name);

    OrderDetailsViewModel findOrderById(String id) throws CustomException;

}
