package com.mark.util;

import java.util.HashMap;

import javax.inject.Inject;

import com.mark.beans.WeatherSensorModel;

public class WeatherSensorFactory {
	@Inject
	LoggingInterface logging;
	
	private static final HashMap<String, WeatherSensorModel> weatherMap = new HashMap<String, WeatherSensorModel>();

	// Ensure that there are no duplicate WeatherSensorModels
	public static WeatherSensorModel getWeather(double humidity, double pressure) {
		
		WeatherSensorModel weather = (WeatherSensorModel)weatherMap.get(humidity + "," + pressure);
		
		if(weather == null) {
			weather = new WeatherSensorModel(humidity, pressure);
			weatherMap.put(humidity + "," + pressure,  weather);
			System.out.println("Creating WeatherSensorModel with data : " + humidity + "," + pressure);
		}
		return weather;
	}
}
