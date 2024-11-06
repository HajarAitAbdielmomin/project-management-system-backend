package com.app.model;

import java.util.ArrayList;

public class Team {
	private long id;
	private String name;
	public ProjectManager projectManager;
	public ArrayList<TeamMember> members = new ArrayList<TeamMember>();
	public ArrayList<Project> projects = new ArrayList<Project>();
}