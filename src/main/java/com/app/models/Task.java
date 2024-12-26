package com.app.models;

import com.app.enums.TaskStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
@EntityListeners(AuditingEntityListener.class)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Task title is required")
	@Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters")
	@Column(nullable = false)
	private String title;

	@NotNull(message = "Status is required")
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private TaskStatus status;

	@DecimalMin(value = "0.0", message = "Progress cannot be less than 0")
	@DecimalMax(value = "100.0", message = "Progress cannot be more than 100")
	@Column(nullable = false)
	private float progress;

	@CreatedDate
	@Column(nullable = false, updatable = false)
	private LocalDateTime createdAt;

	@LastModifiedDate
	@Column(nullable = false)
	private LocalDateTime updatedAt;

	@Column(name = "finished_at")
	private LocalDateTime finishedAt;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "project_id", nullable = false)
	private Project project;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id", nullable = false)
	private TeamMember member;
}