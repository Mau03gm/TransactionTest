package com.mauriciogomez.transaction.service;

import com.mauriciogomez.transaction.dto.LoginRequest;
import com.mauriciogomez.transaction.entity.User;

public interface AuthService {
    public String login(LoginRequest request);
    public User createUser(String username, String rawPassword);
}
