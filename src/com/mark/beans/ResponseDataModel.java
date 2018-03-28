package com.mark.beans;

import javax.inject.Named;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Response")
@XmlAccessorType(XmlAccessType.FIELD)
@Named
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