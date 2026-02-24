package com.codebygaurav.lovable_ai.service;

import com.codebygaurav.lovable_ai.dto.project.FileContentResponse;
import com.codebygaurav.lovable_ai.dto.project.FileNode;

import java.util.List;

public interface FileService {
    List<FileNode> getFileTree(Long projectId, Long userId);

    FileContentResponse getFileContent(Long projectId, String path, Long userId);
}
