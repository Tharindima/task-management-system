package com.example.task_tracker;

import com.example.task_tracker.controller.TaskController;
import com.example.task_tracker.dto.TaskRequestDTO;
import com.example.task_tracker.dto.TaskResponseDTO;
import com.example.task_tracker.entity.TaskStatus;
import com.example.task_tracker.service.TaskService;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TaskControllerTest {

    @Test
    void shouldReturnCreatedResponseForCreateTask() {
        TaskService taskService = mock(TaskService.class);
        TaskController taskController = new TaskController(taskService);

        TaskRequestDTO requestDTO = new TaskRequestDTO();
        requestDTO.setDescription("Create task");

        TaskResponseDTO responseDTO = new TaskResponseDTO();
        responseDTO.setId(1L);
        responseDTO.setDescription("Create task");
        responseDTO.setStatus(TaskStatus.TODO);

        when(taskService.createTask(requestDTO)).thenReturn(responseDTO);

        ResponseEntity<TaskResponseDTO> response = taskController.createTask(requestDTO);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isEqualTo(responseDTO);
    }

    @Test
    void shouldReturnNoContentForDeleteTask() {
        TaskService taskService = mock(TaskService.class);
        TaskController taskController = new TaskController(taskService);

        ResponseEntity<Void> response = taskController.deleteTask(1L);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    void shouldReturnTaskListForGetAllTasks() {
        TaskService taskService = mock(TaskService.class);
        TaskController taskController = new TaskController(taskService);

        TaskResponseDTO responseDTO = new TaskResponseDTO();
        responseDTO.setId(1L);
        responseDTO.setDescription("First task");
        responseDTO.setStatus(TaskStatus.TODO);

        when(taskService.getAllTasks()).thenReturn(List.of(responseDTO));

        List<TaskResponseDTO> response = taskController.getAllTasks();

        assertThat(response).hasSize(1);
        assertThat(response.getFirst().getDescription()).isEqualTo("First task");
    }
}
