package com.UGAgrads.freddiefinance;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HistoryPresenter {

	private static ArrayList<String> accountsList;
	private static DatabaseHelper db;
	private String start, end;
	private double withdrawal;
	
	public static ArrayList<String> getAccounts(UserHomeActivity activity) {
		
		db = new DatabaseHelper(activity);
		
		// The data to show
		accountsList = new ArrayList<String>();

		List<Account> accounts = db
				.getAccountsByOwner(LoginPresenter.loginUsername);
		for (Account account : accounts) {
			accountsList.add(account.getAccountName());
		}

		Collections.sort(accountsList);

		/** create new account always at end */
		accountsList.add("CREATE NEW ACCOUNT");
		
		return accountsList;

	}

	public double getSpending(HistoryActivity histAct, String category) {
		start = histAct.getStartDate();
		end = histAct.getEndDate();
		
		if (category.equalsIgnoreCase("food")) {
			return getFoodSpending();
		}
		if (category.equalsIgnoreCase("rent")) {
			return getRentSpending();
		}
		if (category.equalsIgnoreCase("clothing")) {
			return getClothingSpending();
		}
		if (category.equalsIgnoreCase("entertainment")) {
			return getEntertainmentSpending();
		}
		
		return withdrawal;
	}
	
	public double getTotalSpending() {
		
		ArrayList<Withdrawal> wd =
				db.getAllAccountWithdrawals(LoginPresenter.loginUsername);
		for(int i = 0; i < wd.size(); i++) {
			withdrawal += wd.get(i).getTransactionAmmount();
		}
		return withdrawal;
	}
	
	private double getFoodSpending() {
		double foodSpending = 0;
		ArrayList<Withdrawal> wd =
				db.getAllAccountWithdrawals(LoginPresenter.loginUsername);
		for(int i = 0; i < wd.size(); i++) {
			if ((wd.get(i).getTransactionDateEffective().compareTo(start) > 0)
					&& wd.get(i).getTransactionDateEffective().compareTo(end) < 0) {
				if (wd.get(i).getWithdrawalMoneyExpense().equals("Food")) {
					foodSpending += wd.get(i).getTransactionAmmount();
				}
			}		
		}
		return foodSpending;
	}
	
	private double getRentSpending() {
		double spending = 0;
		ArrayList<Withdrawal> wd =
				db.getAllAccountWithdrawals(LoginPresenter.loginUsername);
		for(int i = 0; i < wd.size(); i++) {
			if ((wd.get(i).getTransactionDateEffective().compareTo(start) > 0)
					&& wd.get(i).getTransactionDateEffective().compareTo(end) < 0) {
				if (wd.get(i).getWithdrawalMoneyExpense().equals("Food")) {
					spending += wd.get(i).getTransactionAmmount();
				}
			}		
		}
		return spending;
	}
	
	private double getEntertainmentSpending() {
		double spending = 0;
		ArrayList<Withdrawal> wd =
				db.getAllAccountWithdrawals(LoginPresenter.loginUsername);
		for(int i = 0; i < wd.size(); i++) {
			if ((wd.get(i).getTransactionDateEffective().compareTo(start) > 0)
					&& wd.get(i).getTransactionDateEffective().compareTo(end) < 0) {
				if (wd.get(i).getWithdrawalMoneyExpense().equals("Food")) {
					spending += wd.get(i).getTransactionAmmount();
				}
			}		
		}
		return spending;
	}
	
	private double getClothingSpending() {
		double spending = 0;
		ArrayList<Withdrawal> wd =
				db.getAllAccountWithdrawals(LoginPresenter.loginUsername);
		for(int i = 0; i < wd.size(); i++) {
			if ((wd.get(i).getTransactionDateEffective().compareTo(start) > 0)
					&& wd.get(i).getTransactionDateEffective().compareTo(end) < 0) {
				if (wd.get(i).getWithdrawalMoneyExpense().equals("Food")) {
					spending += wd.get(i).getTransactionAmmount();
				}
			}		
		}
		return spending;
	}
}
