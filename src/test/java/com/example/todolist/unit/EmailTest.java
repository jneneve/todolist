package com.example.todolist.unit;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Description;

import com.example.todolist.domain.entity.Email;

public class EmailTest {

	@Test
	@Description("It should accept a valid email")
	public void givenAnEmailAcceptIt() {
		assertThat(Email.validate("johndue@gmail.com")).isTrue();
	}

	@Test
	@Description("It should not accept an email without the at-sign")
	public void givenAnEmailDoNotAcceptWithouAtSign() {
		assertThat(Email.validate("jonhduegmail.com")).isFalse();
	}

	@Test
	@Description("It should not accept more than 255 chars")
	public void givenAnEmailDoNotAcceptMoreThan255Chars() {
		String email = "c".repeat(260);
		assertThat(Email.validate(email)).isFalse();
	}

	@Test
	@Description("It should not accept an empty email")
	public void givenAnEmailDoNotAcceptEmptyOne() {
		assertThat(Email.validate("")).isFalse();
	}

	@Test
	@Description("It should not accept more than 64 chars on local part")
	public void givenAnEmailDoNotAcceptMoreThan64CharsOnLocalPart() {
		String localPart = "c".repeat(100);
		String email = localPart + "@gmail.com";
		assertThat(Email.validate(email)).isFalse();
	}

	@Test
	@Description("It should not accept empty on local part")
	public void givenAnEmailDoNotAcceptEmptyOnLocalPart() {
		assertThat(Email.validate("@gmail.com")).isFalse();
	}

	@Test
	@Description("It should not accept invalid char on local part")
	public void givenAnEmailDoNotAcceptInvalidCharOnLocalPart() {
		assertThat(Email.validate("jo nhdue@gmail.com")).isFalse();
	}

	@Test
	@Description("It should not accept a dot as first char on local part")
	public void givenAnEmailDoNotAcceptDotAsFirstCharOnLocalPart() {
		assertThat(Email.validate(".johndue@gmail.com")).isFalse();
	}

	@Test
	@Description("It should not accept a dot as last char on local part")
	public void givenAnEmailDoNotAcceptDotAsLastCharOnLocalPart() {
		assertThat(Email.validate("johndue.@gmail.com")).isFalse();
	}

	@Test
	@Description("It should not accept dot as first char on domain part")
	public void givenAnEmailDoNotAcceptDotAsFirstCharOnDomainPart() {
		assertThat(Email.validate("johndue@.gmail.com")).isFalse();
	}

	@Test
	@Description("It should not accept more than 63 chars on domain part")
	public void givenAnEmailDoNotAcceptMoreThan63CharsOnDomainPart() {
		String domain = "c".repeat(100);
		String email = "johndue@" + domain + ".com";
		assertThat(Email.validate(email)).isFalse();
	}
}
