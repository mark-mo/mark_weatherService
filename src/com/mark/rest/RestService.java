package com.mark.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.mark.beans.ResponseDataModel;
import com.mark.beans.ResponseModel;
import com.mark.beans.SensorModel;
import com.mark.business.WeatherServiceInterface;
import com.mark.exception.DatabaseErrorException;
import com.mark.util.HtmlCode;
import com.mark.util.LoggingInterceptor;
import com.mark.util.LoggingInterface;

/**
 * This class is REST Service that implements the REST API to support the
 * Weather Sensor IoT.
 * 
 * @author markreha
 * @version $Revision$
 */
@Interceptors(LoggingInterceptor.class)
@Path("/weather")
public class RestService {
	@Inject
	LoggingInterface logging;
	
	@EJB
	WeatherServiceInterface service;

	/**
	 * Test Service API at /test using HTTP GET.
	 * 
	 * @return Response Model
	 */
	@GET
	@Path("/test")
	@Produces("application/json")
	public String test() {
		// Log the API call
		logging.info("Entering RestService.test");

		// Return a Test Response
		// ResponseModel response = new ResponseModel(0, "This is a test");
		return "working...";
	}

	/**
	 * Save Sensor Data API at /save using HTTP POST. Return a ResponseModel object
	 * to show the response for the REST service TODO: Change to GET after ensuring
	 * it works
	 * 
	 * @param model
	 *            The WeatherSensorModel data to save.
	 * @return Response Model with error code of 1 for no error, 0 if save failed,
	 *         -2 if database error
	 */
	@POST
	@Path("/save")
	@Consumes("application/json")
	@Produces("application/json")
	public ResponseModel saveAccelSensorData(SensorModel model) {
		model.setSensorName("Main");
		// Log the API call
		logging.debug("Model: " + model.toString());

		// Send model to database
		boolean OK = service.save(model);
		// Return OK Response
		ResponseModel response = new ResponseModel(OK ? HtmlCode.Success.getIdentifier() : HtmlCode.BadRequest.getIdentifier(), 
				OK ? "OK" : "Error");
		logging.debug("Response: " + response.getMessage());
		logging.info("Exiting RestService.saveAccelSensorData");
		return response;
	}

	@GET
	@Path("/readings")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseModel getReadings() {
		ResponseModel response;
		logging.info("Entering RestService.getReadings");

		try {
			List<SensorModel> readings = service.getReadings();
			response = new ResponseDataModel<List<SensorModel>>(HtmlCode.Success.getIdentifier(), "OK",
					readings);
			logging.info("Response: " + response.getMessage());
		} catch (DatabaseErrorException e) {
			response = new ResponseModel(HtmlCode.BadRequest.getIdentifier(), "Error retrieving readings");
			logging.severe(response.getMessage());
		}

		logging.info("Exiting RestService.getReadings");
		return response;
	}
}