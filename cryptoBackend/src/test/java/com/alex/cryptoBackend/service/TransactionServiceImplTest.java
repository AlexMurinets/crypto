package com.alex.cryptoBackend.service;

import com.alex.cryptoBackend.mapper.MapMapper;
import com.alex.cryptoBackend.repository.CurrencyRepository;
import com.alex.cryptoBackend.repository.TransactionRepository;
import com.alex.cryptoBackend.repository.UserRepository;
import com.alex.cryptoBackend.repository.WalletRepository;
import com.alex.cryptoBackend.service.impl.TransactionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceImplTest {
    private TransactionService transactionService;

    @Mock
    private TransactionRepository transactionRepository;
    @Mock
    private WalletRepository walletRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private CurrencyRepository currencyRepository;
    @Mock
    private MapMapper mapper;

    @BeforeEach
    void setUp() {
        transactionService = new TransactionServiceImpl(transactionRepository, walletRepository, userRepository, currencyRepository, mapper);
    }

    @Test
    void getAllTransactions() {

    }

    @Test
    void getTransactionById() {

    }

    @Test
    void getTransactionsByWalletPositivePeriod() {

    }

    @Test
    void getTransactionsByWalletNegativePeriod() {

    }

    @Test
    void getAllTransactionsByPeriod() {

    }

    @Test
    void getAllWalletsTransactions() {

    }

    @Test
    void getAllWalletsTransactionsByUserAndCurrency() {

    }

    @Test
    void getTransactionDtos() {

    }

    @Test
    void setNegativeTransaction() {

    }

    @Test
    void executeTransactionWithWallets() {

    }

    @Test
    void executeTransactionUsers() {

    }

    @Test
    void executeTransactionUsersCurrency() {

    }

    @Test
    void getTransactionDto() {

    }
}
