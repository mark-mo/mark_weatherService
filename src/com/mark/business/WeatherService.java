package com.mark.business;

import java.util.List;

import javax.ejb.EJB;

import com.mark.beans.WeatherSensorModel;
import com.mark.data.DataAccessInterface;

public class WeatherService {
	@EJB
	DataAccessInterface<WeatherSensorModel> dao;
	
	public boolean save(WeatherSensorModel model) {
		return dao.create(model);
	}

	public List<WeatherSensorModel> getReadings() {
		return dao.findAll();
	}
}
