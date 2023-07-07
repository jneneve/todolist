package com.example.todolist.integration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.context.annotation.Description;

import com.example.todolist.application.repository.TaskRepository;
import com.example.todolist.application.usecase.GetTasksByEmailService;
import com.example.todolist.application.usecase.GetTasksByEmailUseCase;
import com.example.todolist.application.usecase.TaskResponseModel;
import com.example.todolist.domain.entity.Task;
import com.example.todolist.infra.repository.memory.TaskRepositoryMemory;

public class GetTaskByEmailServiceTest {

	private final TaskRepository taskRepository = Mockito.mock(TaskRepository.class);

	@Test
	@Description("It should not return an email that do not exists")
	public void givenAnEmailThatDoNotExistsShouldThrownAnException() {
		String email = "not_exists_this_email";
		GetTasksByEmailUseCase getTasksByEmailUseCase = new GetTasksByEmailService(taskRepository);
		when(taskRepository.findByEmail(email)).thenReturn(false);
		assertThatThrownBy(() -> getTasksByEmailUseCase.execute(email)).isInstanceOf(IllegalStateException.class)
				.hasMessageContaining("This email do not exists!");
	}
	
	@Test
	@Description("It should return how many tasks exists from email")
	public void givenAnEmailGetTasks() {
		TaskRepository taskRepository = new TaskRepositoryMemory();
		Task task = new Task(1L, "johndue@gmail.com", "Title", "Description",
				LocalDateTime.parse("2023-06-07T12:06:30"), "NOT_STARTED");
		taskRepository.save(task);
		GetTasksByEmailUseCase getTasksByEmailUseCase = new GetTasksByEmailService(taskRepository);
		List<TaskResponseModel> responseModel = getTasksByEmailUseCase.execute("johndue@gmail.com");
		assertThat(responseModel).hasSize(1);
	}
}
	