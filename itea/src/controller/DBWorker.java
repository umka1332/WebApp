package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBWorker {
	private final static String SELECT_QUERY = "select * from users";
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
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SELECT_QUERY);
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
	public String getUsers () {
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
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SELECT_QUERY);
			StringBuilder res = new StringBuilder();
			res.append("<tr>");
			res.append("<td>id:</td>");
			res.append("<td>login:</td>");
			res.append("<td>password:</td>");
			res.append("<td>Nmae:</td>");
			res.append("<td>Region:</td>");
			res.append("<td>Gender:</td>");
			res.append("<td>Comment:</td>");
			res.append("</tr>");
			while (rs.next()) {
				res.append("<tr>");
				res.append("<td>" + rs.getInt("id") + "</td>");
				res.append("<td>" + rs.getString("login") + "</td>");
				res.append("<td>" + rs.getString("password") + "</td>");
				res.append("<td>" + rs.getString("name") + "</td>");
				res.append("<td>" + rs.getString("region") + "</td>");
				res.append("<td>" + rs.getBoolean("gender") + "</td>");
				res.append("<td>" + rs.getString("comment") + "</td>");
				res.append("</tr>");
			}
			return res.toString();
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
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException sqlEx) {
				} // ignore

				conn = null;
			}

		}
		return "Error";
	}
	/*
	 * PrepareSTATEMENT*/
	
	
}
