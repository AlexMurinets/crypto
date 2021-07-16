package com.alex.cryptoBackend.service.impl;

import com.alex.cryptoBackend.dto.NewUserDto;
import com.alex.cryptoBackend.dto.UserDto;
import com.alex.cryptoBackend.mapper.MapMapper;
import com.alex.cryptoBackend.model.User;
import com.alex.cryptoBackend.model.UserState;
import com.alex.cryptoBackend.repository.UserRepository;
import com.alex.cryptoBackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final MapMapper mapper;

    private final static String USER_EXCEPTION_MESSAGE = "User doesn't exist";

    @Override
    public List<UserDto> getAllUsers() {
        List<UserDto> users = mapper.toDto(
                userRepository.findAll().stream()
                        .filter(user -> user.getState() == UserState.ACTIVE)
                        .collect(Collectors.toList())
        );
        return users;
    }

    @Override
    public UserDto getUserById(Long id) {
        return mapper.toDto(userRepository.findById(id).filter(user -> user.getState() == UserState.ACTIVE).orElseThrow(() -> new IllegalArgumentException(USER_EXCEPTION_MESSAGE)));
    }

    @Override
    public void deleteUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(USER_EXCEPTION_MESSAGE));
        user.setState(UserState.DELETED);
        userRepository.save(user);
    }

    @Override
    public UserDto createUser(NewUserDto newUser) {
        User user = mapper.toUser(newUser);
        userRepository.save(user);
        return mapper.toDto(user);
    }

    @Override
    public UserDto updateUser(UserDto user, Long id) {
        userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(USER_EXCEPTION_MESSAGE));
        user.setId(id);
        User updatedUser = mapper.toUser(user);
        userRepository.save(updatedUser);
        return mapper.toDto(updatedUser);
    }
}
