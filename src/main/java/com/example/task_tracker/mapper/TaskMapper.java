package com.example.task_tracker.mapper;

import com.example.task_tracker.dto.TaskRequestDTO;
import com.example.task_tracker.dto.TaskResponseDTO;
import com.example.task_tracker.entity.Task;
import com.example.task_tracker.entity.TaskStatus;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    public Task toEntity(TaskRequestDTO requestDTO) {
        Task task = new Task();
        task.setDescription(requestDTO.getDescription());
        task.setStatus(TaskStatus.TODO);
        return task;
    }

    public TaskResponseDTO toResponseDTO(Task task) {
        TaskResponseDTO responseDTO = new TaskResponseDTO();
        responseDTO.setId(task.getId());
        responseDTO.setDescription(task.getDescription());
        responseDTO.setStatus(task.getStatus());
        responseDTO.setCreatedAt(task.getCreatedAt());
        responseDTO.setUpdatedAt(task.getUpdatedAt());
        return responseDTO;
    }
}
