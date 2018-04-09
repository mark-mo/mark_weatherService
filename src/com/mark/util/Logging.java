package com.mark.util;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Startup
@Singleton
public class Logging implements LoggingInterface {
	@PostConstruct
	public void init() {
	}
	
	public void debug(String info) {
		Date time = new Date();
		System.out.println(time.toString() + " [DEBUG] " + info);
	}
	
	public void severe(String info) {
		Date time = new Date();
		System.out.println(time.toString() + " [SEVERE] " + info);
	}
	
	public void warning(String info) {
		Date time = new Date();
		System.out.println(time.getTime() + " [WARNING] " + info);
	}
	
	public void info(String info) {
		Date time = new Date();
		System.out.println(time.toString() + " [INFO] " + info);
	}
	
	public void trace(String info) {
		Date time = new Date();
		System.out.println("******" + time.toString() + " [TRACE] " + info);
	}
}
