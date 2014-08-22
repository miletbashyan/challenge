package com.artak.twitter.console.commands;

import com.artak.twitter.server.Twitter;

public interface Command {
	boolean matches(String commandLine);

	Command newInstance(String commandLine);

	void execute(Twitter twitter);
}
