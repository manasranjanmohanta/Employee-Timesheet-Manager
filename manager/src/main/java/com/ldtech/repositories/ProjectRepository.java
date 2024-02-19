package com.ldtech.repositories;

import com.ldtech.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, String> {
    // You can add custom query methods here if needed
}
