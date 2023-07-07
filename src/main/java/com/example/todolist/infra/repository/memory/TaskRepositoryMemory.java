package com.example.todolist.infra.repository.memory;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.todolist.application.repository.TaskRepository;
import com.example.todolist.domain.entity.Task;

@Repository
public class TaskRepositoryMemory implements TaskRepository {

	private final List<Task> tasksGroup;

	public TaskRepositoryMemory() {
		this.tasksGroup = new ArrayList<Task>();
	}

	@Override
	public void save(Task task) {
		this.tasksGroup.add(task);
	}

	@Override
	public boolean findByEmail(String email) {
		for (Task task : this.tasksGroup) {
			if (task.getEmail().value().equals(email)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public List<Task> getTasksByEmail(String email) {
		List<Task> tasksGroupByEmail = new ArrayList<Task>();
		for (Task task : this.tasksGroup) {
			if (task.getEmail().value().equals(email)) {
				tasksGroupByEmail.add(task);
			}
		}
		return tasksGroupByEmail;
	}

	@Override
	public void clear() {
		this.tasksGroup.clear();
	}
}
