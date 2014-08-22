package com.artak.twitter.console.commands;

import com.artak.twitter.server.Twitter;

public class PostCommand extends AbstractCommand {
	private final String COMMAND_PATTERN = "^\\w+ -> .+";
	private String user;
	private String message;

	public PostCommand() {
		super();
	}
	
	private PostCommand(String commandLine) {
		String trimmedCommand = commandLine.trim();
		validateCommand(trimmedCommand);
		user = parseFirstArgument(trimmedCommand);
		message = parseSecondArgument(trimmedCommand);
		initialize();
	}
	

	@Override
	protected String getCommandPattern() {
		return COMMAND_PATTERN;
	}

	@Override
	public Command newInstance(String commandLine) {
		return new PostCommand(commandLine);
	}

	@Override
	protected void executeConcrete(Twitter twitter) {
		twitter.post(user, message);
		
	}
}
