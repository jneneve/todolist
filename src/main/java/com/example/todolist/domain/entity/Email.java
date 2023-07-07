package com.example.todolist.domain.entity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Email {

	private final String email;

	private Email(String email) {
		this.email = email;
	}

	public String value() {
		return this.email;
	}

	public static Email create(String email) {
		if (!Email.validate(email)) {
			throw new IllegalStateException("This email is invalid!");
		}
		return new Email(email);
	}

	public static boolean validate(String email) {
		if (email.isEmpty()) {
			return false;
		}
		if (email.length() > 256) {
			return false;
		}
		Pattern pattern = Pattern.compile(
				"^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$");
		Matcher matcher = pattern.matcher(email);
		if (!matcher.matches()) {
			return false;
		}
		String account = email.split("@")[0];
		String address = email.split("@")[1];
		if (account.length() > 64) {
			return false;
		}
		String[] domainParts = address.split("\\.");
		for (String part : domainParts) {
			if (part.length() > 63) {
				return false;
			}
		}
		return true;
	}
}
