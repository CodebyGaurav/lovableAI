package com.codebygaurav.lovable_ai.mapper;

import com.codebygaurav.lovable_ai.dto.project.ProjectResponse;
import com.codebygaurav.lovable_ai.dto.project.ProjectSummayResponse;
import com.codebygaurav.lovable_ai.entity.Project;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    ProjectResponse toProjectResponse(Project project);
    ProjectSummayResponse toProjectSummayResponse(Project project);

    List<ProjectSummayResponse> toListOfProjectSummayResponse(List<Project> projects);
}
