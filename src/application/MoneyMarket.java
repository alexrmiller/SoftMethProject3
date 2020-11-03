package application;
/**
 * MoneyMarket class is an extended Account class
 * 
 * @author Maudiel Romero, Alex Miller
 *
 */
import java.text.DecimalFormat;

import application.Account;
import application.Date;

public class MoneyMarket extends Account {
	private int withdrawals;
	
	DecimalFormat df = new DecimalFormat("#.##");

	/**
	 * Constructor for MoneyMarket Account
	 * 
	 * @param holder
	 * @param balance
	 * @param dateOpen
	 * @param withdrawals
	 */
	public MoneyMarket(Profile holder, Double balance, Date dateOpen, int withdrawals) {
		super.Account(holder, balance, dateOpen);
		this.withdrawals = withdrawals;
	}

	/**
	 * Constructor for MoneyMarket Account using only holder
	 * 
	 * @param holder
	 */
	public MoneyMarket(Profile holder) {
		super.Account(holder);
	}
	
	/**
	 * setter to increase the MoneyMarket's withdrawal amount by 1
	 * @return
	 */
	public void withdrawal2() {
		this.withdrawals++;
		return;
	}

	/**
	 * 
	 * @return ret refers to interest double 0.0002083
	 */
	@Override
	public double monthlyInterest() {
		 String ret = df.format(this.getBalance() * (.0065/12));
		 return Double.parseDouble(ret);

	}

	/**
	 * adds fee based on sufficient funds and withdrawn more than 7 times
	 * 
	 * @return fee refers to monthly fee of 12 or 0 depending on users balance
	 */
	@Override
	public double monthlyFee() {
		if(this.withdrawals>6) {
			return 12;
		}
		else if (2500 > super.getBalance()) {
			return 12;
		} else {
			return 0;
		}
	}

	/**
	 * @return "*Money Market*" + super.toString() + "*" + withdrawals + " withdrawals*"
	 */
	@Override
	public String toString() {
		return "*Money Market*" + super.toString() + "*" + withdrawals + " withdrawals*";
	}
	
	/**
	 * prints out account formatted properly for exporting
	 */
	public String ExportString() {
		return "M," + super.ExportString() + "," + withdrawals;
	}

}
