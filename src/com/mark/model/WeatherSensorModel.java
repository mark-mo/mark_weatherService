package com.mark.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WeatherSensorModel {
	private String sensorName;
	private double humidity;
	private double pressure;
	private double year;
	private double month;
	private double day;
	private double hour;
	private double minute;
	private double second;

	public WeatherSensorModel() {
		sensorName = "";
		humidity = 0;
		pressure = 0;
	}

	public WeatherSensorModel(double humidity, double pressure) {
		this.sensorName = "Main";
		this.humidity = humidity;
		this.pressure = pressure;
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		
		String unparsed = dateFormat.format(date);
		setTime(unparsed);
	}
	
	public WeatherSensorModel(double humidity, double pressure, String time) {
		this.sensorName = "Main";
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

	public void setHumidity(double humidity) {
		this.humidity = humidity;
	}

	public double getPressure() {
		return pressure;
	}

	public void setPressure(double pressure) {
		this.pressure = pressure;
	}
	

	public double getYear() {
		return year;
	}

	public double getMonth() {
		return month;
	}

	public double getDay() {
		return day;
	}

	public double getHour() {
		return hour;
	}

	public double getMinute() {
		return minute;
	}

	public double getSecond() {
		return second;
	}

	public void setTime(String time) {
		//2016/11/16 12:08:43
		this.year = Integer.parseInt(time.substring(0, 4));
		time = time.substring(5);
		this.month = Integer.parseInt(time.substring(0, 2));
		time = time.substring(3);
		this.day = Integer.parseInt(time.substring(0, 2));
		time = time.substring(3);
		this.hour = Integer.parseInt(time.substring(0, 2));
		time = time.substring(3);
		this.minute = Integer.parseInt(time.substring(0, 2));
		time = time.substring(3);
		this.second = Integer.parseInt(time.substring(0, 2));
	}
}