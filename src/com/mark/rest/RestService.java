package com.mark.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.mark.beans.ResponseDataModel;
import com.mark.beans.ResponseModel;
import com.mark.beans.WeatherSensorModel;
import com.mark.business.WeatherBusiness;
import com.mark.exception.DatabaseErrorException;
import com.mark.util.HtmlCode;

/**
 * This class is REST Service that implements the REST API to support the
 * Weather Sensor IoT.
 * 
 * @author markreha
 * @version $Revision$
 */
@Path("/weather")
public class RestService {
	// Logger logger = LoggerFactory.getLogger(RestService.class);
	// static WeatherServiceInterface service;

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
		// logger.info("Entering RestService.test()");

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
	public ResponseModel saveAccelSensorData(WeatherSensorModel model) {
		System.out.println("In save");
		model.setSensorName("Main");
		// Log the API call
		// logger.info("Entering RestService.save()");
		// logger.debug("Model: " + model.toString());

		// Send model to database
		WeatherBusiness business = new WeatherBusiness();

		if (business.save(model)) {
			// Return OK Response
			ResponseModel response = new ResponseModel(HtmlCode.Success.getIdentifier(), "OK");
			return response;
		} else {
			ResponseModel response = new ResponseModel(HtmlCode.BadRequest.getIdentifier(), "Bad Request");
			return response;
		}
	}

	@GET
	@Path("/readings")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseModel getReadings() {
		WeatherBusiness business = new WeatherBusiness();
		ResponseModel response;

		try {
			List<WeatherSensorModel> readings = business.getReadings();
			response = new ResponseDataModel<List<WeatherSensorModel>>(HtmlCode.Success.getIdentifier(), "OK",
					readings);
		} catch (DatabaseErrorException e) {
			response = new ResponseModel(HtmlCode.BadRequest.getIdentifier(), "Error retrieving readings");
		}

		return response;
	}
}