package com.codebygaurav.lovable_ai.service;

import com.codebygaurav.lovable_ai.dto.project.ProjectRequest;
import com.codebygaurav.lovable_ai.dto.project.ProjectResponse;
import com.codebygaurav.lovable_ai.dto.project.ProjectSummaryResponse;

import java.util.List;

public interface ProjectService {
    List<ProjectSummaryResponse> getUserProjects();

    ProjectResponse getUserProjectById(Long id);

    ProjectResponse createProject(ProjectRequest projectRequest);

    ProjectResponse updateProject(Long id, ProjectRequest request);

    void softDelete(Long id);
}
