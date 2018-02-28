package com.mark.util;

/**
 * A collection of Html return codes
 * @author Mark
 *
 */
public enum HtmlCode {
	Success(200),
	PageNotFound(404),
	BadRequest(400);
	
	int identifier;
	
	HtmlCode(int identifier) {
		this.identifier = identifier;
	}
	
	public int getIdentifier() {
		return identifier;
	}
}
