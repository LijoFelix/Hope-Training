package com.ecommerce.main;

import com.ecommerce.model.*;
import com.ecommerce.service.OrderService;

public class Main {
    public static void main(String[] args) {
        User u=new User("Siva", "Thiruvannamalai");
        Product p=new Product("Duke", 200000);
        Order o=new Order(p, u, 2);
        OrderService OS=new OrderService();
        OS.placeOrder(o);
    }
}