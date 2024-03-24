package com.learnjava.api.orderservices.controller;

import com.learnjava.api.orderservices.dto.OrderRequest;
import com.learnjava.api.orderservices.services.OrderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    OrderServices orderServices;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void placeOrder(@RequestBody OrderRequest orderRequest) {
        orderServices.placeOrder(orderRequest);
    }
}
