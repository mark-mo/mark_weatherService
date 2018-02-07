package com.mark.model;

public class WeatherModel {
	private String sensorName;
	private double humidity;
	private double pressure;
	
	public WeatherModel(double humidity, double pressure) {
		sensorName = "Default";
		this.humidity = humidity;
		this.pressure = pressure;
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
}
