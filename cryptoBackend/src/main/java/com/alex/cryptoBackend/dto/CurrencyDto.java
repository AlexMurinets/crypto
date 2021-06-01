package com.alex.cryptoBackend.dto;

import lombok.Data;

@Data
public class CurrencyDto {
    private Long id;
    private String name;
    private Float value;
    private String abbreviation;
}
