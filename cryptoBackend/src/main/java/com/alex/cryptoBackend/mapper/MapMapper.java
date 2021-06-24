package com.alex.cryptoBackend.mapper;

import com.alex.cryptoBackend.dto.*;
import com.alex.cryptoBackend.model.*;
import com.alex.cryptoBackend.security.service.UserDetailsImpl;
import org.mapstruct.Mapper;


import java.util.List;

@Mapper(componentModel = "spring")
public interface MapMapper {
    UserDetailsImpl toUserDetails(User user);
    RoleDto toDto(Role role);
    Role toRole(RoleDto roleDto);
    User toUser(UserDto userDto);
    User toUser(NewUserDto newUser);
    Currency toCurrency(CurrencyDto currencyDto);
    CurrencyDto toDto(Currency currency);
    List<CurrencyDto> toCurrencyListDto(List<Currency> currencies);
    Wallet toWallet(WalletDto walletDto);
    List<WalletDto> toWalletListDto(List<Wallet> wallets);
    WalletDto toDto(Wallet wallet);
    UserDto toDto(User user);
    List<UserDto> toDto(List<User> users);
    TransactionDto toDto(Transaction transaction);
}
