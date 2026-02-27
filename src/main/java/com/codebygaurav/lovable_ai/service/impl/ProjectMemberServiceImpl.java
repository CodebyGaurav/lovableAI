package com.codebygaurav.lovable_ai.service.impl;

import com.codebygaurav.lovable_ai.dto.member.InviteMemberRequest;
import com.codebygaurav.lovable_ai.dto.member.MemberResponse;
import com.codebygaurav.lovable_ai.dto.member.UpdateMemberRoleRequest;
import com.codebygaurav.lovable_ai.entity.Project;
import com.codebygaurav.lovable_ai.entity.ProjectMember;
import com.codebygaurav.lovable_ai.entity.ProjectMemberId;
import com.codebygaurav.lovable_ai.entity.User;
import com.codebygaurav.lovable_ai.mapper.ProjectMemberMapper;
import com.codebygaurav.lovable_ai.repository.ProjectMemberRepository;
import com.codebygaurav.lovable_ai.repository.ProjectRepository;
import com.codebygaurav.lovable_ai.repository.UserRepository;
import com.codebygaurav.lovable_ai.service.ProjectMemberService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Transactional
public class ProjectMemberServiceImpl implements ProjectMemberService {

    ProjectMemberRepository projectMemberRepository;
    ProjectRepository projectRepository;
    ProjectMemberMapper projectMemberMapper;
    UserRepository userRepository;

    @Override
    public List<MemberResponse> getProjectMembers(Long projectId, Long userId) {
        Project project = getAccessibleProjectById(projectId, userId);

        List<MemberResponse> memberResponseList = new ArrayList<>();
        memberResponseList.add(projectMemberMapper.toProjectMemberResponseFromOwner(project.getOwner()));

        memberResponseList.addAll(
                projectMemberRepository.findByIdProjectId(projectId)
                        .stream()
                        .map(projectMemberMapper::toProjectMemberResponseFromMember)
                        .toList());

        return memberResponseList;
    }

    @Override
    public MemberResponse inviteMember(Long projectId, InviteMemberRequest request, Long userId) {
        Project project = getAccessibleProjectById(projectId, userId);
        if (!project.getOwner().getId().equals(userId)) {
            throw new RuntimeException("Not Allowed");
        }

        User invitee = userRepository.findByEmail(request.email()).orElseThrow();
        if (invitee.getId().equals(userId)) {
            throw new RuntimeException("Cannot invite yourself");
        }
        ProjectMemberId projectMemberId = new ProjectMemberId(projectId, invitee.getId());
        if (projectMemberRepository.existsById(projectMemberId)) {
            throw new RuntimeException("Cannot invite once again");
        }

        ProjectMember member = ProjectMember.builder()
                .id(projectMemberId)
                .project(project)
                .user(invitee)
                .projectRole(request.role())
                .invitedAt(Instant.now())
                .build();

        projectMemberRepository.save(member);
        return projectMemberMapper.toProjectMemberResponseFromMember(member);
    }

    @Override
    public MemberResponse updateMemberRole(Long projectId, Long memberId, UpdateMemberRoleRequest request, Long userId) {
        Project project = getAccessibleProjectById(projectId, userId);
        if (!project.getOwner().getId().equals(userId)) {
            throw new RuntimeException("Not Allowed");
        }

        ProjectMemberId projectMemberId = new ProjectMemberId(projectId,memberId);
        ProjectMember projectMember = projectMemberRepository.findById(projectMemberId).orElseThrow();
        projectMember.setProjectRole(request.role());

        projectMemberRepository.save(projectMember);

        return projectMemberMapper.toProjectMemberResponseFromMember(projectMember);
    }

    @Override
    public void removeProjectMember(Long projectId, Long memberId, Long userId) {
        Project project = getAccessibleProjectById(projectId, userId);
        if (!project.getOwner().getId().equals(userId)) {
            throw new RuntimeException("Not Allowed");
        }
        ProjectMemberId projectMemberId = new ProjectMemberId(projectId,memberId);
        if (!projectMemberRepository.existsById(projectMemberId)) {
            throw new RuntimeException("Member not found in project");
        }

        projectMemberRepository.deleteById(projectMemberId);

    }

    /*INTERNAL METHOD*/
    public Project getAccessibleProjectById(Long id, Long userId) {
        return projectRepository.findAccessibleProjectById(id, userId).orElseThrow();
    }
}
