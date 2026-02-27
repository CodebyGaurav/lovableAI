package com.codebygaurav.lovable_ai.service.impl;

import com.codebygaurav.lovable_ai.dto.project.ProjectRequest;
import com.codebygaurav.lovable_ai.dto.project.ProjectResponse;
import com.codebygaurav.lovable_ai.dto.project.ProjectSummayResponse;
import com.codebygaurav.lovable_ai.entity.Project;
import com.codebygaurav.lovable_ai.entity.User;
import com.codebygaurav.lovable_ai.mapper.ProjectMapper;
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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Transactional
public class ProjectServiceImpl implements ProjectService {

    ProjectRepository projectRepository;
    UserRepository userRepository;
    ProjectMapper projectMapper;

    @Override
    public ProjectResponse createProject(ProjectRequest projectRequest, Long userId) {
        User owner = userRepository.findById(userId).orElseThrow();
        Project project = Project.builder()
                .name(projectRequest.name())
                .owner(owner)
                .isPublic(false)
                .build();

        project = projectRepository.save(project);

        return projectMapper.toProjectResponse(project);
    }

    @Override
    public List<ProjectSummayResponse> getUserProjects(Long userId) {

//        return projectRepository.findAllAccessibleByUser(userId)
//                .stream()
//                .map(projectMapper::toProjectSummayResponse)
//                .collect(Collectors.toList());

        var projects = projectRepository.findAllAccessibleByUser(userId);
        return projectMapper.toListOfProjectSummayResponse(projects);
    }

    @Override
    public ProjectResponse getUserProjectById(Long id, Long userId) {
        Project project = getAccessibleProjectById(id, userId);
        return projectMapper.toProjectResponse(project);
    }

    @Override
    public ProjectResponse updateProject(Long id, ProjectRequest request, Long userId) {
        Project project = getAccessibleProjectById(id, userId);
        if (!project.getOwner().getId().equals(userId)) {
            throw new RuntimeException("You are not allowed to update the name");
        }
        project.setName(request.name());
        project = projectRepository.save(project);
        return projectMapper.toProjectResponse(project);
    }

    @Override
    public void softDelete(Long id, Long userId) {
        Project project = getAccessibleProjectById(id, userId);
        if (!project.getOwner().getId().equals(userId)) {
            throw new RuntimeException("You are not allowed to delete");
        }
        project.setDeletedAt(Instant.now());
        projectRepository.save(project);
    }

    /*INTERNAL METHOD*/
    public Project getAccessibleProjectById(Long id, Long userId){
        return projectRepository.findAccessibleProjectById(id, userId).orElseThrow();
    }
}
