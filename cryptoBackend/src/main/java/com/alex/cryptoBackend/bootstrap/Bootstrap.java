package com.alex.cryptoBackend.bootstrap;

import com.alex.cryptoBackend.model.*;
import com.alex.cryptoBackend.repository.CurrencyRepository;
import com.alex.cryptoBackend.repository.RoleRepository;
import com.alex.cryptoBackend.repository.UserRepository;
import com.alex.cryptoBackend.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class Bootstrap implements CommandLineRunner {

    private final UserRepository userRepository;
    private final CurrencyRepository currencyRepository;
    private final WalletRepository walletRepository;
    private final RoleRepository roleRepository;
    private  final PasswordEncoder encoder;

    @Override
    public void run(String... args) throws Exception {

        Role userRole = new Role();
        userRole.setName(ERole.ROLE_USER);
        roleRepository.save(userRole);

        Role adminRole = new Role();
        adminRole.setName(ERole.ROLE_ADMIN);
        roleRepository.save(adminRole);

        User user = new User();
        user.setFirstName("Alexei");
        user.setLastName("Murinets");
        user.setUsername("leha777");
        user.setEmail("Alexei7a@gmail.com");
        user.setPassword(encoder.encode("popins777"));
        user.setState(UserState.ACTIVE);
        user.getRoles().add(userRole);
        user.getRoles().add(adminRole);
        user.setStatus(UserStatus.BASIC);
        userRepository.save(user);

        User user1 = new User();
        user1.setFirstName("Alisher");
        user1.setLastName("Morgenshtern");
        user1.setUsername("clown");
        user1.setEmail("Alisher@gmail.com");
        user1.setPassword(encoder.encode("popins666"));
        user1.getRoles().add(userRole);
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
