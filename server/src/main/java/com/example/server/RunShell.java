package com.example.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class RunShell {
	public static void printmenu() {
		System.out.println("Welcome to MySystem");

		System.out.println("Please select from the menu");
		System.out.println("1- Start all microservices");
		System.out.println("2- Stop all");
		System.out.println("3- Restart");

		System.out.println("4- Print menu");
		System.out.println("9- Exit");
	}
	public static void main(String[] args) {

		Scanner myObj = new Scanner(System.in);
		printmenu();

		String username = "";

		while (true) {
			username = myObj.nextLine();
			System.out.println("You have chosen: " + username);
			if (username.equalsIgnoreCase("9"))
				break;
			if(username.equalsIgnoreCase("4"))
				printmenu();
		}
		System.out.println("Thank you!");
//	    
//        ProcessBuilder processBuilder = new ProcessBuilder();
//        // Windows
//        processBuilder.command("cmd.exe", "/c", "ping -n 3 google.com");
//
//        try {
//
//            Process process = processBuilder.start();
//
//            BufferedReader reader =
//                    new BufferedReader(new InputStreamReader(process.getInputStream()));
//
//            String line;
//            while ((line = reader.readLine()) != null) {
//                System.out.println(line);
//            }
//
//            int exitCode = process.waitFor();
//            System.out.println("\nExited with error code : " + exitCode);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

	}
}
