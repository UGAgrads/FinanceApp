package com.UGAgrads.freddiefinance;

/**
 * Constant values use for categories the sources of deposits
 * 
 * @author UGAGrads
 */
public enum Source {
	
    /**
     * enum for salary source
     */
    Salary("Salary"), 
    
    /**
     * enum for interest source
     */
    Interest("Interest"), 
    
    /**
     * enum for gift source
     */
    Gift("Gift");
	
    /**
     * variable holding the name of the source 
     */
    private final String name;
	
	/**
	 * @param sourceName which is what the use wants to name the source
	 */
    Source(String sourceName) { this.name = sourceName; };
	
	/**
	 * @return string of the name of the source given by the user
	 */
    public String getName() { return this.name; };
}
