package com.artak.twitter.console.formatters;

import org.ocpsoft.prettytime.PrettyTime;

import com.artak.twitter.server.model.Twitt;

public class TwittFormatter {

	public static String format(Twitt twitt) {
		PrettyTime prettyTime = new PrettyTime();
		String messageWithTimePassed = twitt.getMessage() + " ("
				+ prettyTime.format(twitt.getDate()) + ")";
		return messageWithTimePassed;
	}

}
