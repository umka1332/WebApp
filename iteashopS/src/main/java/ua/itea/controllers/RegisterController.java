package ua.itea.controllers;

import java.util.ArrayList;
import java.util.List;

import ua.itea.utils.Validation;
import ua.itea.dao.UserDAO;
import ua.itea.dao.DAOFactory;
import ua.itea.models.User;
import ua.itea.utils.Hash;

public class RegisterController {
	private String login;
	private String password;
	private String rePassword;
	private String name;
	private String region;
	private String gender;
	private String comment;
	private String amigo;

	private boolean error;
	private List<String> errorText;

	private UserDAO userDAO;
	private Validation validator;
	private boolean passwordsPresent = true;

	public RegisterController() {
		DAOFactory factory = DAOFactory.getDAOFactory(1);
		userDAO = factory.getUserDAO();
		errorText = new ArrayList<String>();
		error = false;
		validator = new Validation();
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		if (login == null || login.isEmpty()) {
			addError("Empty login");
		} else {
			if (!validator.checkEmail(login)) {
				addError("Incorrect email used for login");
			}
		}
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		if (password == null || password.isEmpty()) {
			passwordsPresent = false;
			addError("Empty password");
		} else {
			if (!validator.checkPassword(password)) {
				addError("Password should contain a lowercase character, an uppercase character, a digit and at least 8 symbols");
			}
		}
		this.password = password;
	}

	public String getRePassword() {
		return rePassword;
	}

	public void setRePassword(String rePassword) {
		if (rePassword == null || rePassword.isEmpty()) {
			passwordsPresent = false;
			addError("Empty re-password");
		}
		if (passwordsPresent && !password.equals(rePassword)) {
			addError("Passwords do not match");
		}
		this.rePassword = rePassword;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (name == null || name.isEmpty()) {
			addError("Empty name");
		}
		this.name = name;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		if (region == null || region.isEmpty()) {
			addError("Empty region");
		}
		this.region = region;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		if (gender == null || gender.isEmpty()) {
			addError("Empty gender");
		} else {
			if (!(gender.equals("Female") || gender.equals("Male"))) {
				addError("Unexpected gender");
			}
		}
		this.gender = gender;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		if (comment == null || comment.isEmpty()) {
			addError("Empty comment");
		}
		this.comment = comment;
	}

	public String getAmigo() {
		return amigo;
	}

	public void setAmigo(String amigo) {
		if (amigo == null) { // either null, either "ok"
			addError("Empty checkbox");
		}
		this.amigo = amigo;
	}

	public boolean isError() {
		return error;
	}

	public List<String> getErrorText() {
		return errorText;
	}

	public User createUser() {
		if (userDAO.getUserByLogin(login) != null) {
			addError("User with this login already exists");
		}
		if (!error) {
			User user = new User();
			user.setLogin(login);
			user.setPassword(Hash.hash(password));
			user.setName(name);
			user.setRegion(region);
			user.setGender(gender);
			user.setComment(comment);
			boolean success = userDAO.saveUser(user);
			if (!success) {
				addError("Unable to create user in DB");
			}
			return user;
		}
		return null;
	}

	public User updateUser() {
		User user = userDAO.getUserByLogin(login);
		if (user == null) {
			addError("User with this login doesn't exist");
		}
		if (!error) {
			// ?TODO?: update password
			// ?TODO?: use `if(name == null) user.setName(name);` instead of simply
			// `user.setName(name);`
			user.setName(name);
			user.setRegion(region);
			user.setGender(gender);
			user.setComment(comment);
			boolean success = userDAO.updateUser(user);
			if (!success) {
				addError("Unable to update user in DB");
			}
			return user;
		}
		return null;
	}

	private void addError(String s) {
		error = true;
		errorText.add(s);
	}

}
