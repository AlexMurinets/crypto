package com.alex.cryptoBackend.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @Column(nullable = false)
    private Wallet sender;
    @ManyToOne
    @Column(nullable = false)
    private Wallet receiver;
    @Column(nullable = false)
    private LocalDateTime time;
    @Column(nullable = false)
    private BigDecimal amount;
}
