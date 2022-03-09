package com.revature.driver;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.revature.beans.*;
import com.revature.beans.Account.AccountType;
import com.revature.bankingapp.RegisterUser;
import com.revature.beans.User;
import com.revature.beans.User.UserType;
import com.revature.dao.AccountDao;
import com.revature.dao.AccountDaoDB;
import com.revature.dao.UserDao;
import com.revature.dao.UserDaoDB;
import com.revature.services.AccountService;
import com.revature.services.UserService;
import com.revature.utils.SessionCache;

/**
 * This is the entry point to the application
 */
public class BankApplicationDriver {
	

	
	public static void main(String[] args) {
		

		int option = 0;
		String username = null;
		String password = null;
		String fName = null;
		String lName = null;
		UserDao userDao = new UserDaoDB();
		AccountDao accountDao = new AccountDaoDB();
		UserService userService = new UserService(userDao, accountDao);
		AccountService accountService = new AccountService(accountDao);
		

		

		Scanner userInput = new Scanner(System.in);

		while (option <= 3) {
			System.out.println("\t\t\t\t\t\t Welcome to CapitalNone Bank!");

			System.out.println("\t\t\t\t\t\tWhat can I help you with today?\n");
			System.out.println("Input the number of your selection. \n");
			System.out.println("1) New User? Create An Account Here: ");
			System.out.println("2) Returning User? Sign In Here: \n");
			System.out.println("3) Exit");
			option = userInput.nextInt();

			switch (option) {
			
			

			case 1:
				userService = new UserService();
				userInput.nextLine();
				System.out.println("Enter First Name: ");
				fName = userInput.nextLine();
				System.out.println("Enter Last Name: ");
				lName = userInput.nextLine();
				System.out.println("Enter Username: ");
				username = userInput.nextLine();
				System.out.println("Enter Password: ");
				password = userInput.nextLine();
				User user = new User(null, username, password, fName, lName, UserType.CUSTOMER);
				if(userService.register(user) != null) {
					System.out.println("Registration Complete, Login to set up your accounts");
				} else {
					System.out.println("There was an error while registering, please try again.");
				}
				break;
			
			case 2:
				userService = new UserService();
				userInput.nextLine();
				System.out.println("Enter Username: ");
				username = userInput.nextLine();
				System.out.println("Enter Password: ");
				password = userInput.nextLine();
				User loggedUser = userService.login(username, password);
				System.out.println(loggedUser);
				if(loggedUser != null ) {
					System.out.println("Login Successful, Welcome back.");
					SessionCache.setCurrentUser(loggedUser);
					
					int accountType = 0;
					double startingBalance = 0;
					
					while(option <= 5) {
					System.out.println("1) Create Account");
					System.out.println("2) Make a deposit");
					System.out.println("3) Make a withdrawal");
					System.out.println("4) Logout");
					option = userInput.nextInt();
					
						switch (option) {
						case 1:
							System.out.println("What kind of account do you want to set up? \n 1)Checking \n 2)Savings");
							accountType = userInput.nextInt();
							System.out.println("Enter your starting deposit: ");
							startingBalance = userInput.nextDouble();
							Account account = new Account();
							account.setBalance(startingBalance);
							System.out.println("Current UserID: " + SessionCache.getCurrentUser().get().getId());
							account.setOwnerId(loggedUser.getId());
							account.setType(accountType == 1 ? AccountType.CHECKING.toString(): AccountType.SAVINGS.toString());
							List<Account> accountList = new ArrayList<Account>();
							accountList.add(account);
							loggedUser.setAccounts(accountList);
							accountService.createNewAccount(loggedUser);
							break;
						case 2:
							System.out.println("Your Current Accounts");
							accountService.getAccounts(loggedUser).forEach(System.out::println);
							System.out.println("Enter desired Account Id: ");
							int daccountId = 0;
							daccountId = userInput.nextInt();
							System.out.println("Enter deposit amount: \n $ ");
							double depositAmount = 0;
							depositAmount = userInput.nextDouble();
							account = accountDao.getAccount(daccountId);
							accountService.deposit(account, depositAmount);				
							break;
						case 3:
							System.out.println("Your Current Accounts");
							accountService.getAccounts(loggedUser).forEach(System.out::println);
							System.out.println("Enter desired Account Id: ");
							int waccountId = 0;
							waccountId = userInput.nextInt();
							System.out.println("Enter withdrawal amount: \n $ ");
							double withdrawAmount = 0;
							withdrawAmount = userInput.nextDouble();
							account = accountDao.getAccount(waccountId);
							accountService.withdraw(account, withdrawAmount);				
							break;	
						case 4:
							System.out.println("Thank you for banking with CapitalNone Bank, have a wonderful day!");
							System.exit(0);
						}
					}
				
				} else {
					System.out.println("Incorrect Credentials, please try again.");
				}
				break;
			case 3:
				System.out.println("Thank you for banking with CapitalNone Bank, have a wonderful day!");
				System.exit(0);
			default:
				System.out.println("What is the purpose of your visit today? Enter the corresponding number.");
			}

		}
		userInput.close();
	}
	
}

