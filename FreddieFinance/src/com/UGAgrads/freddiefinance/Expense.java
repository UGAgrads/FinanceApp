package com.UGAgrads.freddiefinance;

/**
 * @author
 */
public enum Expense {
	
    /** */
    Rent("Rent"), Food("Food"), Clothes("Clothes"), Entertainment("Entertainment");
	
    /** */
    private final String name;
	
	/**
	 * @param expenseName FILL THIS IN!
	 */
    Expense(String expenseName) { this.name = expenseName; };
	
	/**
	 * @return The string value of the expense.
	 */
    public String getName() { return this.name; };
	
}
