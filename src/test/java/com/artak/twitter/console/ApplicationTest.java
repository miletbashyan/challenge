package com.artak.twitter.console;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ApplicationTest {
	private Application app = new Application();

	@Before
	public void setTestMode() {
		app.setTestMode(true);
	}

	@Test
	public void start() {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));

		ByteArrayInputStream inContent = new ByteArrayInputStream(
				" ".getBytes());
		System.setIn(inContent);
		
		app.start();
		String outPut = new String(outContent.toByteArray());

		boolean exitHintPrinted = outPut.contains("Type Bye to exit twitter");
		boolean promptPrinted = outPut.contains(">");
		assertTrue(exitHintPrinted && promptPrinted);
	}

	@Test
	public void exit() {
		ByteArrayInputStream inContent = new ByteArrayInputStream(
				"Bye".getBytes());
		System.setIn(inContent);

		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));

		app.setTestMode(false);
		app.start();
		String outPut = new String(outContent.toByteArray());
		assertTrue(outPut.contains("See you later!"));
	}

	
	@Test
	public void post() {
		ByteArrayInputStream inContent = new ByteArrayInputStream(
				"Alice -> Message1".getBytes());
		System.setIn(inContent);

		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));

		app.start();
		String outPut = new String(outContent.toByteArray());
		assertTrue(outPut.endsWith(">"));
	}
	
	@Test
	public void read() {
		writeToSysIn("Alice -> Message1");
		
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		
		app.start();

		writeToSysIn("Alice");		
		app.start();
		
		String outPut = new String(outContent.toByteArray());
		assertTrue(outPut.contains("Message1"));
	}
	
	@Test
	public void follows() {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		writeToSysIn("Alice follows Bob");
		app.start();
		List<String> usersToFollow = app.twitter.getUsersToFollow("Alice");
		
		assertTrue(usersToFollow.contains("Bob"));
	}
	
	@Test
	public void wall() {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		app.twitter.post("Bob", "Message from Bob");
		app.twitter.follow("Alice", "Bob");
		writeToSysIn("Alice wall");
		app.start();
		
		String outPut = new String(outContent.toByteArray());
		assertTrue(outPut.contains("Message from Bob"));
	}

	private void writeToSysIn(String string) {
		ByteArrayInputStream inContent;
		inContent = new ByteArrayInputStream(string.getBytes());
		System.setIn(inContent);
	}	
	
	
}
