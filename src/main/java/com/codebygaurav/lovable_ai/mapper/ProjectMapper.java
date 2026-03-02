package com.codebygaurav.lovable_ai.mapper;

import com.codebygaurav.lovable_ai.dto.project.ProjectResponse;
import com.codebygaurav.lovable_ai.dto.project.ProjectSummaryResponse;
import com.codebygaurav.lovable_ai.entity.Project;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    ProjectResponse toProjectResponse(Project project);
    ProjectSummaryResponse toProjectSummaryResponse(Project project);

    List<ProjectSummaryResponse> toListOfProjectSummaryResponse(List<Project> projects);
}
