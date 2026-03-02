package com.codebygaurav.lovable_ai.dto.member;

import com.codebygaurav.lovable_ai.enums.ProjectRole;
import jakarta.validation.constraints.NotNull;

public record UpdateMemberRoleRequest(
        @NotNull ProjectRole role
) {
}
