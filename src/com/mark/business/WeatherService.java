package com.mark.business;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Named;

import com.mark.beans.WeatherSensorModel;
import com.mark.data.DataAccessInterface;

@Stateless
@Local(WeatherServiceInterface.class)
@LocalBean
public class WeatherService {
	@EJB
	@Named("weatherDAO")
	DataAccessInterface<WeatherSensorModel> dao;
	
	public boolean save(WeatherSensorModel model) {
		return dao.create(model);
	}

	public List<WeatherSensorModel> getReadings() {
		return dao.findAll();
	}
}
