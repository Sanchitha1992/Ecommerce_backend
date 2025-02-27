package com.learnjava.api.orderservices.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineItemsRequest {
    private String skuCode;
    private BigDecimal price;
    private int quantity;
}
