package com.mark.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class WeatherSensorModel {
	private String sensorName;
	private double humidity;
	private double pressure;
	private String time;
	
	public WeatherSensorModel() {
		this.humidity = 0;
		this.pressure = 0;
	}
	
	public WeatherSensorModel(double humidity, double pressure) {
		this.humidity = humidity;
		this.pressure = pressure;
	}
	
	public WeatherSensorModel(double humidity, double pressure, String time) {
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

	public String getTime() {
		return time;
	}
	
	public void setTime(String date) {
		this.time = date;
	}

	
}