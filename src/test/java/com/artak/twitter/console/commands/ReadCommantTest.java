package com.artak.twitter.console.commands;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Test;

import com.artak.twitter.console.commands.ReadCommand;
import com.artak.twitter.server.Twitter;

public class ReadCommantTest {

	private ReadCommand readCommand = new ReadCommand();

	@Test
	public void matches() {
		boolean matches = readCommand.matches("Alice");
		assertTrue(matches);
	}

	@Test
	public void validCommand() {
		readCommand.validateCommand("Alice");
	}

	@Test(expected = UnsupportedOperationException.class)
	public void InvalidCommand() {
		readCommand.validateCommand("Alice invalid");
		fail();
	}

	@Test
	public void matchesAfterTrimHeadingSpaces() {
		boolean matches = readCommand.matches(" Alice");
		assertTrue(matches);
	}

	@Test
	public void matchesAfterTrimTailSpaces() {
		boolean matches = readCommand.matches("Alice ");
		assertTrue(matches);
	}

	@Test
	public void doesNotMatch() {
		boolean matches = readCommand.matches("Alice Alice");
		assertFalse(matches);
	}

	@Test
	public void ececuteInitialized() {
		Command command = readCommand.newInstance("Alice");
		Twitter twitter = new Twitter();
		twitter.post("Alice", "Message");

		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));

		command.execute(twitter);

		String outPut = new String(outContent.toByteArray());

		assertTrue(outPut.contains("Message"));
	}

}
