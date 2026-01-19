package com.app.repository;

import com.app.models.Backlog;
import com.app.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BacklogRepository extends JpaRepository<Backlog, Long> {
    List<Backlog> findBacklogsByProject(Project project);
}
