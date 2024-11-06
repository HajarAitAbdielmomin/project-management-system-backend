package com.app.model;

import java.util.ArrayList;
import java.util.Date;

public class Conversation {
	private long id;
	private Date startDate;
	private Date status;
	private Date lastMessageAt;
	public ArrayList<User> participants= new ArrayList<User>();
	public ArrayList<Message> message = new ArrayList<Message>();
}