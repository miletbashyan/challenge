package com.artak.twitter.server;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.artak.twitter.server.model.Twitt;
import com.artak.twitter.server.model.WallItem;

public class Twitter {
	private final Map<String, List<Twitt>> twitts = new ConcurrentHashMap<>();
	private final Map<String, List<String>> usersToFollow = new ConcurrentHashMap<>();
	private Calendar calendar = null;

	public List<Twitt> getTimeLine(String user) {
		List<Twitt> userTwitts = getUserTwitts(user);
		return Collections.unmodifiableList(userTwitts);
	}

	private List<Twitt> getUserTwitts(String user) {
		List<Twitt> userTwitts = twitts.get(user);
		if (userTwitts == null) {
			userTwitts = Collections.synchronizedList(new ArrayList<Twitt>());
		}
		return userTwitts;
	}

	public void post(String user, String message) {
		Twitt twitt = new Twitt(message, getTwittTime());
		List<Twitt> userTwitts = getUserTwitts(user);
		userTwitts.add(twitt);
		twitts.put(user, userTwitts);
	}

	private Date getTwittTime() {
		if (calendar == null) {
			return new Date();
		} else {
			return calendar.getTime();
		}
	}

	void setCalendar(Calendar calendar) {
		this.calendar = calendar;
	}

	public void follow(String follower, String userToFollow) {
		List<String> usersToFollowByFollower = getUsersToFollowList(follower);
		usersToFollowByFollower.add(userToFollow);
		usersToFollow.put(follower, usersToFollowByFollower);
	}

	public List<String> getUsersToFollow(String follower) {
		List<String> usersToFollowByFollower = getUsersToFollowList(follower);
		return Collections.unmodifiableList(usersToFollowByFollower);
	}

	private List<String> getUsersToFollowList(String follower) {
		List<String> usersToFollowByFollower = usersToFollow.get(follower);
		if (usersToFollowByFollower == null) {
			usersToFollowByFollower = Collections.synchronizedList(new ArrayList<String>());
		}
		return usersToFollowByFollower;
	}

	public List<WallItem> getWall(String user) {
		List<WallItem> wallItems = new ArrayList<WallItem>();
		addTwitts(user, wallItems);//Add own twitts
		addTwittsOfUsersToFollow(user, wallItems);
		return Collections.unmodifiableList(wallItems);
	}

	private void addTwittsOfUsersToFollow(String user, List<WallItem> wallItems) {
		List<String> usersToFollow = getUsersToFollow(user);

		for (String userToFollow : usersToFollow) {
			addTwitts(userToFollow, wallItems);
		}
	}

	private void addTwitts(String user, List<WallItem> wallItems) {
		wallItems.add(new WallItem(user, getTimeLine(user)));
	}

}
