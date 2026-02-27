package com.codebygaurav.lovable_ai.service;

import com.codebygaurav.lovable_ai.dto.member.InviteMemberRequest;
import com.codebygaurav.lovable_ai.dto.member.MemberResponse;
import com.codebygaurav.lovable_ai.dto.member.UpdateMemberRoleRequest;

import java.util.List;

public interface ProjectMemberService {
     List<MemberResponse> getProjectMembers(Long projectId, Long userId);
     MemberResponse inviteMember(Long projectId, InviteMemberRequest request, Long userId);
     MemberResponse updateMemberRole(Long projectId, Long memberId, UpdateMemberRoleRequest request, Long userId);
     void removeProjectMember(Long projectId, Long memberId, Long userId);
}
