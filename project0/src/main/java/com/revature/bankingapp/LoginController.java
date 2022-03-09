package com.revature.bankingapp;

import java.util.Scanner;

public class LoginController {


	public static void loginUser() {

		Scanner userInput = new Scanner(System.in);

		userInput.nextLine();
		System.out.println("Enter Username: ");
		String userUsername = userInput.nextLine();
		System.out.println("Enter Password: ");
		String userPassword = userInput.nextLine();
			if(userUsername && userPassword == //username and password in filesystem) {
					System.out.println("Welcome Back <insert name here> how can I help you today!")
			} else {
				System.out.println("Sorry those credentials arent valid, please try again");
				loginUser();
		}

	}

