package com.mongoClient.mongoClientHttp.controller;

import com.mongoClient.mongoClientHttp.entity.Order;
import com.mongoClient.mongoClientHttp.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pedidos")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @PostMapping
    public Order createOrder(@RequestBody Order product) {
        return orderRepository.save(product);
    }

    @GetMapping("/todos")
    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    @GetMapping("/obtener/por/usuario/{user}")
    public List<Order> getOrdersByUser(@PathVariable("user") String user) {
        return orderRepository.findByVnuser(user);
    }

    @GetMapping("/obtener/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable("id") String id) {
        return orderRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
