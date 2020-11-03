/**
 * Authors: Maudiel Romero & Alex Miller 
**/
package application;

import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import java.io.File;
import java.io.FileWriter;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.MenuBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import javafx.scene.control.TextField;

public class SampleController {
	AccountDatabase database = new AccountDatabase();
	@FXML
	private BorderPane Borderpain;
	// TAB 1 AND ALL PROPERTIES EXCEPT TEXT AREA
	@FXML
	private DatePicker Tab1Date;
	@FXML
	private TextField FirstName;

	@FXML
	private TextField Lastname;

	@FXML
	private TextField Balance;

	@FXML
	private RadioButton Checking;

	@FXML
	private RadioButton Savings;

	@FXML
	private RadioButton MoneyMarket;

	@FXML
	private CheckBox DirectDeposit;

	@FXML
	private CheckBox LoyalCustomer;

	@FXML
	private Button OpenAccount;

	@FXML
	private Button CloseAccount;

	@FXML
	private Button Clear;

	// TAB 2 AND ALL PROPERTIES EXCEPT TEXT AREA

	@FXML
	private TextField Tab2FirstName;

	@FXML
	private TextField Tab2LastName;

	@FXML
	private TextField Tab2Balance;

	@FXML
	private RadioButton Tab2MoneyMarket;

	@FXML
	private RadioButton Tab2Checking;

	@FXML
	private RadioButton Tab2Savings;

	@FXML
	private Button Deposite;

	@FXML
	private Button Withdraw;

	// TAB 3 AND ALL PROPERTIES EXCEPT TEXT AREA
	@FXML
	private MenuItem Tab3Export;

	@FXML
	private MenuItem Tab3accounts;

	@FXML
	private MenuItem Tab3Lastname;

	@FXML
	private MenuItem tab3Date;

	@FXML
	private MenuBar MenuBar;

	@FXML
	private MenuItem Tab3Date;

	@FXML
	private MenuItem Tab3PrintBylastname;

	@FXML
	private TextArea TextArea;

	/**
	 * Disables/ Enables the radio button for direct deposit if savings is selected
	 * or not on tab 1
	 **/
	@FXML
	void RaddioToCheckBoxToggle(ActionEvent event) {
		Savings.selectedProperty().addListener((v, oldValue, newValue) -> {
			if (Savings.isSelected()) {
				DirectDeposit.setDisable(true);
			} else {
				DirectDeposit.setDisable(false);
			}
		});
	}

	/**
	 * on click event for clear button (tab 1) that clears the values placed in the
	 * inputs in tab 1
	 **/
	@FXML
	void OnClear(MouseEvent event) {
		FirstName.clear();
		Lastname.clear();
		Tab1Date.setValue(null);
		;
		Balance.clear();
		Checking.setSelected(false);
		Savings.setSelected(false);
		MoneyMarket.setSelected(false);
		DirectDeposit.setSelected(false);
		LoyalCustomer.setSelected(false);
		TextArea.clear();

	}

	/**
	 * on click event for close button (tab 1) given the data, it closes an account
	 * in the account database
	 **/
	@FXML
	void OnCloseAccount(MouseEvent event) {
		String Fname = FirstName.getText();
		String Lname = Lastname.getText();
		if (!CheckValidStrings1()) {
			return;
		}

		Profile prof = new Profile(Fname, Lname);
		if (Checking.isSelected() == true) {
			Checking check = new Checking(prof);
			if (database.remove(check) == true) {
				TextArea.setText("Account closed and removed from the database.");
			} else if (database.remove(check) == false) {
				TextArea.setText("Account Does not exists");
			}
		} else if (Savings.isSelected() == true) {
			Savings save = new Savings(prof);
			if (database.remove(save) == true) {
				TextArea.setText("Account closed and removed from the database.");
			} else if (database.remove(save) == false) {
				TextArea.setText("Account Does not exists");
			}
		} else if (MoneyMarket.isSelected() == true) {
			MoneyMarket money = new MoneyMarket(prof);
			if (database.remove(money) == true) {
				TextArea.setText("Account closed and removed from the database.");
			} else if (database.remove(money) == false) {
				TextArea.setText("Account Does not exists");
			}
		} else {
			TextArea.setText("Invalid: must pick Type of account.");
		}
	}

	/**
	 * if check box button is checked it sets values for direct deposit (tab 1)
	 **/
	@FXML
	public boolean OnDirectDeposit() {
		if (DirectDeposit.isSelected() == true) {
			return true;
		} else
			return false;
	}

	/**
	 * if check box button is checked it sets the value for loyal customer (tab 1)
	 **/
	@FXML
	public boolean OnLoyalCustomer() {
		if (LoyalCustomer.isSelected() == true) {
			return true;
		} else
			return false;
	}

	/**
	 * on click event for selecting the money market radio button, disables the
	 * check boxes for direct deposit or loyal customer (tab 1)
	 **/
	@FXML
	void OnMoneyMarket(MouseEvent event) {
		LoyalCustomer.setDisable(true);
		DirectDeposit.setDisable(true);
		LoyalCustomer.setSelected(false);
		DirectDeposit.setSelected(false);
		MoneyMarket.selectedProperty().addListener((v, oldValue, newValue) -> {
			if (MoneyMarket.isSelected()) {
				LoyalCustomer.setDisable(true);
				DirectDeposit.setDisable(true);
				LoyalCustomer.setSelected(false);
				DirectDeposit.setSelected(false);
			} else {
				LoyalCustomer.setDisable(false);
				DirectDeposit.setDisable(false);
			}
		});
	}

	/**
	 * on click event for the open button, it opens an account in the account
	 * database given valid data
	 **/
	@FXML
	void OnOpenAccount(MouseEvent event) {
		String balance = Balance.getText();
		if (Tab1Dateval() == null) {
			return;
		}
		if (!CheckValidStrings1()) {
			return;
		} else if (!CheckValidDouble(balance)) {
			return;
		}

		Date date = Tab1Dateval();
		double bal = Double.parseDouble(balance);
		String Fname = FirstName.getText();
		String Lname = Lastname.getText();
		boolean directdeposit = OnDirectDeposit();
		boolean loyalcustomer = OnLoyalCustomer();

		Profile prof = new Profile(Fname, Lname);
		if (Checking.isSelected() == true) {
			Checking check = new Checking(prof, bal, date, directdeposit);
			if (database.add(check) == true) {
				TextArea.setText(check.toString());
			} else {
				TextArea.setText("Account Already exists");
			}
		} else if (Savings.isSelected() == true) {
			Savings save = new Savings(prof, bal, date, loyalcustomer);
			if (database.add(save) == true) {
				TextArea.setText(save.toString());
			} else {
				TextArea.setText("Account Already exists");
			}
		} else if (MoneyMarket.isSelected() == true) {
			MoneyMarket money = new MoneyMarket(prof, bal, date, 0);
			if (database.add(money) == true) {
				TextArea.setText(money.toString());
			} else {
				TextArea.setText("Account Already exists");
			}
		} else {
			TextArea.setText("Invalid: must pick Type of account.");
		}

	}

	/**
	 * on click event for the savings radio button, disables all options but direct
	 * deposit
	 **/
	@FXML
	void OnSavings(MouseEvent event) {
		DirectDeposit.setDisable(true);
		DirectDeposit.setSelected(false);
		Savings.selectedProperty().addListener((v, oldValue, newValue) -> {
			if (Savings.isSelected()) {
				DirectDeposit.setDisable(true);
				DirectDeposit.setSelected(false);
			} else {
				DirectDeposit.setDisable(false);
			}
		});
	}

	/**
	 * on click event for the checking radio button, disables all options but loyal
	 * customer
	 **/
	@FXML
	void Onchecking(MouseEvent event) {
		LoyalCustomer.setDisable(true);
		LoyalCustomer.setSelected(false);
		Checking.selectedProperty().addListener((v, oldValue, newValue) -> {
			if (Checking.isSelected()) {
				LoyalCustomer.setDisable(true);
				LoyalCustomer.setSelected(false);
			} else {
				LoyalCustomer.setDisable(false);
			}
		});
	}

	/**
	 * Takes the date from the time picker and converts it to our Date type and
	 * ensures validity
	 **/
	@FXML
	public Date Tab1Dateval() {
		LocalDate Date = Tab1Date.getValue();
		if (Date != null) {
			String StringDate = Date.toString();
			String[] date = StringDate.split("-");
			Date Date2 = new Date(Integer.parseInt(date[1]), Integer.parseInt(date[2]), Integer.parseInt(date[0]));
			if (!Date2.isValid()) {
				TextArea.setText("Invalid date");
				return null;
			}
			return Date2;
		} else
			TextArea.setText("Please input a date");
		return null;
	}

//--------------------------- This is all event Handling for tab 2 

	/**
	 * on click event for deposit button, adds the amount of money to the account
	 * given valid data
	 **/
	@FXML
	void OnTab2Deposit(MouseEvent event) {
		String firstname = Tab2FirstName.getText();
		String lastname = Tab2LastName.getText();
		String balance = Tab2Balance.getText();
		if (!CheckValidStrings2()) {
			return;
		}
		if (!CheckValidDouble(balance)) {
			return;
		}
		double bal = Double.parseDouble(balance);
		Profile prof = new Profile(firstname, lastname);
		if (Tab2Checking.isSelected() == true) {
			Checking check = new Checking(prof);
			if (database.deposit(check, bal) == true) {
				TextArea.setText(bal + " deposited to CHECKING.");
			} else if (database.deposit(check, bal) == false) {
				TextArea.setText("Account Does Not exists");
			}
		} else if (Tab2Savings.isSelected() == true) {
			Savings save = new Savings(prof);
			if (database.deposit(save, bal) == true) {
				TextArea.setText(bal + " deposited to SAVING.");
			} else if (database.deposit(save, bal) == false) {
				TextArea.setText("Account Does Not exists");
			}
		} else if (Tab2MoneyMarket.isSelected() == true) {
			MoneyMarket money = new MoneyMarket(prof);
			if (database.deposit(money, bal) == true) {
				TextArea.setText(bal + " deposited to MONEYMARKET.");
			} else if (database.deposit(money, bal) == false) {
				TextArea.setText("Account Does Not exists");
			}
		} else {
			TextArea.setText("Invalid: must pick Type of account.");
		}
	}

	/**
	 * on click event for withdraw button, removes the amount of money to the
	 * account given valid data
	 **/
	@FXML
	void OnTab2Withdraw(MouseEvent event) {
		String firstname = Tab2FirstName.getText();
		String lastname = Tab2LastName.getText();
		String balance = Tab2Balance.getText();
		if (!CheckValidStrings2()) {
			return;
		} else if (!CheckValidDouble(balance)) {
			return;
		}
		double bal = Double.parseDouble(balance);

		Profile prof = new Profile(firstname, lastname);
		if (Tab2Checking.isSelected() == true) {
			Checking check = new Checking(prof);

			if (database.withdraw(check, bal) == 0) {
				TextArea.setText(bal + " withdrawn from CHECKING.");
			} else if (database.withdraw(check, bal) == -1) {
				TextArea.setText("Account Does not exists");
			} else if (database.withdraw(check, bal) == 1) {
				TextArea.setText("Insuficient funds");
			}
		} else if (Tab2Savings.isSelected() == true) {
			Savings save = new Savings(prof);

			if (database.withdraw(save, bal) == 0) {
				TextArea.setText(bal + " withdrawn from SAVINGS.");
			} else if (database.withdraw(save, bal) == -1) {
				TextArea.setText("Account Does not exists");
			} else if (database.withdraw(save, bal) == 1) {
				TextArea.setText("Insuficient funds");
			}
		} else if (Tab2MoneyMarket.isSelected() == true) {
			MoneyMarket money = new MoneyMarket(prof);

			if (database.withdraw(money, bal) == 0) {
				TextArea.setText(bal + " withdrawn from MONEYMARKET.");
			} else if (database.withdraw(money, bal) == -1) {
				TextArea.setText("Account Does not exists");
			} else if (database.withdraw(money, bal) == 1) {
				TextArea.setText("Insuficient funds");
			}
		} else {
			TextArea.setText("Invalid: must pick Type of account.");
		}
	}

// This is all event Handling for Tab 3

	/**
	 * imports a given txt file to be read and parsed and inputted into the database
	 **/
	@FXML
	void Tab3Import(ActionEvent event) {
		FileChooser fc = new FileChooser();
		FileChooser.ExtensionFilter ext = new FileChooser.ExtensionFilter("TEXT files (*.txt)", "*.txt");
		fc.getExtensionFilters().add(ext);
		File selected = fc.showOpenDialog(null);
		if (selected != null) {
			try{
				String test = selected.getName();
				Scanner sc = new Scanner(selected.getPath());
				File file = new File(sc.nextLine());
				sc = new Scanner(file);

			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				String[] input = line.split(",");
				
				if(!PosNum(input[3])&&!nameCheck(input)&&dateCheck(input)) {
					TextArea.setText("invalid input");
					return;
				}
				
				Profile p = new Profile(input[1],input[2]);
				double dub = Double.parseDouble(input[3]);
				String[] date = input[4].split("/");
				Date Date2 = new Date(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]));
				
				if (input[0].equals("C")) {
					if(boolCheck(input)) {
					Checking c = new Checking(p,dub,Date2,Boolean.parseBoolean(input[5]));
					database.add(c);
					}
				}
				if (input[0].equals("S")) {
					if(boolCheck(input)) {
						Savings s = new Savings(p,dub,Date2,Boolean.parseBoolean(input[5]));
						database.add(s);
						}
				}
				if (input[0].equals("M")) {
					if(PosNum(input[5])) {
						MoneyMarket m = new MoneyMarket(p,dub,Date2,Integer.parseInt(input[5]));
						database.add(m);
						}
				}
			}
			TextArea.setText("Accounts added to database");	
			}catch(Exception e) {
				TextArea.setText("invalid text file");
				return;
			}
		}else {
			TextArea.setText("invalid text file");
			return;
		}
	}

	private boolean dateCheck(String[] input) {
		String Date = input[4];
		if (Date != null) {
			String[] date = Date.split("/");
			Date Date2 = new Date(Integer.parseInt(date[1]), Integer.parseInt(date[2]), Integer.parseInt(date[0]));
			if (Date2.isValid()) {
				return true;
			}
			return false;
		} else
			return false;
	}

	private boolean nameCheck(String[] input) {
		if (input[1].matches("[a-zA-Z]+") == true && input[2].matches("[a-zA-Z]+") == true) {
			return true;
		}
		return false;
	}

	/**
	 * exports the accounts in the database to a .txt file
	 **/
	@FXML
	void Export(ActionEvent event) {
		FileChooser fc = new FileChooser();
		FileChooser.ExtensionFilter ext = new FileChooser.ExtensionFilter("TEXT files (*.txt)", "*.txt");
		fc.getExtensionFilters().add(ext);
		File selected = fc.showSaveDialog(null);
		try {
			if(selected.exists()==false) {
				selected.createNewFile();
			}
			FileWriter wr = new FileWriter(selected);
			wr.write(database.exportAccounts());
			wr.close();
			TextArea.setText("database exported");
		}catch(Exception e) {
			TextArea.setText("cannot export database");
			return;
		}
	}

	/**
	 * prints to the text box all the accounts in the database in no order
	 **/
	@FXML
	void PrintByAccount(ActionEvent event) {
		TextArea.setText(database.printAccounts());
	}

	/**
	 * prints to the text box all the accounts in the database in descending order
	 * of date opened
	 **/
	@FXML
	void PrintByDate(ActionEvent event) {
		TextArea.setText(database.printByDateOpen());
	}

	/**
	 * prints to the text box all the accounts in the database in descending order
	 * of last name
	 **/
	@FXML
	void PrintByLastName(ActionEvent event) {
		TextArea.setText(database.printByLastName());
	}

	/**
	 * checks the validity of input of strings on tab 1
	 **/
	public boolean CheckValidStrings1() {
		boolean ret = true;
		if (FirstName.getText().matches("[a-zA-Z]+") == false) {
			TextArea.setText("Invalid: First Name area not valid.");
			ret = false;
		} else if (Lastname.getText().matches("[a-zA-Z]+") == false) {
			TextArea.setText("Invalid: Last Name area not valid.");
			ret = false;
		}
		return ret;
	}

	/**
	 * checks the validity of input of strings on tab 2
	 **/
	public boolean CheckValidStrings2() {
		boolean ret = true;
		if (Tab2FirstName.getText().matches("[a-zA-Z]+") == false) {
			TextArea.setText("Invalid: First Name area not valid.");
			ret = false;
		} else if (Tab2LastName.getText().matches("[a-zA-Z]+") == false) {
			TextArea.setText("Invalid: Last Name area not valid.");
			ret = false;
		}
		return ret;
	}

	/**
	 * checks the validity of the input date on tab 1
	 **/
	public boolean CheckValidDouble(String dub) {
		try {
			if (Double.parseDouble(dub) > 0) {
				return true;
			}
			TextArea.setText("Invalid: enter a positive amount");
			return false;
		} catch (NumberFormatException e) {

			TextArea.setText("Invalid: Not a valid amount");
			return false;

		}
	}

	public boolean PosNum(String a) {
		try {
			if (Double.parseDouble(a) >= 0) {
				return true;
			}
			return false;
		} catch (NumberFormatException ex) {
			return false;
		}
	}

	public boolean boolCheck(String[] arr) {
		String bool = arr[5].toLowerCase();
		if (bool.equals("false") || bool.equals("true")) {
			return true;
		} else {
			return false;
		}
	}

}
