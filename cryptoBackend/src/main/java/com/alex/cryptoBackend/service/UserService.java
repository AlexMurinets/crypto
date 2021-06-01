package com.alex.cryptoBackend.service;

import com.alex.cryptoBackend.dto.NewUserDto;
import com.alex.cryptoBackend.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> getAllUsers();
    UserDto getUserById(Long id);
    void deleteUserById(Long id);
    UserDto createUser(NewUserDto newUser);
    UserDto updateUser(UserDto user, Long id);
}
