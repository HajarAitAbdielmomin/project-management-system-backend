package com.app.model;

import java.util.Date;

public class Task {
	private long id;
	private String title;
	private String status;
	private float progress;
	private Date createdAt;
	private Date updatedAt;
	private Date finishedAt;
	public Project project;
	public TeamMember member;
}