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
    @NotNull
    private Wallet sender;
    @ManyToOne
    @NotNull
    private Wallet receiver;
    @NotNull
    private LocalDateTime time;
    @NotNull
    @Positive
    private BigDecimal amount;
}
