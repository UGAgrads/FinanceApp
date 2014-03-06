package com.UGAgrads.freddiefinance;

public enum Expense {
	Rent("Rent"), 
	Food("Food"), 
	Clothes("Clothes"), 
	Entertainment("Entertainment");
	
	private final String name;
	
	Expense(String name) { this.name = name; };
	public String getValue(){ return this.name; };
	
}
