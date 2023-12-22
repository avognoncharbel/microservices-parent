package com.kinikini.orderservice.service;


import com.kinikini.orderservice.dto.OrderLinesItemsDto;
import com.kinikini.orderservice.dto.OrderRequest;
import com.kinikini.orderservice.model.Order;
import com.kinikini.orderservice.model.OrderLineItems;
import com.kinikini.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional

public class OrderService {
    private final OrderRepository orderRepository;

    public void placeOrder(OrderRequest orderRequest){
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderLineItems> orderLineItems = orderRequest.getOrderLinesItemsDtoList()
                .stream()
                .map(this::mapToDto).toList();

        order.setOrderLineItemsList(orderLineItems);

        orderRepository.save(order);

    }

    private OrderLineItems mapToDto(OrderLinesItemsDto orderLinesItemsDto) {

         OrderLineItems orderLineItems = new OrderLineItems();
         orderLineItems.setId(orderLinesItemsDto.getId());
         orderLineItems.setQuantity(orderLineItems.getQuantity());
         orderLineItems.setPrice(orderLinesItemsDto.getPrice());
         orderLineItems.setSkuCode(orderLinesItemsDto.getSkuCode());
         return orderLineItems;
    }
}
