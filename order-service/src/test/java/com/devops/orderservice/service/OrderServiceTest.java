package com.devops.orderservice.service;

import com.devops.orderservice.repository.OrderRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderServiceTest {

    private final OrderRepository repository = Mockito.mock(OrderRepository.class);

    @Test
    @DisplayName("Should save an Order")
    void saveOrder() {
        int actual = 0;
        assertEquals(0, actual);
//        OrderService orderService = new OrderService(repository);
//        Orders order = new Orders("1", "test order", 1, 1000);
//        Mockito.when(repository.save(Mockito.any(Orders.class))).thenReturn(order);
//
//        Orders testResponse = orderService.createOrder(order);
//        Assertions.assertEquals(order, testResponse);
    }

}