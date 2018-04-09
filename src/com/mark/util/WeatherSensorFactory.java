package com.mark.util;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.inject.Inject;
import javax.interceptor.Interceptors;

import com.mark.beans.WeatherSensorModel;

@Interceptors(LoggingInterceptor.class)
public class WeatherSensorFactory {
	@Inject
	LoggingInterface logging;
	
	static TimeHelper timeHelper;

	private static final HashMap<String, WeatherSensorModel> weatherMap = new HashMap<String, WeatherSensorModel>();

	// For creating brand new weather models, sets time to current time
	public static WeatherSensorModel getWeather(double humidity, double pressure) {

		WeatherSensorModel weather = (WeatherSensorModel) weatherMap.get(humidity + "," + pressure);
		
		if(timeHelper == null) {
			timeHelper = new TimeHelper();
		}

		if (weather == null) {
			weather = new WeatherSensorModel(humidity, pressure);

			Date date = new Date();
			String time = DateFormat.getDateInstance().format(date);

			time = date.toString();

			weather.setTime(timeHelper.setFormatTime(time));
			weatherMap.put(humidity + "," + pressure, weather);
			System.out.println("Creating WeatherSensorModel with data : " + humidity + "," + pressure);
		}
		return weather;
	}

	// For creating older weather models, sets time to the time that was stored in
	// the database
	public static WeatherSensorModel getWeather(double humidity, double pressure, String time) {
		WeatherSensorModel weather = (WeatherSensorModel) weatherMap.get(humidity + "," + pressure);

		if(timeHelper == null) {
			timeHelper = new TimeHelper();
		}
		
		if (weather == null) {
			weather = new WeatherSensorModel(humidity, pressure, time);

			weather.setTime(time);
			weatherMap.put(humidity + "," + pressure, weather);
			System.out.println("Creating WeatherSensorModel with data : " + humidity + "," + pressure);
		}
		return weather;
	}
}
