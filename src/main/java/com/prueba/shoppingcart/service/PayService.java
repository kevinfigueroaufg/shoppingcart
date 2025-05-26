package com.prueba.shoppingcart.service;

import com.prueba.shoppingcart.entity.Order;
import com.prueba.shoppingcart.entity.Pay;
import com.prueba.shoppingcart.exception.CustomException;
import com.prueba.shoppingcart.repository.PayRepository;
import jakarta.persistence.PersistenceException;
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
        Optional<Order> order;
        Pay payReponse = new Pay();
        try {
            order=orderService.getOrdersByID(orderId);
            if (order.isPresent()) {
                Pay pay = new Pay();
                pay.setOrderId(order.get().getId());
                pay.setTotal(order.get().getTotal());
                pay.setTxnDate(new Date());
                pay.setTxnResponse("00"); // Si hay error cualquier otro numero de 2 digitos
                payReponse= payRepository.save(pay);
            }
        }catch (PersistenceException ex) {
            // Manejar errores de la base de datos, como problemas de conexión o constraints
            throw new CustomException("Error al insertar la cita en la base de datos. ", ex);
        } catch (Exception ex) {
            // Manejar cualquier otra excepción
            throw new CustomException("Error inesperado al insertar la cita. ", ex);
        }
        return payReponse;
    }

    public List<Pay> getAllPayments() {
        return payRepository.findAll();
    }

    }
