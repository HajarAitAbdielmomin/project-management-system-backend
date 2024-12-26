package com.app.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "teams")
@EntityListeners(AuditingEntityListener.class)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Team {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotBlank(message = "Team name is required")
	@Size(min = 2, max = 100, message = "Team name must be between 2 and 100 characters")
	@Column(nullable = false)
	private String name;

	@ManyToOne
	@JoinColumn(name = "project_manager_id")
	private ProjectManager projectManager;

	@OneToMany(mappedBy = "team",
			cascade = {CascadeType.PERSIST, CascadeType.MERGE},
			fetch = FetchType.LAZY)
	private List<TeamMember> members = new ArrayList<>();

	@OneToMany(mappedBy = "team",
			cascade = {CascadeType.PERSIST, CascadeType.MERGE},
			fetch = FetchType.LAZY)
	private List<Project> projects = new ArrayList<>();
}