package com.mongoClient.mongoClientHttp.controller;

import com.mongoClient.mongoClientHttp.entity.Product;
import com.mongoClient.mongoClientHttp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/productos")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

    @GetMapping("/todos")
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @PutMapping("/actualizar")
    public Product updateStock(@RequestBody Product product) {
        return productRepository.save(product);
    }

    @GetMapping("/obtener/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") String id) {
        return productRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
