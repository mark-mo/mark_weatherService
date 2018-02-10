package com.mark.dao;

import java.util.List;

import com.mark.model.WeatherSensorModel;

public interface WeatherDaoInterface {
	public void save(WeatherSensorModel model);
	
	public List<WeatherSensorModel> findAll();
}
