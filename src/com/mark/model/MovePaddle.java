package com.mark.model;

public class MovePaddle {
	private double dx;

	// Changing radians per second to meters per second
	public MovePaddle(double dx) {
		double x = dx * 1 / Math.PI;
		double y = 1 / x;
		double out = 40 / y;
		this.dx = out;
	}

	public double getDx() {
		return dx;
	}

	public void setDx(double dx) {
		this.dx = dx;
	}
}
