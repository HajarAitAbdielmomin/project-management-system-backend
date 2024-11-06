package com.app.model;

import java.util.ArrayList;

public class User {
	private long id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	public ArrayList<Notification> notifications = new ArrayList<Notification>();
	public ArrayList<Conversation> conversations = new ArrayList<Conversation>();
}