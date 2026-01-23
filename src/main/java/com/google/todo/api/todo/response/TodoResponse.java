package com.google.todo.api.todo.response;

import com.google.todo.domain.todo.Todo;
import com.google.todo.domain.todo.TodoStatus;
import lombok.Getter;

@Getter
public class TodoResponse {


    private final Long id;
    private final String title;
    private final TodoStatus status;

    public  TodoResponse(Todo todo) {
        this.id = todo.getId();
        this.title = todo.getTitle();
        this.status = todo.getStatus();
    }
}
