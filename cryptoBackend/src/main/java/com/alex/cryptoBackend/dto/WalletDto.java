package com.alex.cryptoBackend.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class WalletDto {
    private Long id;
    private UserDto user;
    private CurrencyDto currency;
    private BigDecimal amount;
}
