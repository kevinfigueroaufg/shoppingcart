package com.prueba.shoppingcart.service;

import com.prueba.shoppingcart.config.ProductProxy;
import com.prueba.shoppingcart.dto.ProductDTO;
import com.prueba.shoppingcart.entity.Client;
import com.prueba.shoppingcart.entity.Order;
import com.prueba.shoppingcart.entity.OrderDetail;
import com.prueba.shoppingcart.exception.CustomException;
import com.prueba.shoppingcart.repository.OrderRepository;
import jakarta.persistence.PersistenceException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductProxy productoClient;
    private final ArrayList<Client> avaliableClients= new ArrayList<Client>();

    public OrderService(OrderRepository orderRepository, ProductProxy productoClient) {
        this.orderRepository = orderRepository;
        this.productoClient = productoClient;
        avaliableClients.add(new Client(1, "Kevin Menjivar", "kevin.menjivar@outlook.com", "San Salvador"));
        avaliableClients.add(new Client(2, "Mayra Figueroa", "mayra.figueroa@outlook.com", "San Salvador"));
        avaliableClients.add(new Client(3, "Glenda Martinez", "glenda.martinez@outlook.com", "San Salvador"));
        avaliableClients.add(new Client(4, "Maria Martinez", "maria.martinez@outlook.com", "San Salvador"));
        avaliableClients.add(new Client(5, "Meredith Palma", "meredith.palma@outlook.com", "San Salvador"));
    }

    public Order addOrder(Order order) {
        try {
            boolean existClient = false;
            for (Client client : avaliableClients) {
                if (client.getId().equals(order.getClientId())) {existClient = true;break;}
            }
            if (existClient) {
                order.setCreationDate(new Date());
                Double total = 0.00;
                List<OrderDetail> detailList = new ArrayList<>();

                for (OrderDetail item : order.getOrderDetails()) {
                    ProductDTO product = productoClient.getProductById(item.getProductId());
                    Double subtotal = product.getPrice() * item.getQty();
                    total += subtotal;

                    OrderDetail detail = new OrderDetail();
                    detail.setProductId(product.getId());
                    detail.setQty(item.getQty());
                    detail.setUnitPrice(product.getPrice());
                    detail.setOrder(order);
                    detailList.add(detail);
                }

                order.setTotal(total);
                order.setOrderDetails(detailList);
                return orderRepository.save(order);
            }else {
                System.out.println("Cliente no disponible");
                return null;
            }
        }catch (PersistenceException ex) {
            // Manejar errores de la base de datos, como problemas de conexión o constraints
            throw new CustomException("Error al insertar la cita en la base de datos. ", ex);
        } catch (Exception ex) {
            // Manejar cualquier otra excepción
            throw new CustomException("Error inesperado al insertar la cita. ", ex);
        }

    }

    public void cancelOrder(Integer orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow();
        orderRepository.delete(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrdersByID(Integer id) {
        return orderRepository.findById(id);
    }
}
