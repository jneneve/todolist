package com.example.todolist.infra.http;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.todolist.application.usecase.CreateTaskUseCase;
import com.example.todolist.application.usecase.TaskRequestModel;

@RestController
@RequestMapping("/api/v1/task")
public class CreateTaskController {

	private final CreateTaskUseCase createTaskUseCase;

	public CreateTaskController(CreateTaskUseCase createTaskUseCase) {
		this.createTaskUseCase = createTaskUseCase;
	}

	@PostMapping("/create")
	public ResponseEntity<Void> createTask(@RequestBody TaskRequestModel requestModel) {
		this.createTaskUseCase.execute(requestModel);
		return ResponseEntity.ok().build();
	}

}
