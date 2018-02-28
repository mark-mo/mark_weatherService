package com.mark.business;

import com.mark.beans.WeatherSensorModel;
import com.mark.data.WeatherDao;
import com.mark.util.BadLoginException;

public class WeatherBusiness {

	public void save(WeatherSensorModel model) {
		WeatherDao dao = new WeatherDao();
		dao.create(model);
	}
}
