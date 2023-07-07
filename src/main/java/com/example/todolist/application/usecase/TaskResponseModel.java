package com.example.todolist.application.usecase;

import java.time.LocalDateTime;

public record TaskResponseModel(Long id, String email, String title, String description, LocalDateTime dueDate,
		String status) {

}
