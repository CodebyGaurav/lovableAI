package com.codebygaurav.lovable_ai.service.impl;

import com.codebygaurav.lovable_ai.dto.auth.AuthResponse;
import com.codebygaurav.lovable_ai.dto.auth.LoginRequest;
import com.codebygaurav.lovable_ai.dto.auth.SignupRequest;
import com.codebygaurav.lovable_ai.entity.User;
import com.codebygaurav.lovable_ai.error.BadRequestException;
import com.codebygaurav.lovable_ai.mapper.UserMapper;
import com.codebygaurav.lovable_ai.repository.UserRepository;
import com.codebygaurav.lovable_ai.security.AuthUtil;
import com.codebygaurav.lovable_ai.service.AuthService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AuthServiceImpl implements AuthService {

    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;
    AuthUtil authUtil;
    AuthenticationManager authenticationManager;

    @Override
    public AuthResponse signup(SignupRequest signupRequest) {
        userRepository.findByUsername(signupRequest.username()).ifPresent(user ->{
            throw new BadRequestException("User already exists with username:"+signupRequest.username());
        });

        User user= userMapper.toEntity(signupRequest);
        user.setPassword(passwordEncoder.encode(signupRequest.password()));
        user = userRepository.save(user);
        String token = authUtil.generateAccessToken(user);
        return new AuthResponse(token,userMapper.toUserProfileResponse(user));
    }

    @Override
    public AuthResponse login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password())
        );

        User user = (User) authentication.getPrincipal();
        String token = authUtil.generateAccessToken(user);

        return new AuthResponse(token,userMapper.toUserProfileResponse(user));
    }
}
