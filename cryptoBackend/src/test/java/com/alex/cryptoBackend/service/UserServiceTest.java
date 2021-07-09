package com.alex.cryptoBackend.service;

import com.alex.cryptoBackend.dto.UserDto;
import com.alex.cryptoBackend.mapper.MapMapper;
import com.alex.cryptoBackend.model.User;
import com.alex.cryptoBackend.model.UserState;
import com.alex.cryptoBackend.repository.UserRepository;
import com.alex.cryptoBackend.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.Server;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@Slf4j
@ExtendWith(SpringExtension.class)
//@SpringBootTest(
//        SpringBootTest.WebEnvironment.MOCK,
//        classes = Application.class)
//@ContextConfiguration
//@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    private UserService userService;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private MapMapper mapper;

    @BeforeEach
    void setUp() {
        userService = new UserServiceImpl(userRepository, mapper);
    }

    @Test
    void testGetAllUsers() {
        final UserDto user1 = new UserDto();
        user1.setEmail("Jonny@gmail.com");
        user1.setUsername("Username");
        user1.setState(UserState.ACTIVE);

        final UserDto user2 = new UserDto();
        user2.setEmail("Jonny1@gmail.com");
        user2.setUsername("Username1");
        user2.setState(UserState.ACTIVE);

        final User user11 = new User();
        user1.setEmail("Jonny@gmail.com");
        user1.setUsername("Username");
        user1.setState(UserState.ACTIVE);

        final User user22 = new User();
        user22.setEmail("Jonny1@gmail.com");
        user22.setUsername("Username1");
        user22.setState(UserState.ACTIVE);

        final User user33 = new User();
        user33.setEmail("Jonny12@gmail.com");
        user33.setUsername("Username1");
        user33.setState(UserState.DELETED);

        List<User> users = List.of(user11, user22, user33);

        List<UserDto> allUsers = List.of(user1, user2);
        when(userRepository.findAll()).thenReturn(users);

        List<User> actualAllUsers = List.of(user11, user22);
        log.info("this is " + actualAllUsers.size());
        when(mapper.toDto(anyList())).thenReturn(allUsers);

        List<UserDto> actualUsers = userService.getAllUsers();

        verify(userRepository).findAll();

        assertThat(actualUsers).isNotEmpty().isEqualTo(allUsers);
        assertThat(actualUsers.size()).isEqualTo(2);
    }

    @Test
    void testUser() {
        log.info("First test");
        assertThat(2).isEqualTo(1 + 1);
    }
}
