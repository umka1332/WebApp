package ua.itea.controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ua.itea.models.Person;
import ua.itea.utils.ConnectionProvider;
import ua.itea.utils.Hash;

public class LoginController {

	private static final String SEARCH_PERSON = "SELECT * FROM users WHERE login = ? and password = ?";

	public LoginController() {
	}

	public Object getPrincipal(String login, String password) {
		try (Connection conn = ConnectionProvider.getConnection();
				PreparedStatement ps = conn.prepareStatement(SEARCH_PERSON)) {
			ps.setString(1, login);
			ps.setString(2, Hash.hash(password));
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Person p = new Person();
				p.setLogin(login);
				//There could be problem with password in Register/Edit - DB stores only hashed password
				//while user would like to change other fields and store original password
				//Here we could take original password from parameter to store it in session
				p.setPassword(password);
				p.setName(rs.getString("name"));
				p.setRegion(rs.getString("region"));
				p.setGender(rs.getBoolean("gender") ? "Male" : "Female" );
				p.setComment(rs.getString("comment"));
				return p;
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		return null;
	}
}
