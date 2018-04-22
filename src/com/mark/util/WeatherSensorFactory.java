package com.mark.util;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.inject.Inject;
import javax.interceptor.Interceptors;

import com.mark.beans.SensorInterface;
import com.mark.beans.SensorModel;

@Interceptors(LoggingInterceptor.class)
public class WeatherSensorFactory {
	@Inject
	LoggingInterface logging;

	static TimeHelper timeHelper;

	private static final HashMap<String, SensorInterface> weatherMap = new HashMap<String, SensorInterface>();

	// For creating brand new weather models, sets time to current time
	public static SensorInterface getWeather(double humidity, double pressure) {
		// Attempting to get the model from the hashmap will through off the time
		SensorInterface weather;

		if (timeHelper == null) {
			timeHelper = new TimeHelper();
		}

		weather = new SensorModel(humidity, pressure);

		Date date = new Date();
		String time = DateFormat.getDateInstance().format(date);

		time = date.toString();

		weather.setTime(timeHelper.setFormatTime(time));
		weatherMap.put(humidity + "," + pressure + "," + time, weather);
		System.out.println("Creating WeatherSensorModel with data : " + humidity + "," + pressure);
		
		return weather;
	}

	// For creating older weather models, sets time to the time that was stored in
	// the database
	public static SensorInterface getWeather(double humidity, double pressure, String time) {
		SensorInterface weather = (SensorInterface) weatherMap.get(humidity + "," + pressure + "," + time);

		if (timeHelper == null) {
			timeHelper = new TimeHelper();
		}

		if (weather == null) {
			weather = new SensorModel(humidity, pressure, time);

			weather.setTime(time);
			weatherMap.put(humidity + "," + pressure, weather);
			System.out.println("Creating WeatherSensorModel with data : " + humidity + "," + pressure);
		}
		return weather;
	}
}
