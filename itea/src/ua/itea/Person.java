package ua.itea;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Person implements Serializable {
	private String login; // varchar 50
	private String password; // varchar 100
	private String rePassword;// no db field
	private String name;
	private String region;
	private String gender;
	private String comment;
	private String amigo;// No db field

	private boolean error;
	private List<String> errorText;

	private static final String DB_URL = "jdbc:mysql://localhost/iteashop" + "?user=root&password=";
	private static final String CHECK_LOGIN = "SELECT * FROM users WHERE login = ?";
	private static final String ADD_USER = "INSERT INTO users (login, password, name, region, gender, comment)"
			+ " VALUES(?, ?, ?, ?, ?, ?)";

	public Person() {
	}

	static {
		try {
			System.out.print("Loading driver... ");
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			System.out.println("Success");
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		invalidate();
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		invalidate();
		this.password = password;
	}

	public String getRePassword() {
		return rePassword;
	}

	public void setRePassword(String rePassword) {
		invalidate();
		this.rePassword = rePassword;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		invalidate();
		this.name = name;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		invalidate();
		this.region = region;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		invalidate();
		this.gender = gender;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		invalidate();
		this.comment = comment;
	}

	public String getAmigo() {
		return amigo;
	}

	public void setAmigo(String amigo) {
		invalidate();
		this.amigo = amigo;
	}

	public boolean isError() {
		validate();
		return error;
	}

	public List<String> getErrorText() {
		validate();
		return errorText;
	}

	private void invalidate() {
		errorText = null;
	}

	private void addError(String s) {
		error = true;
		errorText.add(s);
	}

	private void validate() {
		if (errorText == null) {

			errorText = new ArrayList<String>();
			error = false;

			Validation validator = new Validation();
			boolean passwordsPresent = true;

			if (login == null || login.isEmpty()) {
				addError("Empty login");
			}
			if (!validator.checkEmail(login)) {
				addError("Incorrect email used for login");
			} else {
				try (Connection conn = DriverManager.getConnection(DB_URL);
						PreparedStatement ps = conn.prepareStatement(CHECK_LOGIN);) {
					ps.setString(1, login);
					if (ps.executeQuery().next()) {
						addError("User with this login already exists");
					}
				} catch (SQLException e) {
					addError("Problem with DB");
				}
			}
			if (password.isEmpty()) {
				passwordsPresent = false;
				addError("Empty password");
			}
			if (rePassword.isEmpty()) {
				passwordsPresent = false;
				addError("Empty re-password");
			}
			if (passwordsPresent && !password.equals(rePassword)) {
				addError("Passwords do not match");
			}
			if (!validator.checkPassword(password)) {
				addError(
						"Password should contain a lowercase character, an uppercase character, a digit and at least 8 symbols");
			}
			if (name.isEmpty()) {
				addError("Empty name");
			}
			if (region.isEmpty()) {
				addError("region loin");
			}
			if (gender == null) {
				addError("Empty gender");
			}
			if (comment.isEmpty()) {
				addError("Empty comment");
			}
			if (amigo == null) { // either null, either "ok"
				addError("Empty checkbox");
			}

			if (!error) {
				try (Connection conn = DriverManager.getConnection(DB_URL);
						PreparedStatement ps = conn.prepareStatement(ADD_USER)) {
					ps.setString(1, login);
					ps.setString(2, password);
					ps.setString(3, name);
					ps.setString(4, region);//Currently we have a number from selector rather than actual region
					ps.setBoolean(5, gender.equals("Male"));
					ps.setString(6, comment);
					ps.execute();
				} catch (SQLException ex) {
					System.out.println("SQLException: " + ex.getMessage());
					System.out.println("SQLState: " + ex.getSQLState());
					System.out.println("VendorError: " + ex.getErrorCode());
					addError("Can not write user to DB");
				}
			}
		}
	}

	@Override
	public String toString() {
		return "Person [login=" + login + ", password=" + password + ", rePassword=" + rePassword + ", name=" + name
				+ ", region=" + region + ", gender=" + gender + ", comment=" + comment + ", amigo=" + amigo + "]";
	}

}
