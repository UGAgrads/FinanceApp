package com.UGAgrads.freddiefinance;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * The presenter for TransactionActivity
 * 
 * @author UGA Grads
 */
class TransactionPresenter {
    
    /**
     * Whether this transaction is a deposit or not
     */
    public static boolean isDeposit;
	
	/**
	 * The user's account to add a transaction to 
	 */
    public static Account userAccount;
	
	/**
	 * The user who is making a transaction 
	 */
    public static String user;
	
	/**
	 * The amount of the transaction
	 */
    public static String amount;
	
	/**
	 * The spending category or source of this transaction
	 */
    public static String sourceString;
	
	/**
	 * The spending category or source of this transaction
	 */
    public static String moneySource;
	
	/**
	 * The description for withdrawing
	 */
    public static String withdrawReason;
	
	/**
	 * The spending category of this transaction
	 */
    public static String expenseCategory;
	
	/**
	 * The date this transaction was made
	 */
    public static String dateEffective;
	
	/**
	 * A reference to the SQLite database
	 */
    public static DatabaseHelper db;
	
	/**
	 * The description of this transaction
	 */
    public static String transactionDescription = "";

	/**
	 * Method called when the user attempts to make a transaction.
	 * Verifies that the values entered are correct.
	 * 
	 * @param activity A reference to the transaction activity
	 * @param acct a reference to the account
	 * @return an value corresponding to an error type
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
     * Creates a transaction of type deposit
     * 
     * @param activity A reference to the TransactionActivity
     * @return the deposit being created
     */
    public static Transaction createDeposit(TransactionActivity activity) {
        db = new DatabaseHelper(activity);
        Deposit depo = new Deposit(Double.parseDouble(amount),
		        dateEffective, db.getUserByUsername(user),
		        userAccount, moneySource, transactionDescription);
        return depo;
    }

    /**
     * Creates a transaction of type withdrawal
     * 
     * @param activity A reference to the TransactionActivity
     * @returnthe withdrawal being created
     */
    public static Transaction createWithdrawal(TransactionActivity activity) {
        db = new DatabaseHelper(activity);
        Withdrawal drawl = new Withdrawal(Double.parseDouble(amount),
				dateEffective, db.getUserByUsername(user), userAccount,
				expenseCategory, transactionDescription);
        return drawl;
    }

    /**
     * Makes the change in the accoun'ts stored balance
     * 
     * @param acct The account being withdrawn or deposited from
     * @param accAmount the amount being changed
     * @param isDepositTest whether this is a deposit or not
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
     * Saves the transaction to the database
     * 
     * @param action A reference to the Transaction
     * @param acct The account this transaction is ovvurring in
     */
    public static void saveTransaction(Transaction action, Account acct) {
        db.addNewTransactionToDatabase(action);
        db.updateAccountInfo(acct);
		//update user info as well
    }

    /**
     * Changes and saves the transaction
     * 
     * @param activity A reference to the TransactionActivity
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

