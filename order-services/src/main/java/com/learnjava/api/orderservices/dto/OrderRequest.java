package com.learnjava.api.orderservices.dto;

import com.learnjava.api.orderservices.model.OrderLineItems;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRequest {
    private String orderName;
    private List<OrderLineItemsRequest> lineItems;
}
