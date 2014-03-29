package com.UGAgrads.freddiefinance;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * @author
 */
public class TransactionPresenter {
    
    /**
     * 
     */
    public static boolean isDeposit;
	
	/**
	 * 
	 */
    public static Account userAccount;
	
	/**
	 * 
	 */
    public static String user;
	
	/**
	 * 
	 */
    public static String amount;
	
	/**
	 * 
	 */
    public static String sourceString;
	
	/**
	 * 
	 */
    public static String moneySource;
	
	/**
	 * 
	 */
    public static String withdrawReason;
	
	/**
	 * 
	 */
    public static String expenseCategory;
	
	/**
	 * 
	 */
    public static String dateEffective;
	
	/**
	 * 
	 */
    public static DatabaseHelper db;
	
	/**
	 * 
	 */
    public static String transactionDescription = "";

	/**
	 * FILL THIS IN.
	 * @param activity FILL THIS IN!
	 * @param acct FILL THIS IN!
	 * @return FILL THIS IN!
	 */
    public static int verifyTransaction(TransactionActivity activity,
			Account acct) {
        db = new DatabaseHelper(activity);
        userAccount = acct;
        isDeposit = activity.isDeposit;
        user = userAccount.getAccountOwner();
        if (isDeposit) {
            amount = ((EditText) activity
					.findViewById(R.id.editTransDepositAmount))
					.getText()
					.toString();
            moneySource = ((Spinner) activity
					.findViewById(R.id.money_source_spinner))
					.getSelectedItem()
					.toString();
					
            dateEffective = ((Button) activity
			        .findViewById(R.id.editDateEffective))
			        .getText()
			        .toString();
            transactionDescription = ((EditText) activity
			        .findViewById(R.id.editTransactionDescription))
			        .getText()
			        .toString();
        } else {
            withdrawReason = ((EditText) activity
					.findViewById(R.id.editWithdrawalReason))
					.getText()
					.toString();
            amount = ((EditText) activity
					.findViewById(R.id.editTransWithdrawalAmount))
					.getText()
					.toString();
            transactionDescription = withdrawReason;
            dateEffective = ((Button) activity
			        .findViewById(R.id.editDateEffective))
			        .getText()
			        .toString();
            expenseCategory = ((Spinner) activity
			        .findViewById(R.id.expense_category_spinner))
			        .getSelectedItem()
			        .toString();
            if (Double.parseDouble(amount) > Double.parseDouble(acct.getBalance())) {
                return 4;
            }
        }
        if (amount.equals("")) {
            return 0;
        }
        if (isDeposit) {
            if (transactionDescription.equals("")) {
                return 2;
            }
        } else if (withdrawReason.equals("")) {
            return 3;
        } 
        if (dateEffective.equals("Pick a date")) {
            return 5;
        }
        return 6;
    }
    
    /**
     * @param activity FILL THIS IN!
     * @return FILL THIS IN!
     */
    public static Transaction createDeposit(TransactionActivity activity) {
        db = new DatabaseHelper(activity);
        Deposit depo = new Deposit(Double.parseDouble(amount),
		        dateEffective, db.getUserByUsername(user),
		        userAccount, moneySource, transactionDescription);
        return depo;
    }

    /**
     * @param activity FILL THIS IN!
     * @return FILL THIS IN!
     */
    public static Transaction createWithdrawal(TransactionActivity activity) {
        db = new DatabaseHelper(activity);
        Withdrawal drawl = new Withdrawal(Double.parseDouble(amount),
				dateEffective, db.getUserByUsername(user), userAccount,
				expenseCategory, transactionDescription);
        return drawl;
    }

    /**
     * @param acct FILL THIS IN!
     * @param accAmount FILL THIS IN!
     * @param isDepositTest FILL THIS IN!
     */
    public static void changeAccValues(Account acct, double accAmount,
			boolean isDepositTest) {
        double res = 0;
        double startValue = Double.parseDouble(acct.getBalance());
        double modifier = (isDepositTest) ? 1 : -1;
        res = startValue + accAmount * modifier;
        acct.setBalance(Double.toString(res));
    }

    /**
     * @param action FILL THIS IN!
     * @param acct FILL THIS IN!
     */
    public static void saveTransaction(Transaction action, Account acct) {
        db.addNewTransactionToDatabase(action);
        db.updateAccountInfo(acct);
		//update user info as well
    }

    /**
     * @param activity FILL THIS IN!
     */
    public static void makeTheTransaction(TransactionActivity activity) {
        changeAccValues(userAccount, Double.parseDouble(amount), isDeposit);
        if (isDeposit) {
            saveTransaction(createDeposit(activity), userAccount);
        } else {
            saveTransaction(createWithdrawal(activity), userAccount);
        }
    }
}
