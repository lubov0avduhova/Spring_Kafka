package org.example.corecrm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class TaskDto {
    private Long id;
    private String name;
    private LocalDateTime createdAt;
    private boolean isClosed;
}
