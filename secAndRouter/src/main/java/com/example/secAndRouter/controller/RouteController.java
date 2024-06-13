package com.example.secAndRouter.controller;

import com.example.secAndRouter.service.OrderServiceHandler;
import com.example.secAndRouter.service.ProductServiceHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
public class RouteController {

    @Autowired
    private ProductServiceHandler productServiceHandler;
    @Autowired
    private OrderServiceHandler orderServiceHandler;

    @PostMapping("/vs/create/product")
    public Object createProduct(@RequestBody Object object) {
        return productServiceHandler.createProduct(object);
    }

    @PostMapping("/vs/get/product")
    public Object getProduct(@RequestBody Object object) {
        return productServiceHandler.getProduct(object);
    }

    @GetMapping("/vs/get/products")
    public Flux<Object> getProducts() {
        return productServiceHandler.getProducts();
    }

    @PostMapping("/vs/create/order")
    public Object createOrder(@RequestBody Object object) {
        return orderServiceHandler.createOrder(object);
    }

    @PostMapping("/vs/get/order")
    public Object getOrder(@RequestBody Object object) {
        return orderServiceHandler.getOrder(object);
    }

    @GetMapping("/vs/get/orders")
    public Flux<Object> getOrders() {
        return orderServiceHandler.getOrders();
    }

    @PostMapping("/vs/get/orders/by/user")
    public Flux<Object> getOrdersByUser(@RequestBody Object object) {
        return orderServiceHandler.getOrdersByUser(object);
    }
}
