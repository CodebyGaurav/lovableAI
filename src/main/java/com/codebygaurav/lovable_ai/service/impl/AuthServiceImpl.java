package com.codebygaurav.lovable_ai.service.impl;

import com.codebygaurav.lovable_ai.dto.auth.AuthResponse;
import com.codebygaurav.lovable_ai.dto.auth.LoginRequest;
import com.codebygaurav.lovable_ai.dto.auth.SignupRequest;
import com.codebygaurav.lovable_ai.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Override
    public AuthResponse signup(SignupRequest signupRequest) {
        return null;
    }

    @Override
    public AuthResponse login(LoginRequest loginRequest) {
        return null;
    }
}
