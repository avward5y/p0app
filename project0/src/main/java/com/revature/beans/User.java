package com.revature.beans;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * Represents a User of the system. Can be a CUSTOMER or EMPLOYEE. Every user
 * can have one or more accounts.
 */
public class User implements Serializable {
	/** Automatically generated universally unique identifier */
	private static final long serialVersionUID = -8630467975639842515L;

	public static enum UserType {
		CUSTOMER, EMPLOYEE
	}
	
	public User() {
		super();
		
	}

	private Integer id;
	private static String username;
	private static String password;
	private static String firstName;
	private static String lastName;
	private static Integer accountId;
	private static UserType userType;
	private List<Account> accounts;

	public User(Integer id, String username, String password, String firstName, String lastName, UserType userType) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userType = userType;

	}

	public User(String username, String password, String firstName, String lastName, Integer accountId,
			UserType userType) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.accountId = accountId;
		this.userType = userType;
	}

	public User(String username, String password, String firstName, String lastName) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public User(Integer id, String username, String firstName, String lastName) {
		super();
		this.id = id;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public static String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public static String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public static String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public static String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public static UserType getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = UserType.valueOf(userType);
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(accountId, accounts, firstName, id, lastName, password, userType, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(accountId, other.accountId) && Objects.equals(accounts, other.accounts)
				&& Objects.equals(firstName, other.firstName) && Objects.equals(id, other.id)
				&& Objects.equals(lastName, other.lastName) && Objects.equals(password, other.password)
				&& userType == other.userType && Objects.equals(username, other.username);
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", userType=" + userType + "]";
	}

}
