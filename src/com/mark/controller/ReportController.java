package com.mark.controller;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.interceptor.Interceptors;

import com.mark.beans.ReportModel;
import com.mark.beans.User;
import com.mark.util.LoggingInterceptor;

@Interceptors({ LoggingInterceptor.class })
@ViewScoped
@Named
public class ReportController {
	public String getReport() {
		// Get the User model from POST
		FacesContext context = FacesContext.getCurrentInstance();
		ReportModel report = context.getApplication().evaluateExpressionGet(context, "#{report}", ReportModel.class);

		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("report", report);
		if(report.getType().equals("table")) {
			return "table.xhmtl";
		}
		else if(report.getType().equals("report")) {
			return "report.xhmtl";
		}
		return "table.xhtml";
	}
}
