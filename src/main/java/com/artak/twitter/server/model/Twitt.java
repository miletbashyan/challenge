package com.artak.twitter.server.model;

import java.util.Date;

public class Twitt {

	private String message;
	private Date date;

	public Twitt(String message, Date date) {
		this.message = message;
		this.date = date;
	}

	public String getMessage() {
		return message;
	}

	public Date getDate() {
		return date;
	}

}
