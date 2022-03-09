package com.revature.bankingapp;

import java.util.Scanner;

public class RegisterUser {

	public static void registerUser() {

		Scanner userInput = new Scanner(System.in);

		userInput.nextLine();
		System.out.println("Enter Username: ");
		userInput.nextLine();
		System.out.println("Enter Password: ");
		String createdPassword = userInput.nextLine();
		System.out.println("Confirm Password: ");
		String verifiedPassword = userInput.nextLine();
		if (verifiedPassword != createdPassword) {
			System.out.println("This input does not match your initial input, please try again.\n Press Enter.");
			registerUser();
		} else {
			System.out.println("Welcome to CapitalNone Bank are you interested in a Checking or Savings account?");
			userInput.nextLine();
		}

	}

}
