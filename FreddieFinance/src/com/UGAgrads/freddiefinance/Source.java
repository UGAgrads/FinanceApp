package com.UGAgrads.freddiefinance;

/**
 * @author
 */
public enum Source {
    /**
     * 
     */
    Salary("Salary"), Interest("Interest"), Gift("Gift");
	
    /**
     * 
     */
    private final String name;
	
	/**
	 * @param sourceName FILL THIS IN!
	 */
    Source(String sourceName) { this.name = sourceName; };
	
	/**
	 * @return FILL THIS IN!
	 */
    public String getName() { return this.name; };
}
