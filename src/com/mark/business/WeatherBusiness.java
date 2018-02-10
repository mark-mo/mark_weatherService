package com.mark.business;

import com.mark.dao.WeatherDao;
import com.mark.model.WeatherSensorModel;

public class WeatherBusiness {

	public void save(WeatherSensorModel model) {
		WeatherDao dao = new WeatherDao();
		dao.save(model);
	}
}
