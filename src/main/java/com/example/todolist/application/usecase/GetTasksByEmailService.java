package com.example.todolist.application.usecase;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.todolist.application.repository.TaskRepository;
import com.example.todolist.domain.entity.Task;

@Service
public class GetTasksByEmailService implements GetTasksByEmailUseCase {

	private final TaskRepository taskRepository;

	public GetTasksByEmailService(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}

	@Override
	public List<TaskResponseModel> execute(String email) {
		if (!this.taskRepository.findByEmail(email)) {
			throw new IllegalStateException("This email do not exists!");
		}
		List<Task> queryResult = this.taskRepository.getTasksByEmail(email);
		List<TaskResponseModel> tasksGroupByEmail = queryResult.stream()
				.map(task -> new TaskResponseModel(task.getIdTask(), task.getEmail().value(), task.getTitle(),
						task.getDescription(), task.getDueDate(), task.getStatus()))
				.collect(Collectors.toList());
		return tasksGroupByEmail;
	}
}
