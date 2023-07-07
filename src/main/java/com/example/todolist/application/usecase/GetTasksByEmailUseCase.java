package com.example.todolist.application.usecase;

import java.util.List;

public interface GetTasksByEmailUseCase {

	public List<TaskResponseModel> execute(String email);
}
