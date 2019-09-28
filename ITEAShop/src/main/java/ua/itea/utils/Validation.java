package ua.itea.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
	public final static String PASWORD_PATTERN = "^((?=.*[a-z])(?=.*\\d)(?=.*[A-Z]).{8,})$";
	public final static String EMAIL_PATTERN = "^(.+)@(.+)$";
	
	public boolean checkPassword(String password) {
		Pattern pattern = Pattern.compile(PASWORD_PATTERN);
		Matcher matcher = pattern.matcher(password);
		return matcher.matches();
	}
	
	public boolean checkEmail(String email) {
		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
	public String getPasswordPattern () {
		return PASWORD_PATTERN;
	}
}
