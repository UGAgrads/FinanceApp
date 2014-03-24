package com.UGAgrads.freddiefinance;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.util.Log;

public class HistoryPresenter {

	private static ArrayList<String> accountsList;
	private static DatabaseHelper db;
	private HistoryActivity activity;
	private static String start, end;
	private static double withdrawal;
	
	public static void initDB(HistoryActivity activity) {
		db = new DatabaseHelper(activity);
	}

	public static double getSpending(HistoryActivity histAct, String category) {
		start = histAct.getStartDate();
		end = histAct.getEndDate();
		
		if (category.equalsIgnoreCase("food")) {
			try {
				return getFoodSpending();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (category.equalsIgnoreCase("rent")) {
			try {
				return getRentSpending();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (category.equalsIgnoreCase("clothing")) {
			try {
				return getClothingSpending();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (category.equalsIgnoreCase("entertainment")) {
			try {
				return getEntertainmentSpending();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return withdrawal;
	}

	public static double getTotalSpending() {

		ArrayList<Withdrawal> wd =
				db.getAllAccountWithdrawals(LoginPresenter.loginUsername);
		Log.d("mashal", String.valueOf(withdrawal));
		for(int i = 0; i < wd.size(); i++) {
			withdrawal += wd.get(i).getTransactionAmmount();
		}
		Log.d("mashal", String.valueOf(withdrawal));
		return withdrawal;
	}

	private static double getFoodSpending() throws ParseException {
		double foodSpending = 0;
		ArrayList<Withdrawal> wd =
				db.getAllAccountWithdrawals(LoginPresenter.loginUsername);
		for(int i = 0; i < wd.size(); i++) {
			String dateWD = wd.get(i).getTransactionDateEffective();
			SimpleDateFormat sdfWD = new SimpleDateFormat("mm/dd/yyyy");
			Date wdDate = sdfWD.parse(dateWD);
			
			SimpleDateFormat endSdf = new SimpleDateFormat("mm/dd/yyyy");
			Date endDate = endSdf.parse(end);
			
			SimpleDateFormat startSdf = new SimpleDateFormat("mm/dd/yyyy");
			Date startDate = startSdf.parse(start);
			
			if ((wdDate.after(startDate))
					&& endDate.after(wdDate)) {
				if (wd.get(i).getWithdrawalMoneyExpense().equals("Food")) {
					foodSpending += wd.get(i).getTransactionAmmount();
				}
			}		
		}
		return foodSpending;
	}
	
	private static double getRentSpending() throws ParseException {
		double spending = 0;
		ArrayList<Withdrawal> wd =
				db.getAllAccountWithdrawals(LoginPresenter.loginUsername);
		for(int i = 0; i < wd.size(); i++) {
			String dateWD = wd.get(i).getTransactionDateEffective();
			SimpleDateFormat sdfWD = new SimpleDateFormat("mm/dd/yyyy");
			Date wdDate = sdfWD.parse(dateWD);
			
			SimpleDateFormat endSdf = new SimpleDateFormat("mm/dd/yyyy");
			Date endDate = endSdf.parse(end);
			
			SimpleDateFormat startSdf = new SimpleDateFormat("mm/dd/yyyy");
			Date startDate = startSdf.parse(start);
			
			if ((wdDate.after(startDate))
					&& endDate.after(wdDate)) {
				if (wd.get(i).getWithdrawalMoneyExpense().equals("Rent")) {
					spending += wd.get(i).getTransactionAmmount();
				}
			}		
		}
		return spending;
	}
	
	private static double getEntertainmentSpending() throws ParseException {
		double spending = 0;
		ArrayList<Withdrawal> wd =
				db.getAllAccountWithdrawals(LoginPresenter.loginUsername);
		for(int i = 0; i < wd.size(); i++) {
			String dateWD = wd.get(i).getTransactionDateEffective();
			SimpleDateFormat sdfWD = new SimpleDateFormat("mm/dd/yyyy");
			Date wdDate = sdfWD.parse(dateWD);
			
			SimpleDateFormat endSdf = new SimpleDateFormat("mm/dd/yyyy");
			Date endDate = endSdf.parse(end);
			
			SimpleDateFormat startSdf = new SimpleDateFormat("mm/dd/yyyy");
			Date startDate = startSdf.parse(start);
			
			if ((wdDate.after(startDate))
					&& endDate.after(wdDate)) {
				if (wd.get(i).getWithdrawalMoneyExpense().equals("Entertainment")) {
					spending += wd.get(i).getTransactionAmmount();
				}
			}		
		}
		return spending;
	}
	
	private static double getClothingSpending() throws ParseException {
		double spending = 0;
		ArrayList<Withdrawal> wd =
				db.getAllAccountWithdrawals(LoginPresenter.loginUsername);
		for(int i = 0; i < wd.size(); i++) {
			String dateWD = wd.get(i).getTransactionDateEffective();
			SimpleDateFormat sdfWD = new SimpleDateFormat("mm/dd/yyyy");
			Date wdDate = sdfWD.parse(dateWD);
			
			SimpleDateFormat endSdf = new SimpleDateFormat("mm/dd/yyyy");
			Date endDate = endSdf.parse(end);
			
			SimpleDateFormat startSdf = new SimpleDateFormat("mm/dd/yyyy");
			Date startDate = startSdf.parse(start);
			
			if ((wdDate.after(startDate))
					&& endDate.after(wdDate)) {
				if (wd.get(i).getWithdrawalMoneyExpense().equals("Clothing")) {
					spending += wd.get(i).getTransactionAmmount();
				}
			}		
		}
		return spending;
	}
}
