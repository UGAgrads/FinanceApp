package com.UGAgrads.freddiefinance;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * @author
 */
public class HistoryPresenter {

    /** FILL THIS IN! */
    private ArrayList<String> accountsList;
	/** FILL THIS IN! */
    private DatabaseHelper db;
	/** FILL THIS IN! */
    private HistoryActivity activity;
	/** FILL THIS IN! */
    private Date start, end;
	/** FILL THIS IN! */
    private double withdrawal;
	/** FILL THIS IN! */
    private SimpleDateFormat format;
	
	/**
	 * @param activity FILL THIS IN!
	 */
    public HistoryPresenter(HistoryActivity activity) {
        db = new DatabaseHelper(activity);
    }

    /**
     * @param histAct FILL THIS IN!
     * @param category FILL THIS IN!
     * @return FILL THIS IN!
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
     * @return FILL THIS IN!
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
