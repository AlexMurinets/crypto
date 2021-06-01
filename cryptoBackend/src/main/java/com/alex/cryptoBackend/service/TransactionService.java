package com.alex.cryptoBackend.service;

import com.alex.cryptoBackend.dto.TransactionDto;

import java.util.List;

public interface TransactionService {
    List<TransactionDto> getAllTransactions();
}
