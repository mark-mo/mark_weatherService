package com.mark.util;

import java.io.Serializable;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class LoggingInterceptor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2285627168837773873L;
	
	@Inject
	LoggingInterface logging;

	@AroundInvoke
	public Object methodInterceptor(InvocationContext ctx) throws Exception {
		logging.trace("******************* Intercepting call to method: " + ctx.getTarget().getClass() + "."
				+ ctx.getMethod().getName() + "()");
		return ctx.proceed();
	}
}
