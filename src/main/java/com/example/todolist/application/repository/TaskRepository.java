package com.example.todolist.application.repository;

import java.util.List;

import com.example.todolist.domain.entity.Task;

public interface TaskRepository {

	public void save(Task task);

	public boolean findByEmail(String email);

	public List<Task> getTasksByEmail(String email);
	
	public void clear();
}
