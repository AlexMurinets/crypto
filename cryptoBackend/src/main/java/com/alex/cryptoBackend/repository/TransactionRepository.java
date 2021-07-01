package com.alex.cryptoBackend.repository;

import com.alex.cryptoBackend.model.Transaction;
import com.alex.cryptoBackend.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findBySender(Wallet wallet);
    List<Transaction> findByReceiver(Wallet wallet);
}
