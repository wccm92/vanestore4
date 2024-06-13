package com.example.secAndRouter.service;

import com.example.secAndRouter.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class OrderServiceHandler {

    @Autowired
    private WebClient.Builder webClientBuilder;
    @Value("${elixir.base-url}")
    private String elixirBaseUrl;

    public Object createOrder(Object object) {
        WebClient webClient = webClientBuilder.build();
        return webClient.post()
                .uri(elixirBaseUrl + "/vanestore/v1/create/order")
                .body(Mono.just(object), Object.class)
                .retrieve()
                .bodyToMono(Object.class);
    }

    public Object getOrder(Object object) {
        WebClient webClient = webClientBuilder.build();
        try {
            return webClient.post()
                    .uri(elixirBaseUrl + "/vanestore/v1/get/order")
                    .body(Mono.just(object), Object.class)
                    .retrieve()
                    .bodyToMono(Object.class);
        } catch (WebClientResponseException ex) {
            if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new ResourceNotFoundException("Producto no encontrado", ex);
            }
            throw ex;
        }
    }

    public Flux<Object> getOrders() {
        WebClient webClient = webClientBuilder.build();
        return webClient.get()
                .uri(elixirBaseUrl + "/vanestore/v1/get/orders")
                .retrieve()
                .bodyToFlux(Object.class);
    }

    public Flux<Object> getOrdersByUser(Object object) {
        WebClient webClient = webClientBuilder.build();
        return webClient.post()
                .uri(elixirBaseUrl + "/vanestore/v1/get/orders/by/user")
                .body(Mono.just(object), Object.class)
                .retrieve()
                .bodyToFlux(Object.class);
    }
}
