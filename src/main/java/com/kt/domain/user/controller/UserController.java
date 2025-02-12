package com.kt.domain.user.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kt.domain.user.entity.User;
import com.kt.domain.user.service.UserService;
import com.kt.global.dto.Result;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<Result<User>> createUser(@RequestBody User user) {
        return ResponseEntity.ok().body(Result.success(userService.saveUser(user)));
    }

    @GetMapping
    public ResponseEntity<Result<List<User>>> getAllUsers() {
        return ResponseEntity.ok().body(Result.success(userService.getAllUsers()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Result<Optional<User>>> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok().body(Result.success(userService.getUserById(id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Result<String>> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().body(Result.success("User deleted successfully"));
    }
}
