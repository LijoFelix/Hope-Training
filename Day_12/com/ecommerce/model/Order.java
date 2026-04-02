package com.ecommerce.model;

public class Order {
    public Product product;
    public User user;
    public int quantity;
    public Order(Product product,User user,int quantity){
        this.product=product;
        this.user=user;
        this.quantity=quantity;
    }
}