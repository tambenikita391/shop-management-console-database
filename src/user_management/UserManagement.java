package user_management;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import db_operations.DBUtils;

public class UserManagement
{
	public static void userManagement() throws SQLException
	{
		Scanner r = new Scanner(System.in);
		boolean canIKeepRunning = true;
		while(canIKeepRunning == true)
		{
			System.out.println("******WELCOME TO USER MANAGMENT*****");
			System.out.println("\n");
			System.out.println("What would you like to do:");
			
			System.out.println("1.Add user");
			System.out.println("2.Edit user");
			System.out.println("3.Search user");
			System.out.println("4.Delete user");
			System.out.println("5.Quit");
			
			int option=r.nextInt();
			if(option==1)
			{
				addUser();
			}
			else if(option==2)
			{
				System.out.println("Enter the name of user to edit:");
				r.nextLine();
				String en=r.nextLine();
				editUser(en);
			}
			else if(option==3)
			{
				System.out.println("Enter the name of user to search:");
				r.nextLine();
				String sn=r.nextLine();
				searchUser(sn);
			}
			else if(option==4)
			{
				System.out.println("Enter the name of user to delete:");
				r.nextLine();
				String dn=r.nextLine();
				deleteUser(dn);
			}
			else if(option==5)
			{	
				System.out.println("Program finished!!");
				canIKeepRunning=false;
				
			}
		}
	}

public static void addUser() 
{
	
	Scanner r = new Scanner(System.in); 
	User u=new User();
	
	System.out.println("Enter username:");
	u.userName=r.nextLine();
	
	System.out.println("Enter login name:");
	u.login=r.nextLine();
	
	System.out.println("Enter password:");
	u.passwords=r.nextLine();
	
	System.out.println("Enter cofirm password:");
	u.password2=r.nextLine();
	
	System.out.println("Enter user role:");
	u.userRole=r.nextLine();
	
	String query = "INSERT INTO Users(userName , login , passwords , password2 , userRole) VALUES  ('"+u.userName+"', '"+u.login+"', '"+u.passwords+"','"+u.password2+"','"+u.userRole+"' ";
	DBUtils.executeQuery(query);
}	

public static void searchUser(String userName) throws SQLException
{
	String query = "select * from Users where userName= '"+userName+"'";
	
	ResultSet rs = DBUtils.executeQueryGetResult(query);
	try {
		while(rs.next())
		{
				if(rs.getString("username").equalsIgnoreCase(userName))
				{
					System.out.println("Username:"+rs.getString("userName"));
					System.out.println("Login name:"+rs.getString("login"));
					System.out.println("Password:"+rs.getString("passwords"));
					System.out.println("Confirm password:"+rs.getString("userRole"));
					return;
				}

		}
	} catch (Exception e) {
		System.out.println("User not found!!");
			
	}

}
public static void deleteUser(String userName)
{
	String query = "delete from Users where userName = '"+userName+"' ";
    DBUtils.executeQuery(query);
}
public static void editUser(String name) throws SQLException
{
	String query = "select * from Users where username ='"+name+"'";
	ResultSet rs = DBUtils.executeQueryGetResult(query);
	
	try {
		while(rs.next())
		{
			try {
				if(rs.getString("username").equalsIgnoreCase(name))
				{
					User u = new User();
					Scanner r = new Scanner(System.in);
					
					System.out.println("New Username:");
					u.userName=r.nextLine();
					
					System.out.println("New login name:");
					u.login=r.nextLine();
					
					System.out.println("New password:");
					u.passwords=r.nextLine();
					
					System.out.println("New cofirm password:");
					u.password2=r.nextLine();
					
					System.out.println("New User role:");
					u.userRole=r.nextLine();
					
					String updateQuery ="update Users set"+ "userName='"+u.userName+"', login='"+u.login+"',passwords='"+u.passwords+"',userRole='"+u.userRole+"' ";
					DBUtils.executeQuery(updateQuery);
					System.out.println("User information updated!!");
					return;
				}
			} 
			catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
	} catch (SQLException e) {
		
		System.out.println("User not found!!");
	}

}
public static boolean validateUserAndPasswords(String login , String Passwords)
{
	String query = "select * from Users where login='"+login+"' and Passwords='"+Passwords+"' ";
	ResultSet resultset = DBUtils.executeQueryGetResult(query);
	try {
		if(resultset.next())
		{
			return true;
		}
	}
	catch(SQLException e)
	{
		e.printStackTrace();
	}
	return false;
}

}
	
		
	


