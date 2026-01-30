package com.google.todo.api.todo.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class TodoUpdateRequest {

    @NotBlank
    @Size(max=100)
    private String title;
}
