package com.prueba.shoppingcart.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "orden_detail")
public class OrderDetail {
    @Id
    @SequenceGenerator(name = "ordend_id_seq", sequenceName = "ordend_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ordend_id_seq")
    private Integer id;
    private Integer productId;
    private Integer qty;
    private Double unitPrice;

    @ManyToOne
    @JoinColumn(name = "order.id")
    @JsonBackReference
    private Order order;
}
