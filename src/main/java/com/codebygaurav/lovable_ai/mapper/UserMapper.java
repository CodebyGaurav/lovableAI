package com.codebygaurav.lovable_ai.mapper;

import com.codebygaurav.lovable_ai.dto.auth.SignupRequest;
import com.codebygaurav.lovable_ai.dto.auth.UserProfileResponse;
import com.codebygaurav.lovable_ai.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(SignupRequest signupRequest);
    UserProfileResponse toUserProfileResponse(User user);
}
