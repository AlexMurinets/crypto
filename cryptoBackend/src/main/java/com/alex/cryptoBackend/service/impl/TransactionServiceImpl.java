package com.alex.cryptoBackend.service.impl;

import com.alex.cryptoBackend.dto.TransactionDto;
import com.alex.cryptoBackend.mapper.MapMapper;
import com.alex.cryptoBackend.model.Currency;
import com.alex.cryptoBackend.model.Transaction;
import com.alex.cryptoBackend.model.User;
import com.alex.cryptoBackend.model.Wallet;
import com.alex.cryptoBackend.repository.CurrencyRepository;
import com.alex.cryptoBackend.repository.TransactionRepository;
import com.alex.cryptoBackend.repository.UserRepository;
import com.alex.cryptoBackend.repository.WalletRepository;
import com.alex.cryptoBackend.service.TransactionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
@Service
@Slf4j
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final WalletRepository walletRepository;
    private final UserRepository userRepository;
    private final CurrencyRepository currencyRepository;
    private final MapMapper mapper;

    @Override
    public List<TransactionDto> getAllTransactions() {
        return mapper.toTransactionDtoList(transactionRepository.findAll());
    }

    @Override
    public TransactionDto getTransactionById(Long id) {
        return mapper.toDto(transactionRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Transaction with id " + id + " doesn't exist")));

    }

    @Override
    public List<TransactionDto> getTransactionsByWalletPositivePeriod(Long walletId, LocalDate start, LocalDate end) {
        Wallet wallet = walletRepository.findById(walletId).orElseThrow(() -> new IllegalArgumentException("Wallet doesn't exist"));
        final Predicate<Transaction> predicate = transaction -> transaction.getTime().isAfter(ChronoLocalDateTime.from(start)) && transaction.getTime().isBefore(ChronoLocalDateTime.from(end));
        List<TransactionDto> transactionsReceived = mapper.toTransactionDtoList(transactionRepository.findByReceiver(wallet).stream()
                .filter(predicate)
                .collect(Collectors.toList()));
        return transactionsReceived;
    }

    @Override
    public List<TransactionDto> getTransactionsByWalletNegativePeriod(Long walletId, LocalDate start, LocalDate end) {
        Wallet wallet = walletRepository.findById(walletId).orElseThrow(() -> new IllegalArgumentException("Wallet doesn't exist"));
        final Predicate<Transaction> predicate = transaction -> transaction.getTime().isAfter(ChronoLocalDateTime.from(start)) && transaction.getTime().isBefore(ChronoLocalDateTime.from(end));
        List<TransactionDto> transactionsSend = mapper.toTransactionDtoList(transactionRepository.findBySender(wallet).stream()
                .filter(predicate)
                .collect(Collectors.toList()));
        return transactionsSend;
    }


    @Override
    public List<TransactionDto> getAllTransactionsByPeriod(LocalDate start, LocalDate end) {
        final Predicate<Transaction> predicate = transaction -> transaction.getTime().isAfter(ChronoLocalDateTime.from(start)) && transaction.getTime().isBefore(ChronoLocalDateTime.from(end));
        List<TransactionDto> transactions= mapper.toTransactionDtoList(transactionRepository.findAll().stream()
                .filter(predicate)
                .collect(Collectors.toList()));
        return transactions;
    }

    @Override
    public List<TransactionDto> getAllWalletsTransactions(Long walletId) {
        Wallet wallet = walletRepository.findById(walletId).orElseThrow(() -> new IllegalArgumentException("Wallet doesn't exist"));
        return getTransactionDtos(wallet);
    }

    @Override
    public List<TransactionDto> getAllWalletsTransactionsByUserAndCurrency(Long userId, String abbreviation) {
        User user = userRepository.findById(userId).orElseThrow();
        Currency currency = currencyRepository.findByAbbreviation(abbreviation).orElseThrow();
        Wallet wallet = walletRepository.findByUserAndCurrency(user, currency).orElseThrow(() -> new IllegalArgumentException("Wallet doesn't exist"));
        return getTransactionDtos(wallet);
    }

    private List<TransactionDto> getTransactionDtos(Wallet wallet) {
        List<TransactionDto> transactionsSend = mapper.toTransactionDtoList(transactionRepository.findBySender(wallet));
        List<TransactionDto> transactionsReceive = mapper.toTransactionDtoList(transactionRepository.findByReceiver(wallet));
        List<TransactionDto> transactionsAll = Stream.concat(transactionsReceive.stream(), transactionsSend.stream())
                .sorted(Comparator.comparing(TransactionDto::getTime))
                .collect(Collectors.toList());
        return transactionsAll;
    }

    @Override
    @Transactional
    public TransactionDto executeTransactionWithWallets(Long senderId, Long receiverId, BigDecimal amount) {
        Wallet sender = walletRepository.findById(senderId).orElseThrow(() -> new IllegalArgumentException("Wallet doesn't exist"));
        Wallet receiver = walletRepository.findById(receiverId).orElseThrow(() -> new IllegalArgumentException("Wallet doesn't exist"));
        if (sender.getAmount().compareTo(amount) >= 0) {
            sender.setAmount(sender.getAmount().subtract(amount));
            receiver.setAmount(receiver.getAmount().add(amount));
        }
        else {
            log.error("Not enough money");
            throw new IllegalArgumentException("Not enough money");
        }
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setSender(sender);
        walletRepository.save(sender);
        transaction.setReceiver(receiver);
        walletRepository.save(receiver);
        transaction.setTime(LocalDateTime.now());
        transactionRepository.save(transaction);
        return mapper.toDto(transaction);
    }

    @Override
    public TransactionDto executeTransactionUsers(Long senderId, Long receiverId, BigDecimal amount) {
        return null;
    }
}
