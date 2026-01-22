package com.google.todo.service.todo;

import com.google.todo.domain.todo.Todo;
import com.google.todo.domain.todo.TodoRepository;
import com.google.todo.domain.todo.TodoStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class TodoServiceTest {

    @Mock
    TodoRepository todoRepository;

    @InjectMocks
    TodoService todoService;

    //@Test
    @DisplayName("할 일 생성")
    void create_todo(){
        Todo todo = Todo.create("CI/CD 공부");

        given(todoRepository.save(todo)).willReturn(todo);

        Long id = todoService.createTodo("CI/CD 공부");

        assertThat(id).isNull();
        verify(todoRepository).save(todo);
    }

}