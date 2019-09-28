package ua.itea.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ua.itea.dao.UserDAO;
import ua.itea.models.User;

public class MySQLUserDAO implements UserDAO {

	private static final String SELECT_PERSON = "SELECT * FROM users WHERE login = ?";
	private static final String INSERT_PERSON = "INSERT INTO users (login, password, name, region, gender, comment)"
			+ " VALUES(?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_PERSON = "UPDATE users SET password=?, name=?, region=?, gender=?, comment=? WHERE id=?";
	
	@Override
	public User getUserByLogin(String login) {
		try (Connection conn = MySQLDAOFactory.getConnection();
				PreparedStatement ps = conn.prepareStatement(SELECT_PERSON)) {
			ps.setString(1, login);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				User p = new User();
				p.setId(rs.getLong("id"));
				p.setLogin(login);
				p.setPassword(rs.getString("password"));
				p.setName(rs.getString("name"));
				p.setRegion(rs.getString("region"));
				p.setGender(rs.getBoolean("gender") ? "Male" : "Female");
				p.setComment(rs.getString("comment"));
				return p;
			}
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		return null;
	}

	@Override
	public boolean saveUser(User user) {
		try (Connection conn = MySQLDAOFactory.getConnection();
				PreparedStatement ps = conn.prepareStatement(INSERT_PERSON)) {
			ps.setString(1, user.getLogin());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getName());
			ps.setString(4, user.getRegion());//Currently we have a number from selector rather than actual region
			ps.setBoolean(5, getBooleanFromGender(user.getGender()));
			ps.setString(6, user.getComment());
			ps.execute();
			return true;
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		return false;
	}

	@Override
	public boolean updateUser(User user) {
		try (Connection conn = MySQLDAOFactory.getConnection();
				PreparedStatement ps = conn.prepareStatement(UPDATE_PERSON)) {
			//"UPDATE users SET password=?, name=?, region=?, gender=?, comment=? WHERE login=? ";
			ps.setLong(6, user.getId());
			ps.setString(1, user.getPassword());
			ps.setString(2, user.getName());
			ps.setString(3, user.getRegion());//Currently we have a number from selector rather than actual region
			ps.setBoolean(4, getBooleanFromGender(user.getGender()));
			ps.setString(5, user.getComment());
			ps.execute();
			return true;
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		return false;
	}
	
	private boolean getBooleanFromGender(String gender) {
		if (gender.equals("Male"))
			return true;
		if (gender.equals("Female"))
			return false;
		throw new IllegalArgumentException();
	}

}
