package com.cnu.real_coding_server.service;

import com.cnu.real_coding_server.entity.Project;
import com.cnu.real_coding_server.model.request.ProjectRequest;
import com.cnu.real_coding_server.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;

    public void save(ProjectRequest projectRequest) {
        projectRepository.save(projectRequest.toEntity());
    }

    public Project findById(Integer projectId) {
        return getProject(projectId);
    }

    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    public void edit(Integer projectId, ProjectRequest projectRequest) {
        Project project = getProject(projectId);

        project.setTitle(projectRequest.getTitle());
        project.setDescription(projectRequest.getDescription());
        project.setSummary(projectRequest.getSummary());
        project.setEndDate(projectRequest.getEndDate());
        project.setStartDate(projectRequest.getStartDate());
        project.setIsInProgress(projectRequest.getIsInProgress());
    }

    public void delete(Integer projectId) {
        projectRepository.findById(projectId).ifPresent(projectRepository::delete);
    }

    private Project getProject(Integer projectId) {
        return projectRepository.findById(projectId).stream().findAny()
                .orElseThrow(() -> new RuntimeException("포스트가 존재하지 않습니다."));
    }

}
