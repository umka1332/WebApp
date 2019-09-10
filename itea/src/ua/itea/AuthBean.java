package ua.itea;

public class AuthBean {
	private String login;
	private String password;
	private boolean accessGranted;
	
	public AuthBean() {}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isAccessGranted() {
		return login != null && password != null && getAccess(login, password);
	}

	private boolean getAccess(String login, String password) {
		//  оннект к базе, ЅлаЅлаЅлаЅла
		if (login.equals("admin") && password.equals("123")) {
			return true;
		}
		return false;
	}
}