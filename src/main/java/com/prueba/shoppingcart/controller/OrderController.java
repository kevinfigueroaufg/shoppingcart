package com.prueba.shoppingcart.controller;

import com.prueba.shoppingcart.entity.Order;
import com.prueba.shoppingcart.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shoppingcart")
@CrossOrigin(maxAge = 3600)
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/addOrder")
    public ResponseEntity<Order> addOrder(@RequestBody Order order) {
        return ResponseEntity.ok(orderService.addOrder(order));
    }

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<Order> getOrdersByID(@PathVariable Integer id) {
        return orderService.getOrdersByID(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}

