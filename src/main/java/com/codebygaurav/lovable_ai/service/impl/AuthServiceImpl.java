package com.codebygaurav.lovable_ai.service.impl;

import com.codebygaurav.lovable_ai.dto.auth.AuthResponse;
import com.codebygaurav.lovable_ai.dto.auth.LoginRequest;
import com.codebygaurav.lovable_ai.dto.auth.SignupRequest;
import com.codebygaurav.lovable_ai.entity.User;
import com.codebygaurav.lovable_ai.error.BadRequestException;
import com.codebygaurav.lovable_ai.mapper.UserMapper;
import com.codebygaurav.lovable_ai.repository.UserRepository;
import com.codebygaurav.lovable_ai.service.AuthService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AuthServiceImpl implements AuthService {

    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;

    @Override
    public AuthResponse signup(SignupRequest signupRequest) {
        userRepository.findByUsername(signupRequest.username()).ifPresent(user ->{
            throw new BadRequestException("User already exists with username:"+signupRequest.username());
        });

        User user= userMapper.toEntity(signupRequest);
        user.setPassword(passwordEncoder.encode(signupRequest.password()));
        user = userRepository.save(user);

        return new AuthResponse("dummy",userMapper.toUserProfileResponse(user));
    }

    @Override
    public AuthResponse login(LoginRequest loginRequest) {
        return null;
    }
}
