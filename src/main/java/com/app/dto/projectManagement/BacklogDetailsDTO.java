package com.app.dto.projectManagement;

import com.app.dto.TaskManagement.TaskDTO;
import com.app.enums.TaskStatus;

import java.util.List;

public class BacklogDetailsDTO {
    Long id;
    String title;
    TaskStatus status;
    float progress;
    List<TaskDTO> tasks;

}
