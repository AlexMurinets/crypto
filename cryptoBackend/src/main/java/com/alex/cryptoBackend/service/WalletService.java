package com.alex.cryptoBackend.service;

import com.alex.cryptoBackend.dto.UserDto;
import com.alex.cryptoBackend.dto.WalletDto;
import com.alex.cryptoBackend.model.User;

import java.util.List;

public interface WalletService {
    WalletDto createWallet(WalletDto wallet);
    WalletDto updateWallet(WalletDto wallet, Long id);
    WalletDto getWalletById(Long id);
    List<WalletDto> getWalletsByUser(UserDto user);
    List<WalletDto> getAllWallets();
    void delete(Long id);
}
