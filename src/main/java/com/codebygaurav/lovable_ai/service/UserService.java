package com.codebygaurav.lovable_ai.service;

import com.codebygaurav.lovable_ai.dto.auth.UserProfileResponse;

public interface UserService {
    UserProfileResponse getProfile(Long userId);
}
