package com.alex.cryptoBackend.model;

import javax.persistence.*;
import java.math.BigDecimal;


@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    private UUID transactionId;
    @ManyToOne
    private Wallet sender;
    @ManyToOne
    private Wallet receiver;
    private BigDecimal amount;
}
