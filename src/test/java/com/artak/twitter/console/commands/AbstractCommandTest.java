package com.artak.twitter.console.commands;

import static org.junit.Assert.*;
import org.junit.Test;

public class AbstractCommandTest {
	AbstractCommand command = new PostCommand();

	@Test(expected = IllegalStateException.class)
	public void ececuteUninitialized() {
		command.execute(null);
	}

	@Test
	public void getFirstUser() {
		String user = command.parseFirstArgument("Alice follows Bob");
		assertEquals("Alice", user);
	}

	@Test
	public void getSecond() {
		String user = command.parseSecondArgument("Alice follows Bob");
		assertEquals("Bob", user);
	}
}
