package com.ldapauth.utility;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtTokenDateUtil {
	public static String jwtTokenDateToLMFormat(String date) {
		final DateFormat inputFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
		// final DateFormat outputFormat = new SimpleDateFormat( "MM/dd/yyyy
		// HH:mm:ss aaa");
		final DateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
		outputFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		String convertedDate = null;
		try {
			convertedDate = outputFormat.format(inputFormat.parse(date));
		} catch (final ParseException e) {
			log.error("Error parsing the es date");
		}
		return convertedDate;
	}
}
