package com.mark.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.mark.beans.ResponseModel;
import com.mark.beans.ResponseDataModel;
import com.mark.beans.WeatherSensorModel;
import com.mark.business.WeatherBusiness;
import com.mark.exception.DatabaseErrorException;
import com.mark.util.HtmlCode;

/**
 * This class is REST Service that implements the REST API to support the
 * Weather Sensor IoT.
 */
@Path("/weather")
public class RestService {

	/**
	 * Test Service API at /test using HTTP GET.
	 * 
	 * @return ResponseModel with "status" 0 and "message" "This is a test"
	 */
	@GET
	@Path("/test")
	@Produces("application/json")
	public ResponseModel test() {
		// Return a Test Response
		ResponseModel response = new ResponseModel(0, "This is a test");
		return response;
	}

	/**
	 * Save Sensor Data API at /save using HTTP POST. Return a ResponseModel object
	 * to show the response for the REST service
	 * 
	 * @param model
	 *            The WeatherSensorModel data to save. JSON with fields "humidity", "pressure", and "time"
	 * @return JSON ResponseModel
	 *            JSON with "status" (response code from HtmlCode class enumerator) and "message"
	 */
	@POST
	@Path("/save")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseModel saveReading(WeatherSensorModel model) {
		System.out.println("In save");
		model.setSensorName("Main");
		
		// Send model to database
		WeatherBusiness business = new WeatherBusiness();

		if (business.save(model)) {
			// Return OK Response
			ResponseModel response = new ResponseModel(HtmlCode.Success.getIdentifier(), "OK");
			return response;
		}
		else {
			ResponseModel response = new ResponseModel(HtmlCode.BadRequest.getIdentifier(), "Bad Request");
			return response;
		}
	}

	/**
	 * Gets all weather readings
	 * 
	 * @param model
	 *            The WeatherSensorModel data to save. JSON with fields "humidity", "pressure", and "time"
	 * @return JSON ResponseDataModel (or ResponseModel if error)
	 *            JSON with "data", a list of WeatherSensorModels, and usual "status" and "message"
	 */
	@GET
	@Path("/readings")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseModel getReadings() {
		WeatherBusiness business = new WeatherBusiness();
		ResponseModel response;
		
		try {
			List<WeatherSensorModel> readings = business.getReadings();
			response = new ResponseDataModel<List<WeatherSensorModel>>(HtmlCode.Success.getIdentifier(), "OK", readings);
		} catch (DatabaseErrorException e) {
			response = new ResponseModel(HtmlCode.BadRequest.getIdentifier(), "Error retrieving readings");
		}
		
		return response;
	}
}