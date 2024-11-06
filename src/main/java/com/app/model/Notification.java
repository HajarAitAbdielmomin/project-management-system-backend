package com.app.model;

import java.util.ArrayList;
import java.util.Date;

public class Notification {
	private long id;
	private String text;
	private Date createdAt;
	public ArrayList<User> users = new ArrayList<User>();
}