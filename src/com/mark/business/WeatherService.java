package com.mark.business;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

import com.mark.beans.WeatherSensorModel;
import com.mark.data.DataAccessInterface;

import com.mark.util.LoggingInterceptor;
import com.mark.util.LoggingInterface;

@Stateless
@Interceptors(LoggingInterceptor.class)
@Local(WeatherServiceInterface.class)
@LocalBean
public class WeatherService {
	@Inject
	LoggingInterface logging;
	
	@EJB(beanName="WeatherDAO")
	DataAccessInterface<WeatherSensorModel> weatherDAO;
	
	public boolean save(WeatherSensorModel model) {
		logging.info("Enter into WeatherService.save()");
		boolean result = weatherDAO.create(model);
		logging.info("Exit WeatherService.save() with result " + result);
		return result;
	}

	public List<WeatherSensorModel> getReadings() {
		logging.info("Enter into WeatherService.getReadings()");
		List<WeatherSensorModel> results = weatherDAO.findAll();
		logging.info("Exit WeatherService.findAll() with List of " + results.size() + " WeatherSensorModels.");
		return results;
	}
}
