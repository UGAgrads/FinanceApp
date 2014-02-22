package com.UGAgrads.freddiefinance;

public class Transaction {
	private static User moneySender;
	private static User moneyReciever;
	private static Account sendingAccount;
	private static Account recievingAccount;
	private static long ammountTransfered;
	private static String transactionReferenceNumber;
	
	Transaction(User transactionSender, User transactionReciever, Account transactionSendingAccount,
			Account transactionRecievingAccount, long transactionMoneyAmmount){
		moneySender = transactionSender;
		moneyReciever = transactionReciever;
		sendingAccount = transactionSendingAccount;
		recievingAccount = transactionRecievingAccount;
		ammountTransfered = transactionMoneyAmmount;
		transactionReferenceNumber =  "" + ((int) (Math.random() * 1000));
		
	}
	
	private void completeTransaction(){
		
	}
	
	
}
