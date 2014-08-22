package com.artak.twitter.server;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Test;

import com.artak.twitter.server.model.Twitt;
import com.artak.twitter.server.model.WallItem;

public class TwitterTest {

	@Test
	public void post() {
		Twitter twitter = new Twitter();
		String user = "Alice";
		twitter.post(user, "A message");
		List<Twitt> twitts = twitter.getTimeLine(user);
		String actualMessage = twitts.get(0).getMessage();
		assertEquals("A message", actualMessage);
	}

	@Test
	public void readUsersEptyTimeLine() {
		Twitter twitter = new Twitter();
		String user = "Alice";
		List<Twitt> twitts = twitter.getTimeLine(user);
		assertEquals(0, twitts.size());
	}

	@Test
	public void readUsersNonEptyTimeLine() {
		Twitter twitter = new Twitter();
		String user = "Alice";
		twitter.post(user, "Message");
		List<Twitt> twitts = twitter.getTimeLine(user);
		String actualMessage = twitts.get(0).getMessage();
		assertEquals("Message", actualMessage);
	}

	@Test
	public void timeLineOrder() {
		Twitter twitter = new Twitter();
		String user = "Alice";
		twitter.post(user, "Message1");
		twitter.post(user, "Message2");
		List<Twitt> twitts = twitter.getTimeLine(user);
		String olderMessage = twitts.get(0).getMessage();
		String lastMessage = twitts.get(1).getMessage();

		assertEquals("Message1", olderMessage);
		assertEquals("Message2", lastMessage);
	}

	@Test
	public void messageTimes() {
		Twitter twitter = new Twitter();
		Calendar calendar = new GregorianCalendar();
		twitter.setCalendar(calendar);
		String user = "Alice";
		twitter.setCalendar(calendar);
		twitter.post(user, "Message1");
		calendar.add(Calendar.MINUTE, 1);
		twitter.setCalendar(calendar);
		twitter.post(user, "Message2");
		calendar.add(Calendar.MINUTE, 1);
		twitter.setCalendar(calendar);

		List<Twitt> twitts = twitter.getTimeLine(user);

		Date firstTwittTime = twitts.get(0).getDate();
		Date lastTwittTime = twitts.get(1).getDate();

		long minutesPastSinceFirstTwitt = (calendar.getTimeInMillis() - firstTwittTime
				.getTime()) / 60000;
		long minutesPastSinceLastTwitt = (calendar.getTimeInMillis() - lastTwittTime
				.getTime()) / 60000;

		assertEquals(1, minutesPastSinceLastTwitt);
		assertEquals(2, minutesPastSinceFirstTwitt);
	}

	@Test
	public void followTheUser() {
		Twitter twitter = new Twitter();
		twitter.follow("Charlie", "Alice");
		twitter.follow("Charlie", "Bob");

		List<String> usersToFollow = twitter.getUsersToFollow("Charlie");
		boolean followingBobAndAlice = usersToFollow.contains("Alice")
				&& (usersToFollow.contains("Bob"));
		assertTrue(followingBobAndAlice);
	}

	@Test
	public void wallSize() {
		Twitter twitter = new Twitter();
		twitter.follow("Charlie", "Alice");
		twitter.follow("Charlie", "Bob");
		List<WallItem> wallItems = twitter.getWall("Charlie");

		assertEquals(3, wallItems.size());
	}
}
