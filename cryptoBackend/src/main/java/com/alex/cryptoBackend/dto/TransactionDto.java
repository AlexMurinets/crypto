package com.alex.cryptoBackend.dto;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class TransactionDto {
    private Long id;
    private UUID transactionId;
    private WalletDto sender;
    private WalletDto receiver;
    private BigDecimal amount;
    private LocalDateTime time;
}
