package com.app.repository;

import com.app.models.Backlog;
import com.app.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BacklogRepository extends JpaRepository<Backlog, Long> {
    List<Backlog> findBacklogsByProjectId(Long id);
    boolean existsBacklogByProject_IdAndTitle(Long projectId, String title);
    boolean existsBacklogByProject_IdAndTitleAndIdNot(Long projectId, String title, Long id);
    boolean existsByIdAndProject_Team_Members_Id(Long backlogId, Long memberId);

}
