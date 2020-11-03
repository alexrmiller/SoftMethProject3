package application;

/**
 * Checking class is an extended Account class
 * 
 * @authorMaudiel Romero , Alex Miller
 *
 */
import java.text.DecimalFormat;

public class Checking extends Account {
	private boolean directDeposit;
	
	DecimalFormat df = new DecimalFormat("#.##");

	/**
	 * Constructor for checking account
	 * 
	 * @param holder
	 * @param balance
	 * @param dateOpen
	 * @param directDeposit
	 */
	public Checking(Profile holder, Double balance, Date dateOpen, boolean directDeposit) {
		super.Account(holder, balance, dateOpen);

		this.directDeposit = directDeposit;
	}
	
	/**
	 * Constructor for checking account with only holder information
	 * 
	 * @param holder
	 */
	public Checking(Profile holder) {
		super.Account(holder);
	}

	/**
	 * interest for month is 5% / 12 = 0.000416667
	 * 
	 * @return CheckingInterest refers to monthly interest
	 */
	@Override
	public double monthlyInterest(){
		String ret = df.format(this.getBalance() * (.0005/12));
		 return Double.parseDouble(ret);
	}

	/**
	 * Checks if account holds sufficient funds of >= 1500 or if directDeposit == true
	 * 
	 * @return 0 refers to balance is > 1500 or directDeposit == true else return 25
	 */
	@Override
	public double monthlyFee() {
		if (this.directDeposit==true) {
			return 0;
		}
		else if(1500 > super.getBalance()) {
			return 25;
		} else {
			return 0;
		}
	}

	/**
	 * prints out the Account formatted properly
	 * 
	 * @return if direct deposit "*Checking*" + super.toString() + "*direct deposit account*" ... if not "*Checking*" + super.toString() + "*"
	 */
	@Override
	public String toString() {
		return directDeposit ? "*Checking*" + super.toString() + "*direct deposit account*" : "*Checking*" + super.toString() + "*";
	}
	
	/**
	 * prints out account formatted properly for exporting
	 */
	public String ExportString() {
		return directDeposit ? "C," + super.ExportString() + ",true" : "C," + super.ExportString() + ",false";
	}
	
}
