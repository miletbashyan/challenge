package com.artak.twitter.console.commands;

import java.util.List;

import com.artak.twitter.console.formatters.TwittFormatter;
import com.artak.twitter.server.Twitter;
import com.artak.twitter.server.model.Twitt;

public class ReadCommand extends AbstractCommand {
	private final String COMMAND_PATTERN = "^\\w+$";
	private String user;

	public ReadCommand() {
		super();
	}

	private ReadCommand(String commandLine) {
		String trimmedCommand = commandLine.trim();
		validateCommand(trimmedCommand);
		user = trimmedCommand;
		initialize();
	}

	@Override
	protected String getCommandPattern() {
		return COMMAND_PATTERN;
	}

	@Override
	public Command newInstance(String commandLine) {
		return new ReadCommand(commandLine);
	}

	@Override
	protected void executeConcrete(Twitter twitter) {
		List<Twitt> twitts = twitter.getTimeLine(user);
		for (Twitt twitt : twitts) {
			System.out.println(TwittFormatter.format(twitt));
		}
	}

}
