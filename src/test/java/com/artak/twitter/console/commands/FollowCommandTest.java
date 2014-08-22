package com.artak.twitter.console.commands;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.artak.twitter.server.Twitter;

public class FollowCommandTest {

	FollowCommand followCommand = new FollowCommand();

	@Test
	public void matches() {
		boolean matches = followCommand.matches("Alice follows Bob");
		assertTrue(matches);
	}
	
	@Test
	public void validCommand() {
		followCommand.validateCommand("Alice follows Bob");
	}
	
	
	@Test(expected = UnsupportedOperationException.class)
	public void InvalidCommand() {
		followCommand.validateCommand("Alice invalid Bob");
		fail();
	}
	
	@Test
	public void matchesAfterTrimHeadingSpaces() {
		boolean matches = followCommand.matches(" Alice follows Bob");
		assertTrue(matches);
	}

	@Test
	public void matchesAfterTrimTailSpaces() {
		boolean matches = followCommand.matches("Alice follows Bob ");
		assertTrue(matches);
	}
	
	@Test
	public void doesNotMatch() {
		boolean matches = followCommand.matches("Alice follows Bob Alice");
		assertFalse(matches);
	}	
	
	@Test
	public void ececuteInitialized() {
		Command command = followCommand.newInstance("Alice follows Bob");
		Twitter twitter = new Twitter();
		command.execute(twitter);
		
		List<String> usersToFollow = twitter.getUsersToFollow("Alice");
		assertTrue(usersToFollow.contains("Bob"));
	}
	
}
