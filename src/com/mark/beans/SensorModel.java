package com.mark.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.mark.util.TimeHelper;

@ManagedBean
@ViewScoped
public class SensorModel implements SensorInterface {
	private String sensorName;
	private double firstAtt;
	private double secondAtt;
	private String time;
	
	public SensorModel() {
		this.firstAtt = 0;
		this.secondAtt = 0;
	}
	
	public SensorModel(double firstAtt, double secondAtt) {
		this.firstAtt = firstAtt;
		this.secondAtt = secondAtt;
	}
	
	public SensorModel(double firstAtt, double secondAtt, String time) {
		this.firstAtt = firstAtt;
		this.secondAtt = secondAtt;
		
		setTime(time);
	}

	public String getSensorName() {
		return sensorName;
	}

	public void setSensorName(String sensorName) {
		this.sensorName = sensorName;
	}

	public double getFirstAtt() {
		return firstAtt;
	}

	public void setFirstAtt(int firstAtt) {
		this.firstAtt = firstAtt;
	}

	public double getSecondAtt() {
		return secondAtt;
	}

	public void setSecondAtt(int secondAtt) {
		this.secondAtt = secondAtt;
	}

	public String getTime() {
		return time;
	}
	
	public void setTime(String date) {
		this.time = date;
	}
}