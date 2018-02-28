package com.mark.business;

import com.mark.beans.WeatherSensorModel;
import com.mark.data.WeatherDao;
import com.mark.exception.BadLoginException;

public class WeatherBusiness {

	public boolean save(WeatherSensorModel model) {
		WeatherDao dao = new WeatherDao();
		return dao.create(model);
	}
}
