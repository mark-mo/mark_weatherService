package com.mark.util;

/**
 * A collection of months for DateTime to translate from the abbreviation to the number
 * 
 * @author Mark
 *
 */
public enum Month {
	Jan ("01"),
	Feb ("02"),
	Mar ("03"),
	Apr ("04"),
	May ("05"),
	Jun ("06"),
	Jul ("07"),
	Aug ("08"),
	Sep ("09"),
	Oct ("10"),
	Nov ("11"),
	Dec ("12");
	
	String identifier;
	
	Month(String identifier) {
		this.identifier = identifier;
	}
	
	public String getIdentifier() {
		return identifier;
	}
}
