package com.UGAgrads.freddiefinance;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import android.util.Log;

public class HistoryPresenter {

	private ArrayList<String> accountsList;
	private DatabaseHelper db;
	private HistoryActivity activity;
	private Date start, end;
	private double withdrawal;
	private SimpleDateFormat format;
	
	public HistoryPresenter(HistoryActivity activity) {
		db = new DatabaseHelper(activity);
	}

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
		for(int i = 0; i < wdList.size(); i++) {
			String wdCat = wdList.get(i).getSpendSourceInfo();
			if (wdCat.equalsIgnoreCase(category)) {
				if (wdList.get(i).getTransactionDateEffective().compareTo(start) >=0
						&& wdList.get(i).getTransactionDateEffective().compareTo(end) <=0) {
					withdrawal += wdList.get(i).getTransactionAmount();
				}
			}
		}
		if (category.equalsIgnoreCase("total")) {
			return getTotalSpending();
		}
		return withdrawal;
	}

	private double getTotalSpending() {
		ArrayList<Withdrawal> wdList = db.getAllAccountWithdrawals(LoginPresenter.loginUsername);
		double total = 0;
		
		for(int i = 0; i < wdList.size(); i++) {
			Date wdDate = wdList.get(i).getTransactionDateEffective();
			if (wdDate.compareTo(start) >= 0
					&& wdDate.compareTo(end) <= 0) {
				total += wdList.get(i).getTransactionAmount();
			}
		}

		return total;
	}

}
