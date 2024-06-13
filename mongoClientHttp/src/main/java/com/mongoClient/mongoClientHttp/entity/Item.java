package com.mongoClient.mongoClientHttp.entity;


import lombok.Data;

@Data
public class Item {

    private String product_id;
    private String name;
    private String description;
    private Integer items;
    private String item_price;
    private Double subtotal;
    private String image;
}
