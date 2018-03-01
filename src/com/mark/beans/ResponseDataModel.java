package com.mark.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("deprecation")
@XmlRootElement(name="Response")
@XmlAccessorType(XmlAccessType.FIELD)
@ManagedBean
@ViewScoped
public class ResponseDataModel extends ResponseModel {
	private WeatherSensorModel data;
	
	public ResponseDataModel() {
		super(0, "");
		data = new WeatherSensorModel();
	}
	
	public ResponseDataModel(int status, String message, WeatherSensorModel data) {
		super(status, message);
		
		this.data = data;
	}

	public WeatherSensorModel getData() {
		return data;
	}

	public void setData(WeatherSensorModel data) {
		this.data = data;
	}
}