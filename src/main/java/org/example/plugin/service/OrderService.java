package org.example.plugin.service;

import org.example.plugin.entity.Order;
import org.example.plugin.forbiddenImports.ForbiddenClass;

import java.util.Date;

public class OrderService {

    private final ForbiddenClass forbiddenClass = new ForbiddenClass();

    public void createOrder(String orderName){
        Order order = new Order();
        order.setName(orderName);
        order.setCreatedDate(new Date(System.currentTimeMillis()));
        // save to repository omitted for the example
    }
}
