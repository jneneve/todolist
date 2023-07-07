package com.example.todolist.integration;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Description;

import com.example.todolist.application.repository.TaskRepository;
import com.example.todolist.application.usecase.CreateTaskService;
import com.example.todolist.application.usecase.CreateTaskUseCase;
import com.example.todolist.application.usecase.TaskRequestModel;
import com.example.todolist.application.usecase.TaskResponseModel;
import com.example.todolist.domain.entity.Task;
import com.example.todolist.infra.repository.memory.TaskRepositoryMemory;

public class CreateTaskServiceTest {

	@Test
	@Description("It should create a task")
	public void givenAnrequestModelCreateTask() {
		TaskRepository taskRepository = new TaskRepositoryMemory();
		CreateTaskUseCase createTaskUseCase = new CreateTaskService(taskRepository);
		TaskRequestModel requestModel = new TaskRequestModel("johndue@gmail.com", "Title", "Description",
				LocalDateTime.parse("2023-06-07T12:06:30"), "NOT_STARTED");
		createTaskUseCase.execute(requestModel);
		List<Task> queryResult = taskRepository.getTasksByEmail("johndue@gmail.com");
		List<TaskResponseModel> tasksGroupByEmail = queryResult.stream()
				.map(task -> new TaskResponseModel(task.getIdTask(), task.getEmail().value(), task.getTitle(),
						task.getDescription(), task.getDueDate(), task.getStatus()))
				.collect(Collectors.toList());
		assertThat(tasksGroupByEmail).hasSize(1);
	}
}
