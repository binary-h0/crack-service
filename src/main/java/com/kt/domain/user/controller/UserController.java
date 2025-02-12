package com.kt.domain.user.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kt.domain.user.dto.LoginDto;
import com.kt.domain.user.dto.UserDto;
import com.kt.domain.user.entity.User;
import com.kt.domain.user.service.UserService;
import com.kt.global.dto.Result;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<Result<?>> createUser(@RequestBody UserDto userDto) {
        return ResponseEntity.ok().body(Result.success(userService.createUser(userDto)));
    }

    @GetMapping
    public ResponseEntity<Result<?>> getAllUsers() {
        return ResponseEntity.ok().body(Result.success(userService.getAllUsers()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Result<?>> updateUser(@PathVariable Long id, @RequestBody UserDto userDto,
            HttpSession session) {
        return ResponseEntity.ok().body(Result.success(userService.updateUser(id, userDto, session)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Result<?>> getUserById(@PathVariable Long id, HttpSession session) {
        return ResponseEntity.ok().body(Result.success(userService.getUserById(id, session)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Result<?>> deleteUser(@PathVariable Long id, HttpSession session) {
        return ResponseEntity.ok().body(Result.success(userService.deleteUser(id, session)));
    }

    @PostMapping("/login")
    public ResponseEntity<Result<?>> login(@RequestBody LoginDto loginDto, HttpSession session) {
        return ResponseEntity.ok().body(Result.success(userService.login(loginDto, session)));
    }

    @GetMapping("/logout")
    public ResponseEntity<Result<?>> logout(HttpSession session) {
        return ResponseEntity.ok().body(Result.success(userService.logout(session)));
    }

    @GetMapping("/login/refresh")
    public ResponseEntity<Result<?>> loginRefresh(HttpSession session) {
        return ResponseEntity.ok().body(Result.success(userService.loginRefresh(session)));
    }

    @PostMapping("/find/id")
    public ResponseEntity<Result<?>> findId(@RequestBody UserDto userDto) {
        return ResponseEntity.ok().body(Result.success(userService.findId(userDto)));
    }

    @PostMapping("/find/password")
    public ResponseEntity<Result<?>> findPassword(@RequestBody UserDto userDto) {
        return ResponseEntity.ok().body(Result.success(userService.findPassword(userDto)));
    }
}
