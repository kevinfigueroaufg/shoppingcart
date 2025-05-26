package com.prueba.shoppingcart.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "orden")
public class Order {
    @Id
    @SequenceGenerator(name = "orden_id_seq", sequenceName = "orden_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orden_id_seq")
    private Integer id;

    private Date creationDate;

    private Integer clientId;

    private Double total;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<OrderDetail> orderDetails = new ArrayList<OrderDetail>();
}
