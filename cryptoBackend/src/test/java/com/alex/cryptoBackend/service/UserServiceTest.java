package com.alex.cryptoBackend.service;

import com.alex.cryptoBackend.dto.NewUserDto;
import com.alex.cryptoBackend.dto.UserDto;
import com.alex.cryptoBackend.mapper.MapMapper;
import com.alex.cryptoBackend.model.User;
import com.alex.cryptoBackend.model.UserState;
import com.alex.cryptoBackend.repository.UserRepository;
import com.alex.cryptoBackend.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@Slf4j
@ExtendWith(SpringExtension.class)
public class UserServiceTest {

    private UserService userService;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private MapMapper mapper;
    private final UserDto user1 = new UserDto();
    private final UserDto user2 = new UserDto();
    private final User user11 = new User();
    private final User user22 = new User();
    private final User user33 = new User();
    private List<User> allUsers;
    private List<UserDto> allUserDtos;
    private List<User> allActiveUsers;
    private final String USER_EXCEPTION_MESSAGE = "User doesn't exist";

    @BeforeEach
    void setUp() {
        userService = new UserServiceImpl(userRepository, mapper);
        user1.setEmail("Jonny@gmail.com");
        user1.setId(1L);
        user1.setUsername("Username");
        user1.setState(UserState.ACTIVE);
        user2.setEmail("Jonny1@gmail.com");
        user2.setUsername("Username1");
        user2.setId(2L);
        user2.setState(UserState.ACTIVE);
        user11.setEmail("Jonny@gmail.com");
        user11.setUsername("Username");
        user11.setState(UserState.ACTIVE);
        user11.setId(1L);
        user22.setEmail("Jonny1@gmail.com");
        user22.setUsername("Username1");
        user22.setState(UserState.ACTIVE);
        user22.setId(2L);
        user33.setEmail("Jonny12@gmail.com");
        user33.setUsername("Username1");
        user33.setState(UserState.DELETED);
        user33.setId(3L);
        allUsers = List.of(user11, user22, user33);
        allUserDtos = List.of(user1, user2);
        allActiveUsers = List.of(user11, user22);
    }

    @Test
    void testGetAllUsers() {
        final int actualSize = 2;
        when(userRepository.findAll()).thenReturn(allUsers);
        when(mapper.toDto(anyList())).thenReturn(allUserDtos);
        List<UserDto> actualUserDtos = userService.getAllUsers();
        System.out.println(actualUserDtos.get(0));
        System.out.println(allUsers.get(0));
        verify(mapper).toDto(eq(allActiveUsers));
        verify(userRepository).findAll();
        assertThat(actualUserDtos).isNotEmpty().isEqualTo(allUserDtos);
        assertThat(actualUserDtos.size()).isEqualTo(actualSize);
    }

    @Test
    void testUserGetByIdHappyPath() {
        final long actual = 1;
        when(userRepository.findById(actual)).thenReturn(java.util.Optional.of(user11));
        when(mapper.toDto(eq(user11))).thenReturn(user1);
        UserDto userDto = userService.getUserById(actual);
        assertThat(userDto).isEqualTo(user1);
    }

    @Test
    void testUserGetByIdUnhappyPathWrongId() {
        final long nonActual = 100;
        when(userRepository.findById(nonActual)).thenReturn(Optional.empty());
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() ->  userService.getUserById(nonActual))
                .withMessage(USER_EXCEPTION_MESSAGE);
    }

    @Test
    void testUserGetByIdUnhappyPathDeletedUser() {
        final long actual = 3;
        when(userRepository.findById(actual)).thenReturn(Optional.of(user33));
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() ->  userService.getUserById(actual))
                .withMessage(USER_EXCEPTION_MESSAGE);
    }

    @Test
    void testDeleteUserHappyPath() {
        final long actual = 1;
        when(userRepository.findById(actual)).thenReturn(java.util.Optional.of(user11));
        when(userRepository.save(eq(user11))).thenReturn(user11);
        userService.deleteUserById(actual);
        assertThat(user11.getState()).isEqualTo(UserState.DELETED);
    }

    @Test
    void testDeleteUserUnhappyPath() {
        final long nonActual = 100;
        when(userRepository.findById(nonActual)).thenReturn(Optional.empty());
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() ->  userService.deleteUserById(nonActual))
                .withMessage(USER_EXCEPTION_MESSAGE);
    }

    @Test
    void testCreateUser() {
        final String userEmail = "Test@gmail.com";
        NewUserDto newUserDto = new NewUserDto();
        newUserDto.setEmail(userEmail);
        User newUser = new User();
        newUser.setEmail(userEmail);
        UserDto expectedUserDto = new UserDto();
        expectedUserDto.setEmail(userEmail);
        when(mapper.toUser(eq(newUserDto))).thenReturn(newUser);
        when(mapper.toDto(eq(newUser))).thenReturn(expectedUserDto);
        UserDto actualUserDto = userService.createUser(newUserDto);
        verify(userRepository).save(eq(newUser));
        assertThat(actualUserDto).isEqualTo(expectedUserDto);
    }

    @Test
    void testUpdateUserHappyPath() {
        final long id = 1;
        final String userUsername = "newUsername";
        final String userEmail = "userEmail";
        user1.setUsername(userUsername);
        user1.setEmail(userEmail);
        User updatedUser = user11;
        updatedUser.setUsername(userUsername);
        updatedUser.setEmail(userEmail);
        when(userRepository.findById(id)).thenReturn(Optional.of(user11));
        when(mapper.toDto(eq(updatedUser))).thenReturn(user1);
        when(mapper.toUser(eq(user1))).thenReturn(updatedUser);
        UserDto actualUserDto = userService.updateUser(user1, id);
        assertThat(actualUserDto).isEqualTo(user1);
    }

    @Test
    void testUpdateUserUnhappyPath() {
        final long nonActual = 100;
        when(userRepository.findById(nonActual)).thenReturn(Optional.empty());
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() ->  userService.updateUser(user1, nonActual))
                .withMessage(USER_EXCEPTION_MESSAGE);
    }

}
