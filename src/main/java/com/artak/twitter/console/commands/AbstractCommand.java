package com.artak.twitter.console.commands;

import com.artak.twitter.server.Twitter;

public abstract class AbstractCommand implements Command {
	private boolean initialized = false;

	@Override
	public boolean matches(String commandLine) {
		return commandLine.trim().matches(getCommandPattern());
	}

	@Override
	public void execute(Twitter twitter) {
		if (!initialized) {
			throw new IllegalStateException(
					"Command is not initialized! Try using newInstance method to create an initialized instnace.");
		}
		executeConcrete(twitter);
	};

	protected void initialize() {
		initialized = true;
	}

	protected void validateCommand(String commandLine) {
		if (!matches(commandLine)) {
			throw new UnsupportedOperationException("Invalid command: \""
					+ commandLine + "\"");
		}
	}

	protected abstract String getCommandPattern();

	protected abstract void executeConcrete(Twitter twitter);
	
	String parseFirstArgument(String commandLine) {
		int userNameLength = commandLine.indexOf(" ");
		return commandLine.substring(0, userNameLength);
	}

	String parseSecondArgument(String commandLine) {
		int firstSpaceIndex = commandLine.indexOf(" ");
		int secondSpacePosition = commandLine.indexOf(" ", firstSpaceIndex+1);
		return commandLine.substring(secondSpacePosition+1);
	}

}