package com.mongoClient.mongoClientHttp.repository;

import com.mongoClient.mongoClientHttp.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {

}
