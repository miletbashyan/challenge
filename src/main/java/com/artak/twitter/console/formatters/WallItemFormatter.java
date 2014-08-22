package com.artak.twitter.console.formatters;

import com.artak.twitter.server.model.Twitt;
import com.artak.twitter.server.model.WallItem;

public class WallItemFormatter {

	public static String format(WallItem wallItem) {
		String returnString = "";
		
		for (Twitt twitt : wallItem.getTimeLine()) {
			returnString += wallItem.getUser() + " - " + TwittFormatter.format(twitt) + "\r\n";
		}
		return returnString;
	}

}
