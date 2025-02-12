package com.kt.domain.user.service;

import java.util.List;

import com.kt.domain.user.dto.LoginDto;
import com.kt.domain.user.dto.UserDto;

import jakarta.servlet.http.HttpSession;

public interface UserService {

    public UserDto createUser(UserDto userDto);

    public List<UserDto> getAllUsers();

    public UserDto updateUser(Long id, UserDto userDto, HttpSession session);

    public UserDto getUserById(Long id, HttpSession session);

    public String deleteUser(Long id, HttpSession session);

    public String login(LoginDto loginDto, HttpSession session);

    public String logout(HttpSession session);

    public String loginRefresh(HttpSession session);

    public String findId(UserDto userDto);

    public String findPassword(UserDto userDto);
}
