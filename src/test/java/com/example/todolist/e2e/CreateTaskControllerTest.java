package com.example.todolist.e2e;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Description;
import org.springframework.test.web.servlet.MockMvc;

import com.example.todolist.application.repository.TaskRepository;
import com.example.todolist.application.usecase.TaskRequestModel;
import com.example.todolist.application.usecase.TaskResponseModel;
import com.example.todolist.domain.entity.Task;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class CreateTaskControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private TaskRepository taskRepository;

	@BeforeEach
	public void init() {
		this.taskRepository.clear();
	}
	
	@Test
	@Description("It should create a task via API")
	public void givenAnObjectTaskCreateItViaAPI() throws JsonProcessingException, Exception {
		TaskRequestModel requestModel = new TaskRequestModel("johndue@gmail.com", "Title", "Description",
				LocalDateTime.parse("2023-06-07T12:06:30"), "NOT_STARTED");
		this.mockMvc.perform(post("/api/v1/task/create").header("Content-Type", "application/json")
				.content(this.objectMapper.writeValueAsBytes(requestModel))).andExpect(status().isOk());
		List<Task> queryResult = taskRepository.getTasksByEmail("johndue@gmail.com");
		List<TaskResponseModel> tasksGroupByEmail = queryResult.stream()
				.map(task -> new TaskResponseModel(task.getIdTask(), task.getEmail().value(), task.getTitle(),
						task.getDescription(), task.getDueDate(), task.getStatus()))
				.collect(Collectors.toList());
		assertThat(tasksGroupByEmail).hasSize(1);
	}
}
