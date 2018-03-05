package com.mark.business;

import java.util.List;

import com.mark.beans.WeatherSensorModel;
import com.mark.data.WeatherDAO;
import com.mark.exception.BadLoginException;

public class WeatherBusiness {

	public boolean save(WeatherSensorModel model) {
		WeatherDAO dao = new WeatherDAO();
		return dao.create(model);
	}
	
	public List<WeatherSensorModel> getReadings() {
		WeatherDAO dao = new WeatherDAO();
		return dao.findAll();
	}
}
