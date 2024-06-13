package com.example.secAndRouter.service;

import com.example.secAndRouter.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class ProductServiceHandler {

    @Autowired
    private WebClient.Builder webClientBuilder;
    @Value("${elixir.base-url}")
    private String elixirBaseUrl;

    public Object createProduct(Object object) {
        WebClient webClient = webClientBuilder.build();
        return webClient.post()
                .uri(elixirBaseUrl + "/vanestore/v1/create/product")
                .body(Mono.just(object), Object.class)
                .retrieve()
                .bodyToMono(Object.class);
    }

    public Flux<Object> getProducts() {
        WebClient webClient = webClientBuilder.build();
        return webClient.get()
                .uri(elixirBaseUrl + "/vanestore/v1/get/products")
                .retrieve()
                .bodyToFlux(Object.class);
    }

    public Object getProduct(Object object) {
        WebClient webClient = webClientBuilder.build();
        try {
            return webClient.post()
                    .uri(elixirBaseUrl + "/vanestore/v1/get/product")
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
}
