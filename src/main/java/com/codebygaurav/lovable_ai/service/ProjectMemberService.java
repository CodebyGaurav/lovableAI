package com.codebygaurav.lovable_ai.service;

import com.codebygaurav.lovable_ai.dto.member.InviteMemberRequest;
import com.codebygaurav.lovable_ai.dto.member.MemberResponse;
import com.codebygaurav.lovable_ai.entity.ProjectMember;
import org.jspecify.annotations.Nullable;

import java.util.List;

public interface ProjectMemberService {
     List<ProjectMember> getProjectMembers(Long projectId, Long userId);
     MemberResponse inviteMember(Long projectId, InviteMemberRequest request, Long userId);
     MemberResponse updateMemberRole(Long projectId, Long memberId, InviteMemberRequest request, Long userId);
     MemberResponse deleteProjectMember(Long projectId, Long memberId, Long userId);
}
