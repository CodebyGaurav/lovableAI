package com.codebygaurav.lovable_ai.dto.auth;

public record UserProfileResponse(
    Long id,
    String email,
    String name,
    String avatarUrl
) {
}
