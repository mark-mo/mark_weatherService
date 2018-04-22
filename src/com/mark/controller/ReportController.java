package com.mark.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.bean.ViewScoped;
import com.mark.beans.ReportModel;

@ManagedBean
@ViewScoped
public class ReportController {
	public String onReport() {
		// Get the User model from POST
		FacesContext context = FacesContext.getCurrentInstance();
		ReportModel report = context.getApplication().evaluateExpressionGet(context, "#{reportModel}", ReportModel.class);

		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("report", report);
		if (report.getReportType().equals("table")) {
			return "table.xhtml";
		}
		else if (report.getReportType().equals("graph")) {
			return "graph.xhtml";
		}
		return "table.xhtml";
	}
}
