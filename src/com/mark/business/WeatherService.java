package com.mark.business;

import java.util.ArrayList;
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

	public List<SensorModel> getReadings(int amount) {
		return reverseReadings(weatherDAO.findSelect(amount));
	}
	
	private List<SensorModel> reverseReadings(List<SensorModel> list) {
		List<SensorModel> corrected = new ArrayList<SensorModel>();
		
		for(int i = list.size() - 1; i > -1; i--) {
			corrected.add(list.get(i));
		}
		
		return corrected;
	}
}
