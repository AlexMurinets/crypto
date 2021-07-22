package com.alex.cryptoBackend.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
//    @Column(nullable = false)
    private User user;
    @OneToOne
//    @Column(nullable = false)
    private Currency currency;
    private BigDecimal amount;
}
