package com.alex.cryptoBackend.mapper;

import com.alex.cryptoBackend.dto.*;
import com.alex.cryptoBackend.model.*;
import com.alex.cryptoBackend.repository.UserRepository;
import com.alex.cryptoBackend.security.service.UserDetailsImpl;
import com.alex.cryptoBackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public abstract class MapMapper {

    @Autowired
    protected UserRepository userRepository;

    public abstract UserDetailsImpl toUserDetails(User user);
    public abstract RoleDto toDto(Role role);
    public abstract Role toRole(RoleDto roleDto);
    public abstract User toUser(UserDto userDto);
    public abstract User toUser(NewUserDto newUser);
    public abstract Currency toCurrency(CurrencyDto currencyDto);
    public abstract CurrencyDto toDto(Currency currency);
    public abstract List<CurrencyDto> toCurrencyListDto(List<Currency> currencies);
    @Mapping(target = "user", expression = "java(userRepository.findById(walletDto.getUserId()).orElseThrow(() -> new IllegalArgumentException(\"User doesn't exists\")))")
    public abstract Wallet toWallet(WalletDto walletDto);
    public abstract List<WalletDto> toWalletListDto(List<Wallet> wallets);
    @Mapping(target = "userId", expression = "java(wallet.getUser().getId())")
    public abstract WalletDto toDto(Wallet wallet);
    public abstract Set<WalletDto> walletDtoSet(Set<Wallet> wallets);
    public abstract UserDto toDto(User user);
    public abstract List<UserDto> toDto(List<User> users);
    public abstract TransactionDto toDto(Transaction transaction);
    public abstract Transaction toTransaction(TransactionDto transactionDto);
    public abstract List<TransactionDto> toTransactionDtoList(List<Transaction> transactions);
}
