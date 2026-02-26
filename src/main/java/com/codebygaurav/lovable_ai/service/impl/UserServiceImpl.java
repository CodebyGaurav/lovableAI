package com.codebygaurav.lovable_ai.service.impl;

import com.codebygaurav.lovable_ai.dto.auth.UserProfileResponse;
import com.codebygaurav.lovable_ai.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public UserProfileResponse getProfile(Long userId) {
        return null;
    }
}
