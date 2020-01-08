package org.softuni.productshop.web.controller;

import org.modelmapper.ModelMapper;
import org.softuni.productshop.common.CustomException;
import org.softuni.productshop.domain.model.binding.OrderProductBindingModel;
import org.softuni.productshop.domain.model.service.ProductServiceModel;
import org.softuni.productshop.domain.model.view.order.OrderDetailsViewModel;
import org.softuni.productshop.domain.model.view.order.OrderViewModel;
import org.softuni.productshop.domain.model.view.product.ProductViewModel;
import org.softuni.productshop.service.OrderService;
import org.softuni.productshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Set;

@Controller
@RequestMapping("/orders")
public class OrderController extends BaseController {

    private final OrderService orderService;
    private final ProductService productService;
    private final ModelMapper modelMapper;

    @Autowired
    public OrderController(OrderService orderService, ProductService productService, ModelMapper modelMapper) {
        this.orderService = orderService;
        this.productService = productService;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/product/{id}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView order(@PathVariable(name = "id") String id, ModelAndView modelAndView) {
        try {
            ProductServiceModel productById = this.productService.findById(id);
            ProductViewModel product = this.modelMapper.map(productById, ProductViewModel.class);
            modelAndView.addObject("product", product);
        } catch (CustomException e) {
            return super.redirect("/somethingWentWrong"); // TODO: somethingWentWrong page
        }
        return super.view("/views/order/order-product", modelAndView);
    }

    @PostMapping("/product/{id}")
    public ModelAndView confirmOrder(@PathVariable(name = "id") String id,
                                     @ModelAttribute(name = "order") @Valid OrderProductBindingModel order,
                                     BindingResult bindingResult,
                                     ModelAndView modelAndView,
                                     Principal principal) {

        if (bindingResult.hasErrors()) {
            modelAndView.addObject("order", order);
            super.view("/views/order/order-product", modelAndView);
        }
        try {
            this.orderService.create(id, order, principal.getName());
        } catch (CustomException e) {
            return super.redirect("/somethingWentWrong"); //TODO: somethingWentWrong page
        }
        return super.redirect("/orders/my");
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ModelAndView allOrders(ModelAndView modelAndView) {
        Set<OrderViewModel> allOrders = this.orderService.findAll();
        modelAndView.addObject("orders", allOrders);
        return super.view("/views/order/all-orders", modelAndView);
    }

    @GetMapping("/my")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView myOrders(Principal principal, ModelAndView modelAndView) {
        Set<OrderViewModel> allOrdersByUser = this.orderService
                .findAllOrdersByUser(principal.getName());
        modelAndView.addObject("orders", allOrdersByUser);
        return super.view("/views/order/my-orders", modelAndView);
    }

    @GetMapping("/details/{id}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView details(@PathVariable(name = "id") String id, ModelAndView modelAndView) {
        try {
            OrderDetailsViewModel order = this.orderService.findOrderById(id);
            modelAndView.addObject("order", order);
        } catch (CustomException e) {
            return super.redirect("/somethingWentWrong"); //TODO: somethingWentWrong page
        }
        return super.view("/views/order/order-details", modelAndView);
    }
}
