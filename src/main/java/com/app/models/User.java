package com.app.models;

import jakarta.persistence.*;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import java.time.*;
import java.util.*;


@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type", discriminatorType = DiscriminatorType.STRING)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "First name is required")
	@Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
	@Column(nullable = false, length = 50)
	private String firstName;

	@NotBlank(message = "Last name is required")
	@Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
	@Column(nullable = false, length = 50)
	private String lastName;

	@NotBlank(message = "Email is required")
	@Email(message = "Please provide a valid email address")
	@Column(nullable = false, unique = true)
	private String email;

	@NotBlank(message = "Password is required")
	@Size(min = 8, message = "Password must be at least 8 characters long")
	@Column(nullable = false)
	@ToString.Exclude
	private String password;

	@CreatedDate
	@Column(nullable = false, updatable = false)
	private LocalDateTime createdAt;

	@LastModifiedDate
	@Column(nullable = false)
	private LocalDateTime updatedAt;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "notification_user",
			joinColumns = @JoinColumn(name = "notification_id"),
			inverseJoinColumns = @JoinColumn(name = "user_id")
	)
	private Set<Notification> notifications = new HashSet<>();

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(  name = "user_roles",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

}
