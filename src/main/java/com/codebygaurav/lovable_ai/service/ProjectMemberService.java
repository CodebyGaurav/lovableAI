package com.codebygaurav.lovable_ai.service;

import com.codebygaurav.lovable_ai.dto.member.InviteMemberRequest;
import com.codebygaurav.lovable_ai.dto.member.MemberResponse;
import com.codebygaurav.lovable_ai.dto.member.UpdateMemberRoleRequest;

import java.util.List;

public interface ProjectMemberService {
     List<MemberResponse> getProjectMembers(Long projectId);
     MemberResponse inviteMember(Long projectId, InviteMemberRequest request);
     MemberResponse updateMemberRole(Long projectId, Long memberId, UpdateMemberRoleRequest request);
     void removeProjectMember(Long projectId, Long memberId);
}
