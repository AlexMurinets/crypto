package com.alex.cryptoBackend.bootstrap;

import com.alex.cryptoBackend.model.*;
import com.alex.cryptoBackend.repository.CurrencyRepository;
import com.alex.cryptoBackend.repository.UserRepository;
import com.alex.cryptoBackend.repository.WalletRepository;
import com.alex.cryptoBackend.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class Bootstrap implements CommandLineRunner {

    private final UserRepository userRepository;
    private final CurrencyRepository currencyRepository;
    private final WalletRepository walletRepository;

    @Override
    public void run(String... args) throws Exception {
        User user = new User();
        user.setFirstName("Alexei");
        user.setLastName("Murinets");
        user.setEmail("Alexei7a@gmail.com");
        user.setPassword("popins777");
        user.setState(UserState.ACTIVE);
        user.setStatus(UserStatus.BASIC);
        userRepository.save(user);

        User user1 = new User();
        user1.setFirstName("Alisher");
        user1.setLastName("Morgenshtern");
        user1.setEmail("Alisher@gmail.com");
        user1.setPassword("popins666");
        user1.setState(UserState.ACTIVE);
        user1.setStatus(UserStatus.BASIC);
        userRepository.save(user1);

        Currency currency = new Currency();
        currency.setName("Dollar");
        currency.setAbbreviation("USD");
        currency.setValue(2.5F);
        currencyRepository.save(currency);

        Currency currency1 = new Currency();
        currency1.setName("Euro");
        currency1.setAbbreviation("EUR");
        currency1.setValue(2.5F);
        currencyRepository.save(currency1);

        Wallet wallet = new Wallet();
        wallet.setCurrency(currency1);
        wallet.setUser(user);
        wallet.setAmount(new BigDecimal(10000));
        walletRepository.save(wallet);

        Wallet wallet1 = new Wallet();
        wallet1.setCurrency(currency);
        wallet1.setUser(user);
        wallet1.setAmount(new BigDecimal(5000));
        walletRepository.save(wallet1);

        Wallet wallet2 = new Wallet();
        wallet2.setCurrency(currency1);
        wallet2.setUser(user1);
        wallet2.setAmount(new BigDecimal(1000));
        walletRepository.save(wallet2);


    }
}
