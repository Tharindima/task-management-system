package com.example.task_tracker.dto;

import com.example.task_tracker.entity.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskRequestDTO {

    @NotBlank(message = "Description is required.")
    @Size(max = 255, message = "Description must be 255 characters or fewer.")
    private String description;

    private TaskStatus status;
}
