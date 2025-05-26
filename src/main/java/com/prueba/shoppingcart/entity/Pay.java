package com.prueba.shoppingcart.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class Pay {
    @Id
    @SequenceGenerator(name = "pay_id_seq", sequenceName = "pay_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pay_id_seq")
    private Integer id;

    private Date txnDate;

    private Integer orderId;

    private String txnResponse;

    private Double total;
}
