package com.artak.twitter.console.commands;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.artak.twitter.console.commands.PostCommand;
import com.artak.twitter.server.Twitter;
import com.artak.twitter.server.model.Twitt;

public class PostCommandTest {
	private PostCommand postCommand = new PostCommand();

	@Test
	public void matches() {
		boolean matches = postCommand.matches("Alice -> Message");
		assertTrue(matches);
	}

	@Test
	public void validCommand() {
		postCommand.validateCommand("Alice -> Message");
	}

	@Test(expected = UnsupportedOperationException.class)
	public void InvalidCommand() {
		postCommand.validateCommand("Alice invalid Bob");
		fail();
	}

	@Test
	public void matchesAfterTrimHeadingSpaces() {
		boolean matches = postCommand.matches("  Alice -> Message");
		assertTrue(matches);
	}

	@Test
	public void matchesAfterTrimTailSpaces() {
		boolean matches = postCommand.matches("Alice -> Message ");
		assertTrue(matches);
	}

	@Test
	public void doesNotMatch() {
		boolean matches = postCommand.matches("Alice Alice");
		assertFalse(matches);
	}

	@Test
	public void ececuteInitialized() {
		Command command = postCommand.newInstance("Alice -> Message");
		Twitter twitter = new Twitter();
		command.execute(twitter);

		List<Twitt> timeLine = twitter.getTimeLine("Alice");
		String message = timeLine.get(0).getMessage();
		assertEquals("Message", message);
	}
}
