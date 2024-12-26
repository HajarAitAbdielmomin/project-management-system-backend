package com.app.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.util.HashSet;
import java.util.Set;
import java.time.LocalDateTime;

@Entity
@Table(name = "projects")
@EntityListeners(AuditingEntityListener.class)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotBlank(message = "Project title is required")
	@Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters")
	@Column(nullable = false)
	private String title;

	@CreatedDate
	@Column(name = "created_at", nullable = false, updatable = false)
	private LocalDateTime createdAt;

	@LastModifiedDate
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	@Column(name = "finished_at")
	private LocalDateTime finishedAt;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "project_manager_id")
	private ProjectManager projectManager;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_owner_id")
	private ProductOwner productOwner;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "team_id", nullable = false)
	private Team team;

	@OneToMany(mappedBy = "project", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Task> tasks = new HashSet<>();
}