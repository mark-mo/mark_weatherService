package com.mark.model;

public class AccelSensorModel {
	private String sensorName;
	private double pitch;
	private double roll;
	private double yaw;

	public AccelSensorModel() {
		sensorName = "";
		pitch = 0;
		roll = 0;
		yaw = 0;
	}

	public AccelSensorModel(double pitch, double roll, double yaw) {
		this.sensorName = "Main";
		this.pitch = pitch;
		this.roll = roll;
		this.yaw = yaw;
	}

	public String getSensorName() {
		return sensorName;
	}

	public void setSensorName(String sensorName) {
		this.sensorName = sensorName;
	}

	public double getPitch() {
		return pitch;
	}

	public void setPitch(double pitch) {
		this.pitch = pitch;
	}

	public double getRoll() {
		return roll;
	}

	public void setRoll(double roll) {
		this.roll = roll;
	}

	public double getYaw() {
		return yaw;
	}

	public void setYaw(double yaw) {
		this.yaw = yaw;
	}
	
	@Override
	public String toString() {
		return "AccelSensorModel [Pitch: " + pitch + ", Roll: " + roll + ", Yaw: " + yaw + "]";
	}
}