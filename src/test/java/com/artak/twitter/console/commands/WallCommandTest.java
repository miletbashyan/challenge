package com.artak.twitter.console.commands;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Test;

import com.artak.twitter.console.commands.WallCommand;
import com.artak.twitter.server.Twitter;

public class WallCommandTest {

	private WallCommand wallCommand = new WallCommand();

	@Test
	public void matches() {
		boolean matches = wallCommand.matches("Alice wall");
		assertTrue(matches);
	}

	@Test
	public void validCommand() {
		wallCommand.validateCommand("Alice wall");
	}

	@Test(expected = UnsupportedOperationException.class)
	public void InvalidCommand() {
		wallCommand.validateCommand("Alice invalid");
		fail();
	}

	@Test
	public void matchesAfterTrimHeadingSpaces() {
		boolean matches = wallCommand.matches("  Alice wall");
		assertTrue(matches);
	}

	@Test
	public void matchesAfterTrimTailSpaces() {
		boolean matches = wallCommand.matches("Alice wall ");
		assertTrue(matches);
	}

	@Test
	public void doesNotMatch() {
		boolean matches = wallCommand.matches("Alice Alice");
		assertFalse(matches);
	}

	@Test
	public void ececuteInitialized() {
		Command command = wallCommand.newInstance("Alice wall");
		Twitter twitter = new Twitter();
		twitter.post("Alice", "Wall Message");

		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));

		command.execute(twitter);

		String outPut = new String(outContent.toByteArray());
		assertTrue(outPut.contains("Wall Message"));
	}

}
