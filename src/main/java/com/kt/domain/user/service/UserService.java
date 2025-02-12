package com.kt.domain.user.service;

import java.util.List;
import java.util.Optional;

import com.kt.domain.user.entity.User;

public interface UserService {

    public User saveUser(User user);

    public List<User> getAllUsers();

    public Optional<User> getUserById(Long id);

    public void deleteUser(Long id);
}
