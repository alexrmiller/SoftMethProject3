package application;

/**
 * Account class is an object class that extends to other Account classes.
 * 
 * @authorMaudiel Romero , Alex Miller
 *
 */
import java.text.DecimalFormat;

public abstract class Account {
	private Profile holder;
	private double balance;
	private Date dateOpen;

	/**
	 * constructor for Account Object
	 * 
	 * @param holder
	 * @param balance
	 * @param dateOpen
	 */
	public void Account(Profile holder, double balance, Date dateOpen) {
		this.holder = holder;
		this.balance = balance;
		this.dateOpen = dateOpen;
	}
	/**
	 * constructor for Account Object with only holder information
	 * 
	 * @param holder
	 */
	public void Account(Profile holder) {
		this.holder=holder;
	}

	/**
	 * getter method to get the Account balance
	 * 
	 * @return this.balance
	 */
	public double getBalance() {
		return this.balance;
	}

	/**
	 * takes money out of an Account
	 * 
	 * @param amount refers to amount being taken out of Account
	 * 
	 * @return
	 */
	public void debit(double amount) {
		this.balance -= amount;
		return;
	}

	/**
	 * checks if two Account objects are equal to each other in both profile and extended classes
	 * 
	 * @param obj refers to Account being passed through
	 * @return true if Account and Account types are the same or false if otherwise
	 */
	public boolean equals(Object obj) {
		if(obj !=null && obj.getClass()== getClass()) {	
			return this.holder.equals(((Account)obj).holder);
		}
		return false;
	}

	/**
	 * adds money to an Account
	 * 
	 * @param amount refers to amount being added to this.Account
	 * @return
	 */
	public void credit(double amount) {
		this.balance += amount;
		return;
	}

	/**
	 * Prints the Account information in correct format
	 * 
	 * @return this.holder.toString() + "* $" + balance + "*" + this.dateOpen.toString()
	 */
	@Override
	public String toString() {
		return   this.holder.toString() + "* $" + balance + "*" + this.dateOpen.toString() ;
	}
	
	/**
	 * Prints the Account information in correct format for exporting
	 */
	public String ExportString() {
		return   this.holder.ExportString() + "," + balance + "," + this.dateOpen.toString() ;
	}

	/**
	 * 
	 * 
	 */
	public abstract double monthlyInterest();

	/**
	 * 
	 */
	public abstract double monthlyFee();

	/**
	 * getter method to get the Account Profile
	 * 
	 * @return this.holder
	 */
	public Profile getProfile() {
		return this.holder;
	}
	/**
	 * getter method to get the Account date opened
	 * 
	 * @return this.dateOpen
	 */
	public Date getDateOpen() {
		return this.dateOpen;
	}
	/**
	 * setter method to set the Account balance
	 * 
	 * @param newBalance is the Accounts new balance
	 * @return
	 */
	public void setBalance(double newBalance) {
		this.balance=newBalance;
		return;
	}

}
