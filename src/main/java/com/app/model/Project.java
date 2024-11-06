package com.app.model;

import java.util.ArrayList;
import java.util.Date;

public class Project {
	private long id;
	private String title;
	private Date createdAt;
	private Date updatedAt;
	private Date finishedAt;
	public ProjectManager projectManager;
	public Team team;
	public ArrayList<Task> tasks = new ArrayList<Task>();
}