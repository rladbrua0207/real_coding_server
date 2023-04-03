package com.cnu.real_coding_server.controller;

import com.cnu.real_coding_server.entity.Project;
import com.cnu.real_coding_server.model.request.ProjectRequest;
import com.cnu.real_coding_server.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;

    @PostMapping("project")
    public ResponseEntity<?> save(
            @RequestBody ProjectRequest projectRequest
    ) {
        projectService.save(projectRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("project")
    public ResponseEntity<?> findAllProject() {
        List<Project> projectList = projectService.findAll();
        return new ResponseEntity<>(projectList, HttpStatus.OK);
    }

    @GetMapping("project/{projectId}")
    public ResponseEntity<?> findProjectById(@PathVariable Integer projectId) {
        Project project = projectService.findById(projectId);
        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    @PutMapping("project/{projectId}")
    public ResponseEntity<?> editProjectPyId(
            @PathVariable Integer projectId,
            @RequestBody ProjectRequest projectRequest) {
        projectService.edit(projectId, projectRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("project/{projectId}")
    public ResponseEntity<?> editProjectPyId(
            @PathVariable Integer projectId) {
        projectService.delete(projectId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
