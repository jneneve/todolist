package com.example.todolist.e2e;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Description;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.example.todolist.application.repository.TaskRepository;
import com.example.todolist.application.usecase.TaskResponseModel;
import com.example.todolist.domain.entity.Task;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class GetTaskByEmailControllerTest {

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
	@Description("It should get task by email via API")
	public void givenAnEmailGetTask() throws Exception {
		Task task = new Task(1L, "johndue@gmail.com", "Title", "Description",
				LocalDateTime.parse("2023-06-07T12:06:30"), "NOT_STARTED");
		taskRepository.save(task);
		MvcResult requestResult = mockMvc
				.perform(get("/api/v1/tasks/{email}", task.getEmail().value()).header("Content-Type", "application/json"))
				.andExpect(status().isOk()).andReturn();
		String contentAsString = requestResult.getResponse().getContentAsString();
		List<TaskResponseModel> responseModel = this.objectMapper.readValue(contentAsString, new TypeReference<List<TaskResponseModel>>() {
		});
		assertThat(responseModel).hasSize(1);
	}
}
