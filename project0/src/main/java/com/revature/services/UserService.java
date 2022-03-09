package com.revature.services;

import com.revature.beans.User;
import com.revature.dao.*;
import com.revature.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.revature.bankingapp.*;


/**
 * This class should contain the business logic for performing operations on users
 */
public class UserService {
	
	UserDao userDao;
	AccountDao accountDao;
	private static Connection conn;
	private static Statement stmt;
	private static PreparedStatement pstmt;
	private static ResultSet rs;
	
	public UserService() {
		conn = ConnectionUtil.getConnection();

	}

	public UserService(UserDao userDao2, AccountDao accountDao2) {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Validates the username and password, and return the User object for that user
	 * @throws InvalidCredentialsException if either username is not found or password does not match
	 * @return the User who is now logged in
	 */
	public User login(String username, String password) {
		
		String query = "select * from p0_users where username= '" + username + "' and password='" + password + "';";
		User user = new User();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			if (rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setUserType(rs.getString("user_type"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	
	/**
	 * Creates the specified User in the persistence layer
	 * @param newUser the User to register
	 * @throws UsernameAlreadyExistsException if the given User's username is taken
	 */
	public User register(User user) {
		
		String query = "insert into p0_users (first_name, last_name, username, password, user_type ) values (?,?,?,?,?)";
		boolean status = false;
		int insertStatus = 0;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, User.getFirstName());
			pstmt.setString(2, User.getLastName());
			pstmt.setString(3, User.getUsername());
			pstmt.setString(4, User.getPassword());
			pstmt.setObject(5, User.getUserType().toString());
			insertStatus = pstmt.executeUpdate();
			if(insertStatus > 0)
				status = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
}
