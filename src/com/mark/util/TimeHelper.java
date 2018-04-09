package com.mark.util;

public class TimeHelper {
	private int year;
	private int day;
	private int hour;
	private int minute;
	private int second;
	private String month;
	private String timeZone;
	private String dow;
	private Month monthLabel;

	// Breaks apart the given string and formats the string to the correct DateTime
	// for JavaScript
	public String setFormatTime(String date) {

		dow = date.substring(0, 3);
		date = date.substring(4);
		this.month = date.substring(0, 3);
		this.monthLabel = Month.valueOf(this.month);
		this.month = monthLabel.getIdentifier();
		date = date.substring(4);
		this.day = Integer.parseInt(date.substring(0, 2));
		date = date.substring(3);
		this.hour = Integer.parseInt(date.substring(0, 2));
		date = date.substring(3);
		this.minute = Integer.parseInt(date.substring(0, 2));
		date = date.substring(3);
		this.second = Integer.parseInt(date.substring(0, 2));
		date = date.substring(3);
		this.timeZone = date.substring(0, 3);
		date = date.substring(4);
		this.year = Integer.parseInt(date.substring(0, 4));

		String formattedTime;
		if (day < 10) {
			System.out.println("Day is less than ten, formatting properly");
			formattedTime = year + "-" + month + "-0" + day + " " + hour + ":" + minute + ":" + second;
		} else
			formattedTime = year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second;

		return formattedTime;
	}
}
