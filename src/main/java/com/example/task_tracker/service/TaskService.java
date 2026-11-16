package com.example.task_tracker.service;

import com.example.task_tracker.dto.TaskRequestDTO;
import com.example.task_tracker.dto.TaskResponseDTO;
import com.example.task_tracker.entity.Task;
import com.example.task_tracker.exception.ResourceNotFoundException;
import com.example.task_tracker.mapper.TaskMapper;
import com.example.task_tracker.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    public TaskService(TaskRepository taskRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }

    public TaskResponseDTO createTask(TaskRequestDTO requestDTO) {
        Task task = taskMapper.toEntity(requestDTO);
        Task savedTask = taskRepository.save(task);
        return taskMapper.toResponseDTO(savedTask);
    }

    public List<TaskResponseDTO> getAllTasks() {
        return taskRepository.findAll()
                .stream()
                .map(taskMapper::toResponseDTO)
                .toList();
    }

    public TaskResponseDTO getTaskById(Long id) {
        Task task = findTaskById(id);
        return taskMapper.toResponseDTO(task);
    }

    public TaskResponseDTO updateTask(Long id, TaskRequestDTO requestDTO) {
        Task task = findTaskById(id);

        if (requestDTO.getStatus() == null) {
            throw new IllegalArgumentException("Status is required when updating a task.");
        }

        task.setDescription(requestDTO.getDescription());
        task.setStatus(requestDTO.getStatus());

        Task updatedTask = taskRepository.save(task);
        return taskMapper.toResponseDTO(updatedTask);
    }

    public void deleteTask(Long id) {
        Task task = findTaskById(id);
        taskRepository.delete(task);
    }

    private Task findTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));
    }
}
