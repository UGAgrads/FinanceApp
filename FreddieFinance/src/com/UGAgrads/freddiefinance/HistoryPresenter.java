package com.UGAgrads.freddiefinance;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Presenter class used for the computations in the HistoryActivity class.
 * 
 * @author UGAgrads
 */
public class HistoryPresenter {

    /** List of accounts that are being searched for Transactions. */
    private ArrayList<String> accountsList;
	/** SQLite Database reference. */
    private DatabaseHelper db;
	/** History Activity. */
    private HistoryActivity activity;
	/** Start date of date range specified by user. */
    private Date start;
    /** End date of date range specified by user. */
    private Date end;
	/** Total withdrawal ammount. */
    private double withdrawal;
	/** Format tool to format start and end dates. */
    private SimpleDateFormat format;
	
	/**
	 * Constructor for class that initializes the SQLite db.
	 * 
	 * @param activity HistoryActivity
	 */
    public HistoryPresenter(HistoryActivity activity) {
        db = new DatabaseHelper(activity);
    }

    /**
     * Gets the total amount of withdrawals in the specified expense category.
     * 
     * @param histAct HistoryActivity
     * @param category Expense category to search for
     * @return Total withdrawal amount in the specified expense category
     */
    public double getSpending(HistoryActivity histAct, String category) {
        format = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
        try {
            start = format.parse(histAct.getStartDate());
            end = format.parse(histAct.getEndDate());
        } catch (ParseException e) {
			// TODO Auto-generated catch block
            e.printStackTrace();
        }
		
        ArrayList<Withdrawal> wdList = db.getAllAccountWithdrawals(LoginPresenter.loginUsername);
        withdrawal = 0;
        for (int i = 0; i < wdList.size(); i++) {
            String wdCat = wdList.get(i).getSpendSourceInfo();
            if (wdCat.equalsIgnoreCase(category)) {
                if (wdList.get(i).getTransactionDateEffective().compareTo(start) >= 0
						&& wdList.get(i).getTransactionDateEffective().compareTo(end) <= 0) {
                    withdrawal += wdList.get(i).getTransactionAmount();
                }
            }
        }
        if (category.equalsIgnoreCase("total")) {
            return getTotalSpending();
        }
        return withdrawal;
    }

    /**
     * Gets the total withdrawal amount over all of the users accounts.
     * 
     * @return Total withdrawal amount of all a specified users accounts
     */
    private double getTotalSpending() {
        ArrayList<Withdrawal> wdList = db.getAllAccountWithdrawals(LoginPresenter.loginUsername);
        double total = 0;
		
        for (int i = 0; i < wdList.size(); i++) {
            Date wdDate = wdList.get(i).getTransactionDateEffective();
            if (wdDate.compareTo(start) >= 0
					&& wdDate.compareTo(end) <= 0) {
                total += wdList.get(i).getTransactionAmount();
            }
        }

        return total;
    }

}
