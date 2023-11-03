package login;

import java.sql.SQLException;
import java.util.Scanner;

import user_management.UserManagement;

public class MainApplication 
{
	public static void main(String [] args) throws SQLException
	{
		Scanner r = new Scanner(System.in);
		boolean canIKeepRunning = true;
		
		System.out.println("*****WELCOME TO SHOP MANAGEMENT*****");
		System.out.println("\n");
		
		System.out.println("Enter login name:");
		String login = r.nextLine();
		System.out.println("Enter password:");
		String Passwords = r.nextLine();
		
		if(!UserManagement.validateUserAndPasswords(login, Passwords))
		{
			System.out.println("!!Login failed!!");
			return;
		}
		else {
			System.out.println("Login successful!");
		}
		
		while(canIKeepRunning == true)
		{
			System.out.println("****Welcome to shop management****");
			System.out.println("\n");
			
			System.out.println("What would you like to do:");
			System.out.println("1.User management");
			System.out.println("5.Quit");
			
			int option = r.nextInt();
			
			if(option == 1)
			{
				UserManagement.userManagement();
				
			}
			else if(option == 5)
			{
				break;
			}
			
		}
	}

}
