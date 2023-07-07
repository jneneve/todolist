package com.example.todolist.unit;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Description;

import com.example.todolist.domain.entity.Task;

public class TaskTest {

	@Test
	@Description("It should not create a task with invalid email")
	public void givenAnInvalidEmailDoNotCreateTaskAndThrownException() {
		String email = "not_an_email";
		assertThatThrownBy(() -> new Task(1L, email, "Title", "Description",
				LocalDateTime.parse("2023-06-07T12:06:30"), "NOT_STARTED")).isInstanceOfAny(IllegalStateException.class)
				.hasMessageContaining("This email is invalid!");
	}
}
