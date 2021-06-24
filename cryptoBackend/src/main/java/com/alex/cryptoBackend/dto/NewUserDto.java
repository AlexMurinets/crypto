package com.alex.cryptoBackend.dto;

import com.alex.cryptoBackend.model.Role;
import com.alex.cryptoBackend.model.UserState;
import com.alex.cryptoBackend.model.UserStatus;
import lombok.Data;

import java.util.Set;

@Data
public class NewUserDto {
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private UserStatus status;
    private UserState state;
    private Set<RoleDto> roles;
}
