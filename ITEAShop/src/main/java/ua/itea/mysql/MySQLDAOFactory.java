package ua.itea.mysql;

import ua.itea.dao.UserDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import ua.itea.dao.DAOFactory;
import ua.itea.dao.ProductDAO;

public class MySQLDAOFactory extends DAOFactory {
	static {
		try {
			System.out.print("Loading driver... ");
			Class.forName("com.mysql.jdbc.Driver");
			//Class.forName("com.mysql.fabric.jdbc.FabricMySQLDriver");
			System.out.println("Success");
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	static Connection getConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:mysql://localhost/iteashop?" + "user=root&password=");
	}

	@Override
	public UserDAO getUserDAO() {
		return new MySQLUserDAO();
	}

	@Override
	public ProductDAO getProductDAO() {
		return new MySQLProductDAO();
	}

}
