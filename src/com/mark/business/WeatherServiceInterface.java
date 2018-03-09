package com.mark.business;

import java.util.List;

import com.mark.beans.WeatherSensorModel;

public interface WeatherServiceInterface {
	public boolean save(WeatherSensorModel model);
	
	public List<WeatherSensorModel> getReadings();
}
