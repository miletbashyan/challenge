package com.artak.twitter.console.commands;

import java.util.ArrayList;
import java.util.List;

public class CommandResolver {

	private List<Command> commands = new ArrayList<>();

	public CommandResolver() {
		commands.add(new FollowCommand());
		commands.add(new WallCommand());
		commands.add(new ReadCommand());
		commands.add(new PostCommand());
	}

	public Command resolve(String commandLine) {
		for (Command command : commands) {
			if(command.matches(commandLine)){
				return command.newInstance(commandLine);
			}
		}
		throw new UnsupportedOperationException("invalid command: \"" + commandLine + "\"");
	}

}
