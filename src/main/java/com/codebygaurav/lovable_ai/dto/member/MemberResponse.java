package com.codebygaurav.lovable_ai.dto.member;

import com.codebygaurav.lovable_ai.enums.ProjectRole;

import java.time.Instant;

public record MemberResponse(
        Long userId,
        String username,
        String name,
        ProjectRole projectRole,
        Instant invitedAt
) {
}
