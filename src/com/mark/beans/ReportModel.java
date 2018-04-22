package com.mark.beans;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class ReportModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String reportType;
	private int amount;
	
	public ReportModel() {
		reportType = "";
		amount = 0;
	}
	
	public ReportModel(String reportType, int amount) {
		this.reportType = reportType;
		this.amount = amount;
	}

	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
}
