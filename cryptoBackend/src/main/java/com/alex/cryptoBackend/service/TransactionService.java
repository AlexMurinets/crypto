package com.alex.cryptoBackend.service;

import com.alex.cryptoBackend.dto.TransactionDto;
import com.alex.cryptoBackend.dto.WalletDto;
import com.alex.cryptoBackend.model.Wallet;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface TransactionService {
    List<TransactionDto> getAllTransactions();
    TransactionDto getTransactionById(Long id);
    List<TransactionDto> getTransactionsByWalletPositivePeriod(Long walletId, LocalDate start, LocalDate end);
    List<TransactionDto> getTransactionsByWalletNegativePeriod(Long walletId, LocalDate start, LocalDate end);
    List<TransactionDto> getAllTransactionsByPeriod(LocalDate start, LocalDate end);
    List<TransactionDto> getAllWalletsTransactions(Long walletId);
    TransactionDto executeTransaction(Long senderId, Long receiverId, BigDecimal amount);
}
