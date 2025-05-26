package com.prueba.shoppingcart.service;

import com.prueba.shoppingcart.entity.Order;
import com.prueba.shoppingcart.entity.Pay;
import com.prueba.shoppingcart.repository.PayRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PayService {
    private final PayRepository payRepository;
    private final OrderService orderService;

    public PayService(PayRepository payRepository, OrderService orderService) {
        this.payRepository = payRepository;
        this.orderService = orderService;
    }

    @Transactional
    public Pay payOrder(Integer orderId) {
        Optional<Order> order = Optional.of(new Order());
        order=orderService.getOrdersByID(orderId);
        Pay pay = new Pay();
        pay.setOrderId(order.get().getId());
        pay.setTotal(order.get().getTotal());
        pay.setTxnDate(new Date());
        pay.setTxnResponse("00"); // Si hay error cualquier otro numero de 2 digitos
        return payRepository.save(pay);
    }

    public List<Pay> getAllPayments() {
        return payRepository.findAll();
    }

    }
