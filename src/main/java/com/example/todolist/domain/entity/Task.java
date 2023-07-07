package com.example.todolist.domain.entity;

import java.time.LocalDateTime;

import lombok.Getter;

public class Task {

	@Getter
	private final Long idTask;

	@Getter
	private final Email email;

	@Getter
	private final String title;

	@Getter
	private final String description;

	@Getter
	private final LocalDateTime dueDate;

	@Getter
	private final String status;

	public Task(Long idTask, String email, String title, String description, LocalDateTime dueDate, String status) {
		this.idTask = idTask;
		this.email = Email.create(email);
		this.title = title;
		this.description = description;
		this.dueDate = dueDate;
		this.status = status;
	}
}
