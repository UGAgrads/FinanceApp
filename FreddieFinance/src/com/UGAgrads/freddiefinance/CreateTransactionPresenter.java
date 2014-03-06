package com.UGAgrads.freddiefinance;

import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

public class CreateTransactionPresenter {

	public static boolean isDeposit;
	public static Account userAccount;
	public static String user;
	public static String amount;
	public static String sourceString;
	public static String moneySource;
	public static String withdrawReason;
	public static Expense expenseCategory;
	public static DateFormat dateEffective;
	public static DatabaseHelper db;
	public static String transactionDescription = "";

	public static int verifyTransaction(TransactionActivity activity,
			Account acct) {
		db = new DatabaseHelper(activity);
		Log.d("mash", "made it");
		userAccount = acct;
		isDeposit = activity.isDeposit;
		user = activity.getIntent().getExtras().getString("user");
		// transactionDescription = ((EditText)
		// activity.findViewById(R.id.transactionDescription)).getText().toString();
		if (isDeposit) {
			amount = ((EditText) activity
					.findViewById(R.id.editTransDepositAmount)).getText()
					.toString();
			moneySource = ((Spinner) activity
					.findViewById(R.id.money_source_spinner)).getSelectedItem()
					.toString();
		} else {
			withdrawReason = ((EditText) activity
					.findViewById(R.id.editWithdrawalReason)).getText()
					.toString();
			amount = ((EditText) activity
					.findViewById(R.id.editTransWithdrawalAmount)).getText()
					.toString();
			// expenseCategory = Expense.((Spinner)
			// activity.findViewById(R.id.expense_category_spinner)).getSelectedItem().toString();
			if (Integer.parseInt(amount) > Integer.parseInt(acct.getBalance())) {
				return 4;
			}
		}
		if (amount.equals("")) {
			return 0;
		}
		if (isDeposit) {
			if (moneySource.equals("")) {
				return 2;
			}
		} else if (withdrawReason.equals("")) {
			return 3;
		}
		return 5;
	}

	public static Transaction createDeposit(TransactionActivity activity) {
		db = new DatabaseHelper(activity);
		Deposit depo = new Deposit(Double.parseDouble(amount), dateEffective,
				db.getUserByUsername(user), userAccount, moneySource,
				transactionDescription);
		return depo;
	}

	public static Transaction createWithdrawl(TransactionActivity activity) {
		db = new DatabaseHelper(activity);
		Withdrawal drawl = new Withdrawal(Double.parseDouble(amount),
				dateEffective, db.getUserByUsername(user), userAccount,
				expenseCategory, transactionDescription);
		return drawl;
	}

	public static void changeAccValues(Account acct, int amount,
			boolean isDeposit) {
		int res = 0;
		Log.d("mash", String.valueOf(acct == null));
		int startValue = Integer.parseInt(acct.getBalance());
		int modifier = (isDeposit) ? 1 : -1;
		res = startValue + amount * modifier;
		acct.setBalance(Integer.toString(res));
	}

	public static void saveTransaction(Transaction action, Account acct) {
		//db.addNewTransactionToDatabase(action);
		//db.updateAccountInfo(acct);
//	^^	commented out until bind at index 1 error null is resolved ^^
		acct.getTransactions().add(action);
	}

	public static void makeTheTransaction(TransactionActivity activity) {
		changeAccValues(userAccount, Integer.parseInt(amount), isDeposit);
		if (isDeposit) {
			saveTransaction(createDeposit(activity), userAccount);
		} else {
			saveTransaction(createWithdrawl(activity), userAccount);
		}
	}
}
