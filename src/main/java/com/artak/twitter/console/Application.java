package com.artak.twitter.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.artak.twitter.console.commands.CommandResolver;
import com.artak.twitter.server.Twitter;

public class Application {

	private static final String PROMPT_LINE = ">";
	private static final String FAREWELL_MESSAGE = "See you later!";
	private static final String EXIT_COMMAND_HINT = "Type Bye to exit twitter";
	private static final String EXIT_COMMAND = "Bye";
	private boolean testMode = false;
	Twitter twitter = new Twitter();

	public static void main(String[] args) {
		Application application = new Application();
		application.start();
	}

	public void start() {
		try {
			loop();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	private void loop() throws IOException {
		System.out.println(EXIT_COMMAND_HINT);
		boolean finished = false;
		CommandResolver commandResolver = new CommandResolver();
		do {
			System.out.print(PROMPT_LINE);
			String command;
			command = readLine();

			finished = command.equals(EXIT_COMMAND);
			if (finished) {
				System.out.println(FAREWELL_MESSAGE);
			}
			try {
				commandResolver.resolve(command).execute(twitter);
			} catch (UnsupportedOperationException e) {
				System.out.println("Invalid command");
			}
		} while (!testMode && !finished);

	}

	private String readLine() throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));
		return reader.readLine();
	}

	void setTestMode(boolean testMode) {
		this.testMode = testMode;
	}
}
