package com.mark.util;

import java.io.Serializable;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class SecurityInterceptor implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4437763658959654824L;
	

	@AroundInvoke
	public Object methodInterceptor(InvocationContext ctx) throws Exception {
		System.out.println("******************* Intercepting call to method: " + ctx.getTarget().getClass() + "."
				+ ctx.getMethod().getName() + "()");
		return ctx.proceed();
	}
}
