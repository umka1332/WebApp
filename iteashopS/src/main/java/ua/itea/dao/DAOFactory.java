package ua.itea.dao;

import ua.itea.mysql.MySQLDAOFactory;

public abstract class DAOFactory {
	public static DAOFactory getDAOFactory(int index) {
		switch(index) {
		case 1 : return new MySQLDAOFactory();
		}
		return null;
	}
	
	public abstract UserDAO getUserDAO();
	public abstract ProductDAO getProductDAO();

}
