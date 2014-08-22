package com.artak.twitter.console.commands;

import static org.junit.Assert.*;

import org.junit.Test;

public class CommandResolverTest {

	@Test
	public void resolveFollowCommand() {
		CommandResolver commandResolver = new CommandResolver();
		Command command = commandResolver.resolve("Alice follows Bob");
		
		assertTrue(command instanceof FollowCommand);
	}

	@Test
	public void resolveWallCommand() {
		CommandResolver commandResolver = new CommandResolver();
		Command command = commandResolver.resolve("Alice wall");
		
		assertTrue(command instanceof WallCommand);
	}

	@Test
	public void resolveReadCommand() {
		CommandResolver commandResolver = new CommandResolver();
		Command command = commandResolver.resolve("Alice");
		
		assertTrue(command instanceof ReadCommand);
	}
	
	@Test
	public void resolvePostCommand() {
		CommandResolver commandResolver = new CommandResolver();
		Command command = commandResolver.resolve("Alice -> Message");
		
		assertTrue(command instanceof PostCommand);
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void unsupportedCommand() {
		CommandResolver commandResolver = new CommandResolver();
		commandResolver.resolve("Alice Alicee");
	}
	

}
