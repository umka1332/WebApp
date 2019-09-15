package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/*
 * add check existing login
 * use prepareStatement
 * Auth from DB
 * Register to DB
 */
public class DBWorker2 {
	private final static String SELECT_SOME_QUERY = "select * from users WHERE ID = ?"; //UNSAFE
	private final static String SOME_INSERT =
			"INSERT INTO users(login, password, name, region, gender, comment) " + 
			"VALUES('jdbc@mail.com', '321', 'jdbcuser', 'KNR','1', 'some random comment from jdbc')";

	public static void main(String[] args) {
		try {
			// The newInstance() call is a work around for some
			// broken Java implementations
			System.out.print("Loading driver... ");
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			System.out.println("Success");
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		Connection conn = null;

		try {
			System.out.print("Obtaining connection... ");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/iteashop?" + "user=root&password=");

			System.out.println("OK");

		} catch (SQLException ex) {
			System.out.println("Failed");
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		/////////////////////////////////////////////////////////////////
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			PreparedStatement prepstmt = conn.prepareStatement(SELECT_SOME_QUERY);
			prepstmt.setInt(1, 1);// ? are indexed from 1

			rs = prepstmt.executeQuery();
			while (rs.next()) {
				System.out.println("==================");
				System.out.println("id: " + rs.getInt("id"));
				System.out.println("login: " + rs.getString("login"));
				System.out.println("password: " + rs.getString("password"));
				System.out.println("Nmae: " + rs.getString("name"));
				System.out.println("Region: " + rs.getString("region"));
				System.out.println("Gender: " + rs.getBoolean("gender"));
				System.out.println("Comment: " + rs.getString("comment"));
			}

			// or alternatively, if you don't know ahead of time that
			// the query will be a SELECT...

			/*
			 * if (stmt.execute("SELECT foo FROM bar")) { rs = stmt.getResultSet(); }
			 */
			// Now do something with the ResultSet ....
		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		} finally {
			// it is a good idea to release
			// resources in a finally{} block
			// in reverse-order of their creation
			// if they are no-longer needed

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException sqlEx) {
				} // ignore

				rs = null;
			}

			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException sqlEx) {
				} // ignore

				stmt = null;
			}
		}
		try {
			stmt = conn.createStatement();
			boolean result = stmt.execute(SOME_INSERT);
		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		} finally {
			// it is a good idea to release
			// resources in a finally{} block
			// in reverse-order of their creation
			// if they are no-longer needed

			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException sqlEx) {
				} // ignore

				stmt = null;
			}
		}
	}	
	
}
