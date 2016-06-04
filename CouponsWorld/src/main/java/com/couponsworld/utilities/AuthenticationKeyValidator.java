package com.couponsworld.utilities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AuthenticationKeyValidator {

	private static Pattern pattern;
	private static Matcher matcher;

	private static final String AUTH_KEY_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,8})";

	static {
		pattern = Pattern.compile(AUTH_KEY_PATTERN);
	}

	/**
	 * Validate authKey with regular expression
	 * 
	 * @param password
	 *            password for validation
	 * @return true valid password, false invalid password
	 */
	public static boolean validate(final String authKey) {

		matcher = pattern.matcher(authKey);
		return matcher.matches();

	}

}
