//package com.alex.cryptoBackend.bootstrap;
//
//import com.alex.cryptoBackend.model.Role;
//import com.alex.cryptoBackend.model.User;
//import com.alex.cryptoBackend.model.Wallet;
//import com.alex.cryptoBackend.repository.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
//@Component
//@RequiredArgsConstructor
//public class Bootstrap implements CommandLineRunner {
//
//    private final UserRepository userRepository;
//
//    @Override
//    public void run(String... args) throws Exception {
//        List<User> users = userRepository.findAll();
//        for (User user: users) {
//            System.out.println(user.getWallets().size());
////            for (Wallet wallet: user.getWallets()) {
////                System.out.println(role.getName());
////            }
//        }
//    }
//}
