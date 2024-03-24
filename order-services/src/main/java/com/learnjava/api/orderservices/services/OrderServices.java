package com.learnjava.api.orderservices.services;

import com.learnjava.api.orderservices.dto.OrderLineItemsRequest;
import com.learnjava.api.orderservices.dto.OrderRequest;
import com.learnjava.api.orderservices.model.Order;
import com.learnjava.api.orderservices.model.OrderLineItems;
import com.learnjava.api.orderservices.repository.OrderServiceRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Service
public class OrderServices {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    OrderServiceRepository orderServiceRepository;

    public void placeOrder(OrderRequest orderRequest) {
       // List<OrderLineItems> orderLineItems = (List<OrderLineItems>) orderRequest.getLineItems().stream().map(orderLineItemsRequest -> mapToLineItemsDto(orderLineItemsRequest));

        Order order = modelMapper.map(orderRequest, Order.class);
        orderServiceRepository.save(order);
    }

    private OrderLineItems mapToLineItemsDto(OrderLineItemsRequest orderLineItemsRequest) {
        return modelMapper.map(orderLineItemsRequest, OrderLineItems.class);
    }
}
