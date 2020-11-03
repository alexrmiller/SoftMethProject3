package application;
/**
 * Savings class that extends Account Class
 * 
 * @authorMaudiel Romero , Alex Miller
 *
 */
import java.text.DecimalFormat;

import application.Account;
import application.Date;
public class Savings extends Account {
	private boolean isLoyal;

	DecimalFormat df = new DecimalFormat("#.##");
	
	/**
	 * Constructor for Savings type Account
	 * 
	 * @param holder
	 * @param balance
	 * @param dateOpen
	 * @param isLoyal
	 */
	public Savings(Profile holder, Double balance, Date dateOpen, boolean isLoyal) {
		super.Account(holder, balance, dateOpen);
		this.isLoyal = isLoyal;
	}
	/**
	 * Constructor for Savings type Account with only Profile
	 * 
	 * @param holder
	 */
	public Savings(Profile holder) {
		super.Account(holder);
	}

	/**
	 * Get's the monthly interest on account based on if loyal or not
	 * 
	 * @return 0.00005416667 or 0.0002916667 
	 */
	@Override
	public double monthlyInterest() {
		if (this.isLoyal == false) {
			String ret = df.format(this.getBalance() * (.0035/12));
			 return Double.parseDouble(ret);
		} else {
			String ret = df.format(this.getBalance() * (.0025/12));
			 return Double.parseDouble(ret);
		}
	}

	/**
	 * checks the balance quota to see if fee is applicable
	 * 
	 * @return fee refers to 5 or 0 depending on users balance
	 */
	@Override
	public double monthlyFee() {
		if (300 > super.getBalance()) {
			return 5;
		} else {
			return 0;
		}
	}

	/**
	 * @return "*Savings*" + super.toString() + "*special Savings account*" if loyal or "*Savings*" + super.toString() + "*" if not
	 */
	@Override
	public String toString() {
		return isLoyal ? "*Savings*" + super.toString() + "*special Savings account*" : "*Savings*" + super.toString() + "*";
	}
	
	/**
	 * prints out account formatted properly for exporting
	 */
	public String ExportString() {
		return isLoyal ? "S," + super.ExportString() + ",true" : "S," + super.ExportString() + ",false";
	}

}
