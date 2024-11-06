package com.app.model;

import java.util.ArrayList;
import java.util.Date;

public class Message {
	private long id;
	private String messageText;
	private Date sentAt;
	private Date updatedAt;
	public ArrayList<File> files = new ArrayList<File>();
	public Conversation contains;
}