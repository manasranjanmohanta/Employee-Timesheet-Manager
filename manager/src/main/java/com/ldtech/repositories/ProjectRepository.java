package com.ldtech.repositories;

import com.ldtech.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, String> {
    List<Project> findProjectsByProjectManager(String projectManager);
    // You can add custom query methods here if needed
}
