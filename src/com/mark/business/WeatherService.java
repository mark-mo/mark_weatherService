package com.mark.business;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import com.mark.beans.SensorModel;
import com.mark.data.DataAccessInterface;

import com.mark.util.LoggingInterceptor;

@Stateless
@Interceptors(LoggingInterceptor.class)
@Local(WeatherServiceInterface.class)
@LocalBean
public class WeatherService {
	@EJB(beanName="WeatherDAO")
	DataAccessInterface<SensorModel> weatherDAO;
	
	public boolean save(SensorModel model) {
		return weatherDAO.create(model);
	}

	public List<SensorModel> getReadings() {
		return weatherDAO.findAll();
	}
}
