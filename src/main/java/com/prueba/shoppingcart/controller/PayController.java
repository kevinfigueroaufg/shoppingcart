package com.prueba.shoppingcart.controller;

import com.prueba.shoppingcart.entity.Order;
import com.prueba.shoppingcart.entity.Pay;
import com.prueba.shoppingcart.service.OrderService;
import com.prueba.shoppingcart.service.PayService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shoppingcart")
@CrossOrigin(maxAge = 3600)
public class PayController {

    private final PayService payService;

    public PayController(PayService payService) {
        this.payService = payService;
    }

    @PostMapping("/pay/{orderId}")
    public ResponseEntity<String> procesarPago(@PathVariable Integer orderId) {
        // Simulación del proceso de pago
        Pay response = payService.payOrder(orderId);
        if (response != null) {
            return ResponseEntity.ok("Pago procesado exitosamente para la orden: " + response.getOrderId()+" por "+response.getTotal());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/payments")
    public ResponseEntity<List<Pay>> getAllOrders() {
        return ResponseEntity.ok(payService.getAllPayments());
    }
}
