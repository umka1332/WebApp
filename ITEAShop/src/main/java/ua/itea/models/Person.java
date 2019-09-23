package ua.itea.models;

public class Person {
	private String login;
	private String password;
	private String name;
	private String region;
	private String gender;
	private String comment;
	
	public Person () {}

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "Person [login=" + login + ", password=" + password + ", name=" + name + ", region=" + region
				+ ", gender=" + gender + ", comment=" + comment + "]";
	}
		
}
