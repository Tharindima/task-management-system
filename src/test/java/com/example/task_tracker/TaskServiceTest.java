package com.example.task_tracker;

import com.example.task_tracker.dto.TaskRequestDTO;
import com.example.task_tracker.dto.TaskResponseDTO;
import com.example.task_tracker.entity.Task;
import com.example.task_tracker.entity.TaskStatus;
import com.example.task_tracker.exception.ResourceNotFoundException;
import com.example.task_tracker.mapper.TaskMapper;
import com.example.task_tracker.repository.TaskRepository;
import com.example.task_tracker.service.TaskService;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TaskServiceTest {

    @Test
    void shouldCreateTaskWithTodoStatus() {
        TaskRepository taskRepository = mock(TaskRepository.class);
        TaskMapper taskMapper = new TaskMapper();
        TaskService taskService = new TaskService(taskRepository, taskMapper);

        TaskRequestDTO requestDTO = new TaskRequestDTO();
        requestDTO.setDescription("Learn Spring Boot");

        Task savedTask = buildTask(1L, "Learn Spring Boot", TaskStatus.TODO);
        when(taskRepository.save(any(Task.class))).thenReturn(savedTask);

        TaskResponseDTO responseDTO = taskService.createTask(requestDTO);

        assertThat(responseDTO.getId()).isEqualTo(1L);
        assertThat(responseDTO.getDescription()).isEqualTo("Learn Spring Boot");
        assertThat(responseDTO.getStatus()).isEqualTo(TaskStatus.TODO);
    }

    @Test
    void shouldUpdateDescriptionAndStatus() {
        TaskRepository taskRepository = mock(TaskRepository.class);
        TaskMapper taskMapper = new TaskMapper();
        TaskService taskService = new TaskService(taskRepository, taskMapper);

        Task existingTask = buildTask(1L, "Old description", TaskStatus.TODO);
        Task updatedTask = buildTask(1L, "Updated description", TaskStatus.DONE);

        TaskRequestDTO requestDTO = new TaskRequestDTO();
        requestDTO.setDescription("Updated description");
        requestDTO.setStatus(TaskStatus.DONE);

        when(taskRepository.findById(1L)).thenReturn(Optional.of(existingTask));
        when(taskRepository.save(existingTask)).thenReturn(updatedTask);

        TaskResponseDTO responseDTO = taskService.updateTask(1L, requestDTO);

        assertThat(responseDTO.getDescription()).isEqualTo("Updated description");
        assertThat(responseDTO.getStatus()).isEqualTo(TaskStatus.DONE);
    }

    @Test
    void shouldThrowWhenTaskDoesNotExist() {
        TaskRepository taskRepository = mock(TaskRepository.class);
        TaskMapper taskMapper = new TaskMapper();
        TaskService taskService = new TaskService(taskRepository, taskMapper);

        when(taskRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> taskService.getTaskById(99L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Task not found with id: 99");
    }

    @Test
    void shouldReturnAllTasks() {
        TaskRepository taskRepository = mock(TaskRepository.class);
        TaskMapper taskMapper = new TaskMapper();
        TaskService taskService = new TaskService(taskRepository, taskMapper);

        when(taskRepository.findAll()).thenReturn(List.of(
                buildTask(1L, "Task one", TaskStatus.TODO),
                buildTask(2L, "Task two", TaskStatus.IN_PROGRESS)
        ));

        List<TaskResponseDTO> tasks = taskService.getAllTasks();

        assertThat(tasks).hasSize(2);
        assertThat(tasks.getFirst().getStatus()).isEqualTo(TaskStatus.TODO);
        assertThat(tasks.get(1).getStatus()).isEqualTo(TaskStatus.IN_PROGRESS);
    }

    private Task buildTask(Long id, String description, TaskStatus status) {
        Task task = new Task();
        task.setId(id);
        task.setDescription(description);
        task.setStatus(status);
        task.setCreatedAt(LocalDateTime.now());
        task.setUpdatedAt(LocalDateTime.now());
        return task;
    }
}
