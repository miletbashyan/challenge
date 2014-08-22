package com.artak.twitter.console.commands;

import java.util.List;

import com.artak.twitter.console.formatters.WallItemFormatter;
import com.artak.twitter.server.Twitter;
import com.artak.twitter.server.model.WallItem;

public class WallCommand extends AbstractCommand {
	private final String COMMAND_PATTERN = "^\\w+ wall$";
	private String user;

	public WallCommand() {
		super();
	}

	private WallCommand(String commandLine) {
		String trimmedCommand = commandLine.trim();
		validateCommand(trimmedCommand);
		user = parseFirstArgument(commandLine);
		initialize();
	}

	@Override
	protected String getCommandPattern() {
		return COMMAND_PATTERN;
	}

	@Override
	public Command newInstance(String commandLine) {
		return new WallCommand(commandLine);
	}

	@Override
	protected void executeConcrete(Twitter twitter) {
		List<WallItem> wallItems = twitter.getWall(user);
		for (WallItem wallItem : wallItems) {
			System.out.println(WallItemFormatter.format(wallItem));
		}
	}
}
