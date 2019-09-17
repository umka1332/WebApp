package ua.itea;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import utils.Hash;

public class AuthDB extends Auth {
	private final static String SELECT_SOME_QUERY = "select * from users WHERE login = ? and password = ?"; //UNSAFE
	
	static {
		try {
			System.out.print("Loading driver... ");
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			System.out.println("Success");
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	@Override
	public boolean getLogin(String login, String password) {
		System.out.print("Obtaining connection... ");
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/iteashop?" + "user=root&password=")) {
			System.out.println("OK");
			try (PreparedStatement prepStmt = conn.prepareStatement(SELECT_SOME_QUERY)) {
				prepStmt.setString(1, login);
				prepStmt.setString(2, Hash.hash(password));
				return prepStmt.executeQuery().next();
			} catch (SQLException ex) {
				// handle any errors
				System.out.println("Request Failed");
				System.out.println("SQLException: " + ex.getMessage());
				System.out.println("SQLState: " + ex.getSQLState());
				System.out.println("VendorError: " + ex.getErrorCode());
			}
		} catch (SQLException ex) {
			System.out.println("Connection Failed");
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		return false;
	}
}
