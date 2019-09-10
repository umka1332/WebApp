package ua.itea;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Person implements Serializable {
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

	public Person() {
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
	
	private void validate() {
		if (errorText == null) {
			errorText = new ArrayList<String>();
			error = false;

			Validation validator = new Validation();
			boolean passwordsPresent = true;

			if (login == null || login.isEmpty()) {
				error = true;
				errorText.add("<ul>Empty login</ul>");
			}
			if (!validator.checkEmail(login)) {
				error = false;
				errorText.add("<ul>Incorrect email used for login</ul>");
			}
			if (password.isEmpty()) {
				passwordsPresent = false;
				error = true;
				errorText.add("<Empty password");
			}
			if (rePassword.isEmpty()) {
				passwordsPresent = false;
				error = true;
				errorText.add("Empty re-password");
			}
			if (passwordsPresent && !password.equals(rePassword)) {
				error = true;
				errorText.add("Passwords do not match");
			}
			if (!validator.checkPassword(password)) {
				error = false;
				errorText.add(
						"Password should contain a lowercase character, an uppercase character, a digit and at least 8 symbols");
			}
			if (name.isEmpty()) {
				error = true;
				errorText.add("Empty name");
			}
			if (region.isEmpty()) {
				error = true;
				errorText.add("region loin");
			}
			if (gender == null) {
				error = true;
				errorText.add("Empty gender");
			}
			if (comment.isEmpty()) {
				error = true;
				errorText.add("Empty comment");
			}
			if (amigo == null) { // either null, either "ok"
				error = true;
				errorText.add("Empty checkbox");
			}
		}
	}
	
	@Override
	public String toString() {
		return "Person [login=" + login + ", password=" + password + ", rePassword=" + rePassword + ", name=" + name
				+ ", region=" + region + ", gender=" + gender + ", comment=" + comment + ", amigo=" + amigo + "]";
	}


}
