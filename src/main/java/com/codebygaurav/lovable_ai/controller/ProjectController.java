package com.codebygaurav.lovable_ai.controller;

import com.codebygaurav.lovable_ai.dto.project.ProjectRequest;
import com.codebygaurav.lovable_ai.dto.project.ProjectResponse;
import com.codebygaurav.lovable_ai.dto.project.ProjectSummaryResponse;
import com.codebygaurav.lovable_ai.service.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping
    public ResponseEntity<List<ProjectSummaryResponse>> getMyProjects(){
        Long userId = 1L;
        return ResponseEntity.ok(projectService.getUserProjects(userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponse> getProjectById(@PathVariable Long id){
        Long userId = 1L;

        return ResponseEntity.ok(projectService.getUserProjectById(id,userId));
    }

    @PostMapping
    public ResponseEntity<ProjectResponse> createProject(@RequestBody @Valid ProjectRequest projectRequest){
        Long userId = 1L;
        return ResponseEntity.status(HttpStatus.CREATED).body(projectService.createProject(projectRequest,userId));
    }

    @PatchMapping("{id}")
    public ResponseEntity<ProjectResponse> updateProject(@PathVariable Long id, @RequestBody @Valid ProjectRequest request) {
        Long userId = 1L;
        return ResponseEntity.ok(projectService.updateProject(id, request, userId));

    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id){
        Long userId = 1L;
        projectService.softDelete(id, userId);
        return ResponseEntity.noContent().build();

    }
}
