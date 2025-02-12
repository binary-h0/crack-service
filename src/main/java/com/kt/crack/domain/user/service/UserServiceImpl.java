package com.kt.crack.domain.user.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.kt.crack.domain.user.dto.LoginDto;
import com.kt.crack.domain.user.dto.UserDto;
import com.kt.crack.domain.user.entity.User;
import com.kt.crack.domain.user.repository.UserRepository;
import com.kt.crack.global.exception.CustomException;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto userDto) {
        if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "User already exists");
        }

        // 비밀번호 암호화 + salt 이용

        User user = User.builder()
                .username(userDto.getUsername())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .phone(userDto.getPhone())
                .address(userDto.getAddress())
                .role("USER")
                .build();

        userRepository.save(user);
        return userDto;
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> UserDto.builder()
                        .id(user.getId())
                        .username(user.getUsername())
                        .email(user.getEmail())
                        .build())
                .collect(Collectors.toList());

    }

    @Override
    public UserDto updateUser(Long id, UserDto userDto, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        userRepository.save(user);
        return userDto;
    }

    @Override
    public UserDto getUserById(Long id, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .username(user.getUsername())
                .build();
    }

    @Override
    public String deleteUser(Long id, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        userRepository.deleteById(id);
        return "User deleted successfully";
    }

    @Override
    public String login(LoginDto loginDto, HttpSession session) {
        User user = userRepository.findByEmail(loginDto.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (!user.getPassword().equals(loginDto.getPassword())) {
            throw new RuntimeException("Invalid password");
        }
        session.setAttribute("user", user);
        return "Login successful";
    }

    @Override
    public String logout(HttpSession session) {
        session.invalidate();
        return "Logout successful";
    }

    @Override
    public String loginRefresh(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        return "Login refresh successful";
    }

    @Override
    public String findId(UserDto userDto) {
        User user = userRepository.findByEmail(userDto.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));
        return user.getUsername();
    }

    @Override
    public String findPassword(UserDto userDto) {
        User user = userRepository.findByEmail(userDto.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));
        return user.getPassword();
    }

}