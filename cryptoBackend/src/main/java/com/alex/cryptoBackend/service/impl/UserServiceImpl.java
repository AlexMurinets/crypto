package com.alex.cryptoBackend.service.impl;

import com.alex.cryptoBackend.dto.NewUserDto;
import com.alex.cryptoBackend.dto.RoleDto;
import com.alex.cryptoBackend.dto.UserDto;
import com.alex.cryptoBackend.mapper.MapMapper;
import com.alex.cryptoBackend.model.Role;
import com.alex.cryptoBackend.model.User;
import com.alex.cryptoBackend.model.UserState;
import com.alex.cryptoBackend.repository.RoleRepository;
import com.alex.cryptoBackend.repository.UserRepository;
import com.alex.cryptoBackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final MapMapper mapper;
    private  final PasswordEncoder encoder;

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
        return mapper.toDto(userRepository.findById(id).filter(user -> user.getState() == UserState.ACTIVE).orElseThrow(IllegalArgumentException::new));
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.findById(id).orElseThrow(IllegalArgumentException::new).setState(UserState.DELETED);
    }

    @Override
    public UserDto createUser(NewUserDto newUser) {
        User user = mapper.toUser(newUser);
        Set<Role> roles = new HashSet<>();
        for (RoleDto role : newUser.getRoles()) {
            Role role1 = roleRepository.findByName(role.getName()).orElseThrow(() -> new IllegalArgumentException("Role doesn't exist"));
            roles.add(role1);
        }
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRoles(roles);
        userRepository.save(user);
        return mapper.toDto(user);
    }

    @Override
    public UserDto updateUser(UserDto user, Long id) {
        userRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        user.setId(id);
        User updatedUser = mapper.toUser(user);
        userRepository.save(updatedUser);
        return mapper.toDto(updatedUser);
    }
}
