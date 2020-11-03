package application;

/**
 * AccountDatabase class is a container class that holds Accounts in an array.
 * 
 * @authorMaudiel Romero , Alex Miller
 *
 */
import java.text.DecimalFormat;

public class AccountDatabase {

	private Account[] accounts;
	private int size;
	
	DecimalFormat df = new DecimalFormat("#.##");

	/**
	 * constructor for AccountDatabase automatically start with size of 0 and 5 empty spots
	 *
	 */
	public AccountDatabase() {
		this.accounts = new Account[5];
		this.size = 0;
	}

	/**
	 * finds the given parameter account within the array 
	 * 
	 * @param account
	 * @return i if Account is found at index i or -1 if Account is not found in array
	 *
	 */
	private int find(Account account) {
		for (int i = 0; i < size; i++) {
			if (accounts[i].equals(account)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * grows the array capacity by 5
	 *
	 *@return
	 *
	 */
	private void grow() {
		Account[] newAcc = new Account[this.accounts.length + 5];
		for (int i = 0; i < this.accounts.length; i++) {
			newAcc[i] = accounts[i];
		}
		accounts = newAcc;
		return;
	}

	/**
	 * Adds Account account to the array of accounts
	 * 
	 * @param account
	 * @return true if added false if account already exists in array
	 */
	public boolean add(Account account) {
		int location = find(account);
		if (location == -1) {
			if (this.size == accounts.length) {
				grow();
			}
			accounts[size] = account;
			size++;
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Removes Account account from the array of accounts
	 * 
	 * @param account
	 * @return true if account is removed false if account is not in the array
	 */
	public boolean remove(Account account) {
		int location = find(account);
		if (location != -1) {
			// if found then remove it
			accounts[location] = accounts[this.size - 1];
			accounts[this.size - 1] = null;
			this.size--;
			return true;
		} else {
			// if not found then can't remove
			return false;
		}
	}

	/**
	 * Adds the amount given to the Account given 
	 * 
	 * @param account
	 * @param amount
	 * @return true if amount was added false if account doesn't exist in array
	 */
	public boolean deposit(Account account, double amount) {
		int i = find(account);
		if (i == -1) {
			return false;
		}else {
			accounts[i].credit(amount);
			return true;
		}
	}

	/**
	 * removes the amount given from the Account given 
	 * 
	 * @param account
	 * @param amount
	 * @return return 0: withdraw successful, 1: insufficient funds, -1: account doesn't exist in array
	 */
	public int withdraw(Account account, double amount) {
		int i = find(account);
		if (i == -1) {
			return -1;
		} else if (accounts[i].getBalance() < amount) {
			return 1;
		} else {
			if(accounts[i] instanceof MoneyMarket) {
				((MoneyMarket) accounts[i]).debit(amount);
				((MoneyMarket) accounts[i]).withdrawal2();
				return 0;
			}
			accounts[i].debit(amount);
			return 0;
		}
	}

	/**
	 * sorts the accounts in the array in descending order according to date open
	 * 
	 * @return
	 */
	private void sortByDateOpen() {
		Account account = null;
		for (int i = 0; i < size; i++) {
			for (int j = i; j < size; j++) {
				if (accounts[i].getDateOpen().compareTo(accounts[j].getDateOpen()) == -1) {
					// switch the two
					account = accounts[i];
					accounts[i] = accounts[j];
					accounts[j] = account;
				}
			}
		}
		return;
	}

	/**
	 * sorts the accounts in the array in descending order according to date open
	 * 
	 * @return
	 */
	private void sortByLastName() {
		Account account = null;
		for (int i = 0; i < size; i++) {
			for (int j = i; j < size; j++) {
				if (accounts[i].getProfile().getLastName().compareTo(accounts[j].getProfile().getLastName()) < 0) {
					account = accounts[i];
					accounts[i] = accounts[j];
					accounts[j] = account;
				}
			}
		}
		return;
	}

	/**
	 * prints the accounts in the array in descending order according to accounts' date open
	 * 
	 * @return
	 * 
	 */
	public String printByDateOpen() {
		if(size==0) {
			return "Database is empty." ;
		}
		sortByDateOpen();
		StringBuilder str = new StringBuilder("--Printing statements by date opened-- \n");
		for (int i = 0; i < size; i++) {
			str.append(accounts[i].toString() +"\n");
			str.append("-interest: $ " + accounts[i].monthlyInterest() + "\t");
			str.append("-fee: $ " + accounts[i].monthlyFee() + "\t");
			double newBal = accounts[i].getBalance()-accounts[i].monthlyFee()+accounts[i].monthlyInterest();
			String formatted = df.format(newBal);
			double newBalance = Double.parseDouble(formatted);
			accounts[i].setBalance(newBalance);
			str.append("-new balance: $ " + newBalance + "\n");
		}
		str.append("--end of printing--");
		return str.toString();
	}

	/**
	 * prints the accounts in the array in descending order according to accounts' last name
	 * 
	 * @return
	 */
	public String printByLastName() {
		if(size==0) {
			return "Database is empty.";
		}
		sortByLastName();
		StringBuilder str = new StringBuilder("--Printing statements by last name-- \n");
		for (int i = 0; i < size; i++) {
			str.append(accounts[i].toString() +"\n");
			str.append("-interest: $ " + accounts[i].monthlyInterest()+ "\t");
			str.append("-fee: $ " + accounts[i].monthlyFee()+ "\t");
			double newBal = accounts[i].getBalance()-accounts[i].monthlyFee()+accounts[i].monthlyInterest();
			String formatted = df.format(newBal);
			double newBalance = Double.parseDouble(formatted);
			accounts[i].setBalance(newBalance);
			str.append("-new balance: $ " + newBalance + "\n");
		}
		str.append("--end of printing--");
		return str.toString();
	}

	/**
	 * prints the accounts in the array in no certain order
	 * 
	 * @return
	 */
	public String printAccounts() {
		if(size==0) {
			return "Database is empty";
		}
		StringBuilder str = new StringBuilder("--Listing accounts in the database-- \n");		
		for (int i = 0; i < size; i++) {
			str.append(accounts[i].toString() + "\n");
		}
		str.append("--end of listing--");
		return str.toString();
	}
	
	public String exportAccounts() {
		StringBuilder str = new StringBuilder("");
		if(size==0) {
			return str.toString();
		}		
		for (int i = 0; i < size; i++) {
			str.append(accounts[i].ExportString() + "\n");
		}
		return str.toString();
	}
	
}
