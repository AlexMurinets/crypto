package com.alex.cryptoBackend.service.impl;

import com.alex.cryptoBackend.dto.UserDto;
import com.alex.cryptoBackend.dto.WalletDto;
import com.alex.cryptoBackend.mapper.MapMapper;
import com.alex.cryptoBackend.model.Wallet;
import com.alex.cryptoBackend.repository.WalletRepository;
import com.alex.cryptoBackend.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;
    private final MapMapper mapper;

    @Override
    public WalletDto createWallet(WalletDto wallet) {
        Wallet newWallet = mapper.toWallet(wallet);
        walletRepository.save(newWallet);
        return mapper.toDto(newWallet);
    }

    @Override
    public WalletDto updateWallet(WalletDto wallet, Long id) {
        walletRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        wallet.setId(id);
        Wallet updatedWallet = mapper.toWallet(wallet);
        walletRepository.save(updatedWallet);
        return mapper.toDto(updatedWallet);
    }

    @Override
    public WalletDto getWalletById(Long id) {
        Wallet wallet = walletRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        return mapper.toDto(wallet);
    }

    @Override
    public List<WalletDto> getWalletsByUser(UserDto user) {
        List<Wallet> wallets = walletRepository.findByUser(mapper.toUser(user));
        return mapper.toWalletListDto(wallets);
    }

    @Override
    public List<WalletDto> getAllWallets() {
        List<Wallet> wallets = walletRepository.findAll();
        return mapper.toWalletListDto(wallets);
    }

    @Override
    public void delete(Long id) {
        Wallet wallet = walletRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        if (wallet.getAmount().equals(BigDecimal.ZERO)) {
            walletRepository.delete(wallet);
        }
    }
}
