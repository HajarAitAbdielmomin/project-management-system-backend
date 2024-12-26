package com.app.models;

import jakarta.persistence.*;

import lombok.*;

import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.*;

@Entity
@DiscriminatorValue("PROJECT_MANAGER")
@EntityListeners(AuditingEntityListener.class)
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectManager extends User {
	@OneToMany(mappedBy = "projectManager",
			cascade = {CascadeType.PERSIST, CascadeType.MERGE},
			fetch = FetchType.LAZY)
	private Set<Team> teams = new HashSet<>();

	@OneToMany(mappedBy = "projectManager",
			cascade = {CascadeType.PERSIST, CascadeType.MERGE},
			fetch = FetchType.LAZY)
	private Set<Project> projects = new HashSet<>();
}