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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class TodoServiceTest {

    @Mock
    TodoRepository todoRepository;

    @InjectMocks
    TodoService todoService;

    @Test
    @DisplayName("할 일 생성")
    void create_todo(){
        Todo todo = Todo.create("CI/CD 공부");

        given(todoRepository.save(any(Todo.class))) // 어떤 Todo 든 상관 x
                .willAnswer(invocation -> invocation.getArgument(0));

        Long id = todoService.createTodo("CI/CD 공부");

        //assertThat(id).isNull();
        verify(todoRepository).save(any(Todo.class));

    }

    @Test
    @DisplayName("상태별 조회")
    void find_by_status(){
        given(todoRepository.findByStatus(TodoStatus.PENDING))
                .willReturn(List.of(Todo.create("테스트")));

        List<Todo> todos = todoService.getTodosByStatus(TodoStatus.PENDING);

        assertThat(todos).hasSize(1);
    }

    @Test
    @DisplayName("할 일 완료 처리")
    void complete_todo(){
        Todo todo = Todo.create("완료");

        given(todoRepository.findById(1L)).willReturn(Optional.of(todo));

        todoService.completeTodo(1L);

        assertThat(todo.getStatus()).isEqualTo(TodoStatus.COMPLETED);
    }
}