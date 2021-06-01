package com.alex.cryptoBackend.bootstrap;

import com.alex.cryptoBackend.model.Currency;
import com.alex.cryptoBackend.model.User;
import com.alex.cryptoBackend.model.UserState;
import com.alex.cryptoBackend.model.UserStatus;
import com.alex.cryptoBackend.repository.CurrencyRepository;
import com.alex.cryptoBackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Bootstrap implements CommandLineRunner {

    private final UserRepository userRepository;
    private final CurrencyRepository currencyRepository;

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
    }
}
