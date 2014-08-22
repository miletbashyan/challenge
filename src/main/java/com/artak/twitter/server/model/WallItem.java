package com.artak.twitter.server.model;

import java.util.Collections;
import java.util.List;

public class WallItem {
	private String user;
	private List<Twitt> timeLine;

	public WallItem(String user, List<Twitt> timeLine) {
		super();
		this.user = user;
		this.timeLine = Collections.unmodifiableList(timeLine);
	}

	public String getUser() {
		return user;
	}

	public List<Twitt> getTimeLine() {
		return timeLine;
	}

}
