package com.mark.business;

import java.util.List;

import com.mark.beans.SensorModel;

public interface WeatherServiceInterface {
	public boolean save(SensorModel model);
	
	public List<SensorModel> getReadings(int amount);
}
