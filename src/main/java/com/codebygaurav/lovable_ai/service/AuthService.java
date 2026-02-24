package com.codebygaurav.lovable_ai.service;

import com.codebygaurav.lovable_ai.dto.auth.LoginRequest;
import com.codebygaurav.lovable_ai.dto.auth.SignupRequest;
import com.codebygaurav.lovable_ai.dto.auth.AuthResponse;

public interface AuthService {
    AuthResponse signup(SignupRequest signupRequest);

    AuthResponse login(LoginRequest loginRequest);
}
