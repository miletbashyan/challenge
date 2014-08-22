package com.artak.twitter.console.commands;

import com.artak.twitter.server.Twitter;

public class FollowCommand extends AbstractCommand {
	private final String COMMAND_PATTERN = "^\\w+ follows \\w+$";
	private String follower;
	private String userToFollow;

	public FollowCommand() {
		super();
	}
	
	private FollowCommand(String commandLine) {
		String trimmedCommand = commandLine.trim();
		validateCommand(trimmedCommand);
		follower = parseFirstArgument(trimmedCommand);
		userToFollow = parseSecondArgument(trimmedCommand);
		initialize();
	}

	@Override
	protected String getCommandPattern() {
		return COMMAND_PATTERN;
	}

	@Override
	public Command newInstance(String commandLine) {
		return new FollowCommand(commandLine);
	}

	@Override
	protected void executeConcrete(Twitter twitter) {
		twitter.follow(follower, userToFollow);
	}
}
