package com.mongoClient.mongoClientHttp.repository;

import com.mongoClient.mongoClientHttp.entity.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrderRepository extends MongoRepository<Order, String> {

    List<Order> findByVnuser(String vnuserId);
}
