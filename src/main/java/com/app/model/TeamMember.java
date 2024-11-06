package com.app.model;

import java.util.ArrayList;

public class TeamMember extends User {
	public Team team;
	public ArrayList<Task> tasks = new ArrayList<Task>();
}