package org.example.corecrm.mapping;

import org.example.corecrm.dto.TaskDto;
import org.example.corecrm.entity.Task;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TaskMapper {
    public TaskDto toDto(Task task) {
        return TaskDto.builder()
                .id(task.getId())
                .name(task.getName())
                .createdAt(task.getCreatedAt())
                .isClosed(task.isClosed())
                .build();
    }

    public Task toEntity(TaskDto taskDto) {
        return Task.builder()
                .id(taskDto.getId())
                .name(taskDto.getName())
                .createdAt(taskDto.getCreatedAt())
                .isClosed(taskDto.isClosed())
                .build();
    }

    public List<TaskDto> toListDto(List<Task> list) {
        List<TaskDto> result = new ArrayList<>(list.size());
        for (Task building : list) {
            result.add(toDto(building));
        }
        return result;
    }

}
