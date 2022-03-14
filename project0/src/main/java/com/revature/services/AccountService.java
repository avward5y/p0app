package com.revature.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.revature.beans.Account;
import com.revature.beans.User;
import com.revature.exceptions.OverdraftException;
import com.revature.utils.ConnectionUtil;
import com.revature.dao.*;


/**
 * This class should contain the business logic for performing operations on Accounts
 */
public class AccountService {
	
	public AccountDao actDao;
	public static final double STARTING_BALANCE = 25d;
	private static Connection conn;
	private static Statement stmt;
	private static PreparedStatement pstmt;
	private static ResultSet rs;
	int option = 0;
	UserDao userDao = new UserDaoDB();
	AccountDao accountDao = new AccountDaoDB();
	UserService userService = new UserService(userDao, accountDao);
//	AccountService accountService = new AccountService(accountDao);
	Scanner userInput = new Scanner(System.in);
	
	
	public AccountService(AccountDao dao) {
		conn = ConnectionUtil.getConnection();
		this.actDao = dao;
	}
	
	/**
	 * Withdraws funds from the specified account
	 * @throws OverdraftException if amount is greater than the account balance
	 * @throws UnsupportedOperationException if amount is negative
	 */
	public void withdraw(Account a, Double amount) {
		
		String query = "update p0_account set balance=? where id=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setDouble(1, a.getBalance() - amount);
			pstmt.setObject(2, a.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (!a.isApproved()) 
			throw new UnsupportedOperationException();
		
		
	}
	
	/**
	 * Deposit funds to an account
	 * @throws UnsupportedOperationException if amount is negative
	 */
	public void deposit(Account a, Double amount) {
		
		String query = "update p0_account set balance=? where id=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setDouble(1, a.getBalance() + amount);
			pstmt.setObject(2, a.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (!a.isApproved()) 
			throw new UnsupportedOperationException();
		
	}
	
	/**
	 * Transfers funds between accounts
	 * @throws UnsupportedOperationException if amount is negative or 
	 * the transaction would result in a negative balance for either account
	 * or if either account is not approved
	 * @param fromAct the account to withdraw from
	 * @param toAct the account to deposit to
	 * @param amount the monetary value to transfer
	 */
	public void transfer(Account fromAct, Account toAct, double amount) {
		
	}
	
	/**
	 * Creates a new account for a given User
	 * @return the Account object that was created
	 */
	public Account createNewAccount(User u) {
		System.out.println(u.getAccounts().get(0));
		return actDao.addAccount(u.getAccounts().get(0));
	}
	/**
	 * Approve or reject an account.
	 * @param a
	 * @param approval
	 * @throws UnauthorizedException if logged in user is not an Employee
	 * @return true if account is approved, or false if unapproved
	 */
	public boolean approveOrRejectAccount(Account a, boolean approval) {
		return false;
	}
	
	public List<Account> getAccounts(User u) {
		List<Account> accountList = new ArrayList<Account>();
		accountList = u.getAccounts();
		return accountList;
	}
	
	public void userManagementMenu() {
		Scanner userInput = new Scanner(System.in);
		option = userInput.nextInt();
		
		System.out.println("\t\t\t\t\t\tWelcome to the User Management Console \n \t\t\t\t\t\t     Enter Management Credentials ");
		System.out.println("Username: ");
		String managerUser = userInput.nextLine();
		System.out.println("Password: ");
		String managerPw = userInput.nextLine();
		if (managerUser.contentEquals("admin") && managerPw.contentEquals("admin")) {
			System.out.println("What would you like to do, input the corresponding number:");
			System.out.println("1) View Users");
			System.out.println("2) Update User");
			System.out.println("3) Delete User");
			System.out.println("4) Approve Accounts");
		} else {
			System.out.println("Incorrect Management Credentials, try again.");
			
		}
		
			switch (option) {
				case 1:
					accountDao.getAccounts();
					
					break;
				case 2:
					userDao.updateUser(null);
					break;
      }
	}
}
