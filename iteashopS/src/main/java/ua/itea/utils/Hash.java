package ua.itea.utils;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash {
	private final static String salt="MiNiMaX";
	public static String hash(String password) {
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(StandardCharsets.UTF_8.encode(salt + password));
			String res = String.format("%032x", new BigInteger(md5.digest()));
			//return res;
			return password;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
		
	}

}
