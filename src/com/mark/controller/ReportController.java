package com.mark.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.bean.ViewScoped;
import com.mark.beans.ReportModel;

@ManagedBean
@ViewScoped
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
