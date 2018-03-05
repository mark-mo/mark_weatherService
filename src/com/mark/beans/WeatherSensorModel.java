package com.mark.beans;

import java.text.DateFormat;
import java.util.Date;

import com.mark.util.Month;

public class WeatherSensorModel {
	private String sensorName;
	private double humidity;
	private double pressure;
	private int year;
	private int day;
	private int hour;
	private int minute;
	private int second;
	private String month;
	private String timeZone;
	private String dow;
	private String time;
	
	private Month monthLabel;
	
	public WeatherSensorModel() {
		this.humidity = 0;
		this.pressure = 0;

		Date date = new Date();
		time = DateFormat.getDateInstance().format(date);
		
		time = date.toString();
		
		setFormatTime(time);
	}
	
	public WeatherSensorModel(int humidity, int pressure) {
		this.humidity = humidity;
		this.pressure = pressure;
		
		//Date date = new Date();
		//time = DateFormat.getDateInstance().format(date);
		
		//time = date.toString();
		
		//System.out.println("Original Time: " + time);
		//setFormatTime(time);
	}
	
	public WeatherSensorModel(int humidity, int pressure, String time) {
		this.humidity = humidity;
		this.pressure = pressure;
		
		setTime(time);
	}

	public String getSensorName() {
		return sensorName;
	}

	public void setSensorName(String sensorName) {
		this.sensorName = sensorName;
	}

	public double getHumidity() {
		return humidity;
	}

	public void setHumidity(int humidity) {
		this.humidity = humidity;
	}

	public double getPressure() {
		return pressure;
	}

	public void setPressure(int pressure) {
		this.pressure = pressure;
	}

	public int getYear() {
		return year;
	}

	public String getMonth() {
		return month;
	}

	public int getDay() {
		return day;
	}

	public int getHour() {
		return hour;
	}

	public int getMinute() {
		return minute;
	}

	public int getSecond() {
		return second;
	}

	public String getDow() {
		return dow;
	}

	public void setDow(String dow) {
		this.dow = dow;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public String getTime() {
		return time;
	}
	
	public void setTime(String date) {
		this.time = date;
	}

	// Breaks apart the given string and formats the string to the correct DateTime for JavaScript
	public void setFormatTime(String date) {
		
		this.setDow(date.substring(0, 3));
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
		
		String formattedTime = year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second;
		
		time = formattedTime;
	}
}