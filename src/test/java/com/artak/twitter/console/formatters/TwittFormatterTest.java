package com.artak.twitter.console.formatters;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.GregorianCalendar;
import org.junit.Test;
import com.artak.twitter.server.model.Twitt;

public class TwittFormatterTest {

	@Test
	public void twittFormat1MinuteAgo() {
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.add(Calendar.MINUTE, -1);
		Twitt twitt = new Twitt("Message", calendar.getTime());
		
		String twittText = TwittFormatter.format(twitt);
		assertEquals("Message (moments ago)", twittText);
	}

	@Test
	public void twittFormat5MinutesAgo() {
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.add(Calendar.MINUTE, -5);
		Twitt twitt = new Twitt("Message", calendar.getTime());
		
		String twittText = TwittFormatter.format(twitt);
		assertEquals("Message (5 minutes ago)", twittText);
	}
	
	@Test
	public void twittFormat1HourAgo() {
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.add(Calendar.HOUR, -1);
		Twitt twitt = new Twitt("Message", calendar.getTime());
		String twittText = TwittFormatter.format(twitt);
		assertEquals("Message (1 hour ago)", twittText);
	}
	
	@Test
	public void twittFormat1DayAgo() {
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, -1);
		Twitt twitt = new Twitt("Message", calendar.getTime());
		String twittText = TwittFormatter.format(twitt);
		assertEquals("Message (1 day ago)", twittText);
	}
}
