package com.UGAgrads.freddiefinance;

public enum Source {
	Salary("Salary"),
	Interest("Interest"),
	Gift("Gift");
	
	private final String name;
	
	Source(String name){ this.name = name; };
	public String getValue(){ return this.name; };
}
