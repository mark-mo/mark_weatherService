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
public class ResponseDataModel <T> extends ResponseModel {
	private T data;
	
	public ResponseDataModel() {
		super(0, "");
		data = null;
	}
	
	public ResponseDataModel(int status, String message, T data) {
		super(status, message);
		
		this.data = data;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}