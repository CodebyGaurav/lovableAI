package com.codebygaurav.lovable_ai.dto.member;

import com.codebygaurav.lovable_ai.enums.ProjectRole;

public record UpdateMemberRoleRequest(
        ProjectRole role
) {
}
