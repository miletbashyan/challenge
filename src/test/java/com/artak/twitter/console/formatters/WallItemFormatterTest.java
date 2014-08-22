package com.artak.twitter.console.formatters;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.artak.twitter.console.formatters.WallItemFormatter;
import com.artak.twitter.server.model.Twitt;
import com.artak.twitter.server.model.WallItem;

public class WallItemFormatterTest {

	@Test
	public void wallItemFormat() {
		List<Twitt> twitts = Arrays.asList(new Twitt("Message1", new Date()),
				new Twitt("Message2", new Date()));
		WallItem wallItem = new WallItem("Bob", twitts);

		String wallText = WallItemFormatter.format(wallItem);
		
		boolean containsAllMessagesAndUserName = wallText.startsWith("Bob")
				&& wallText.contains("Message1")
				&& wallText.contains("Message2");
		assertTrue(containsAllMessagesAndUserName);
	}

}
