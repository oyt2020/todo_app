package com.google.todo.api.todo.response;

import com.google.todo.domain.todo.Todo;
import com.google.todo.domain.todo.TodoStatus;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class TodoResponse {


    private final Long id;
    private final String title;
    private final TodoStatus status;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final LocalDate scheduledDate;

    public  TodoResponse(Todo todo) {
        this.id = todo.getId();
        this.title = todo.getTitle();
        this.status = todo.getStatus();
        this.createdAt = todo.getCreatedAt();
        this.updatedAt = todo.getUpdateAt();
        this.scheduledDate = todo.getScheduledDate();
    }
}
