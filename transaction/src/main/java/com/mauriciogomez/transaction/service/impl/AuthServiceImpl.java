package com.mauriciogomez.transaction.service.impl;

import com.mauriciogomez.transaction.dto.LoginRequest;
import com.mauriciogomez.transaction.entity.User;
import com.mauriciogomez.transaction.repository.UserRepository;
import com.mauriciogomez.transaction.service.AuthService;
import com.mauriciogomez.transaction.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String login(LoginRequest request) {

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Credenciales invalidas");
        }
        return JwtUtil.generateToken(user.getUsername());
    }

    @Override
    public User createUser(String username, String rawPassword) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(rawPassword));
        return userRepository.save(user);
    }
}
