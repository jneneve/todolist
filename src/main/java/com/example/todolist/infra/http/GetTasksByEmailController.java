package com.example.todolist.infra.http;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.todolist.application.usecase.GetTasksByEmailUseCase;
import com.example.todolist.application.usecase.TaskResponseModel;

@RestController
@RequestMapping("/api/v1/tasks")
public class GetTasksByEmailController {

	private final GetTasksByEmailUseCase getTasksByEmailUseCase;
	
	public GetTasksByEmailController(GetTasksByEmailUseCase getTasksByEmailUseCase) {
		this.getTasksByEmailUseCase = getTasksByEmailUseCase;
	}
	
	@GetMapping("/{email}")
	public ResponseEntity<List<TaskResponseModel>> getTasksByEmail(@PathVariable String email) {
		List<TaskResponseModel> responseModel = this.getTasksByEmailUseCase.execute(email);
		return ResponseEntity.ok().body(responseModel);
	}
}
