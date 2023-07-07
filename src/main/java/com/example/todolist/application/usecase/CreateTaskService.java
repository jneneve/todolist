package com.example.todolist.application.usecase;

import org.springframework.stereotype.Service;

import com.example.todolist.application.repository.TaskRepository;
import com.example.todolist.domain.entity.Task;

@Service
public class CreateTaskService implements CreateTaskUseCase {

	private final TaskRepository taskRepository;

	public CreateTaskService(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}

	@Override
	public void execute(TaskRequestModel requestModel) {
		Task task = new Task(null, requestModel.email(), requestModel.title(), requestModel.description(),
				requestModel.dueDate(), requestModel.status());
		this.taskRepository.save(task);
	}
}
