package com.revature.bankingapp;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ListOfUsers extends User {

	public static void main(String[] args) {

		// ArrayList<String> listOfUsers = new ArrayList<String>();

		File f = new File("C:\\Users\\Vince\\Desktop\\FileOfUsers.txt");
		FileWriter fw = new FileWriter("C:\\Users\\Vince\\Desktop\\FileOfUsers.txt");

		for (int i = 0; i < 1; i++) {

			try {
				f.createNewFile();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		fw.write(null);
	}

}
