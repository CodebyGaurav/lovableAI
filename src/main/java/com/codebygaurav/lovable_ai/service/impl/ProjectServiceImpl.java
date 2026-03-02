package com.codebygaurav.lovable_ai.service.impl;

import com.codebygaurav.lovable_ai.dto.project.ProjectRequest;
import com.codebygaurav.lovable_ai.dto.project.ProjectResponse;
import com.codebygaurav.lovable_ai.dto.project.ProjectSummaryResponse;
import com.codebygaurav.lovable_ai.entity.Project;
import com.codebygaurav.lovable_ai.entity.ProjectMember;
import com.codebygaurav.lovable_ai.entity.ProjectMemberId;
import com.codebygaurav.lovable_ai.entity.User;
import com.codebygaurav.lovable_ai.enums.ProjectRole;
import com.codebygaurav.lovable_ai.error.ResourceNotFoundException;
import com.codebygaurav.lovable_ai.mapper.ProjectMapper;
import com.codebygaurav.lovable_ai.repository.ProjectMemberRepository;
import com.codebygaurav.lovable_ai.repository.ProjectRepository;
import com.codebygaurav.lovable_ai.repository.UserRepository;
import com.codebygaurav.lovable_ai.service.ProjectService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Transactional
public class ProjectServiceImpl implements ProjectService {

    ProjectRepository projectRepository;
    UserRepository userRepository;
    ProjectMapper projectMapper;
    ProjectMemberRepository projectMemberRepository;

    @Override
    public ProjectResponse createProject(ProjectRequest projectRequest, Long userId) {
        User owner = userRepository.findById(userId).orElseThrow(
                ()->new ResourceNotFoundException("User", userId.toString())
        );
        Project project = Project.builder()
                .name(projectRequest.name())
                .isPublic(false)
                .build();
        project = projectRepository.save(project);
        ProjectMemberId projectMemberId= new ProjectMemberId(project.getId(), owner.getId());
        ProjectMember projectMember = ProjectMember.builder()
                .id(projectMemberId)
                .projectRole(ProjectRole.OWNER)
                .user(owner)
                .acceptedAt(Instant.now())
                .invitedAt(Instant.now())
                .project(project)
                .build();

        projectMemberRepository.save(projectMember);

        return projectMapper.toProjectResponse(project);
    }

    @Override
    public List<ProjectSummaryResponse> getUserProjects(Long userId) {

        var projects = projectRepository.findAllAccessibleByUser(userId);
        return projectMapper.toListOfProjectSummaryResponse(projects);
    }

    @Override
    public ProjectResponse getUserProjectById(Long id, Long userId) {
        Project project = getAccessibleProjectById(id, userId);
        return projectMapper.toProjectResponse(project);
    }

    @Override
    public ProjectResponse updateProject(Long id, ProjectRequest request, Long userId) {
        Project project = getAccessibleProjectById(id, userId);
        project.setName(request.name());
        project = projectRepository.save(project);
        return projectMapper.toProjectResponse(project);
    }

    @Override
    public void softDelete(Long id, Long userId) {
        Project project = getAccessibleProjectById(id, userId);
        project.setDeletedAt(Instant.now());
        projectRepository.save(project);
    }

    /*INTERNAL METHOD*/
    public Project getAccessibleProjectById(Long id, Long userId){
        return projectRepository.findAccessibleProjectById(id, userId)
                .orElseThrow(()-> new ResourceNotFoundException("Project", id.toString()));
    }
}
