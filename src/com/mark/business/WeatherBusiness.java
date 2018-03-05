package com.mark.business;

import com.mark.beans.WeatherSensorModel;
import com.mark.data.WeatherDAO;
import com.mark.exception.BadLoginException;

public class WeatherBusiness {

	public boolean save(WeatherSensorModel model) {
		WeatherDAO dao = new WeatherDAO();
		return dao.create(model);
	}
}
