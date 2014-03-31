package com.UGAgrads.freddiefinance;

/**
 * Constant values used for describing the reasons for Withdrawal transactions.
 * 
 * @author UGAgrads
 */
public enum Expense {
	
    /** Expense constant Rent. */
    Rent("Rent"), 
    /** Expense constant Food. */
    Food("Food"),
    /** Expense constant Clothes. */
    Clothes("Clothes"), 
    /** Expense constant Entertainment. */
    Entertainment("Entertainment");
	
    /** The Expense constant reference. */
    private final String name;
	
	/**
	 * Sets the name reference variable to the input sting.
	 * 
	 * @param expenseName Expense constant as a string
	 */
    Expense(String expenseName) { this.name = expenseName; };
	
	/**
	 * Gets the specified constant as a string.
	 * 
	 * @return The string value of the expense.
	 */
    public String getName() { return this.name; };
	
}
