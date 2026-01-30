package com.google.todo.api.todo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.todo.api.todo.request.TodoCreateRequest;
import com.google.todo.domain.todo.Todo;
import com.google.todo.service.todo.TodoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import static org.mockito.BDDMockito.given;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TodoController.class)
class TodoControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockitoBean
    TodoService todoService;

    // 정상 케이스
    @Test
    @DisplayName("할 일 생성")
    void create_todo() throws Exception {
        given(todoService.createTodo("CI/ CD 공부")).willReturn(1L);

        TodoCreateRequest request =new TodoCreateRequest();

        String json = """
                {
                    "title": "CI/ CD 공부"
                }
                """;

        mockMvc.perform(post("/api/todos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data").value(1L));


        verify(todoService).createTodo("CI/ CD 공부");
    }

    // 실패 케이스
    @Test
    @DisplayName("실패 케이스 제목 없음 400 에러")
    void create_todo_validation_fail() throws Exception {
        String json = """
                {
                    "title": ""
                }
                """;

        mockMvc.perform(post("/api/todos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success").value(false));
    }

    //5. 단건 조회 API 테스트 (GET) 부터
    @Test
    @DisplayName("할 일을 조회 (단건)")
    void get_todo() throws Exception {
        Todo todo = Todo.create("테스트");
        todo.complete();

        given(todoService.getTodo(1L)).willReturn(todo);

        mockMvc.perform(get("/api/todos/{id}",1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.title").value("테스트"))
                .andExpect(jsonPath("$.data.status").value("COMPLETED"));
    }

    // 전체 조회
    @Test
    @DisplayName("할 일 목록 조회 (전체)")
    void get_todos() throws Exception {
        Todo todo1 = Todo.create("todo1");
        Todo todo2 = Todo.create("todo2");

        given(todoService.getTodos())
                .willReturn(java.util.List.of(todo1,todo2));

        mockMvc.perform(get("/api/todos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.length()").value(2));
    }

    // 완료 처리 테스트
    @Test
    @DisplayName("할 일 완료 처리")
    void complete_todo() throws Exception {
        mockMvc.perform(patch("/api/todos/{id}/complete",1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));


        verify(todoService).completeTodo(1L);
    }

    // 삭제 테스트
    @Test
    @DisplayName("할 일 삭제")
    void delete_todo() throws Exception {
        mockMvc.perform(delete("/api/todos/{id}",1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));

        verify(todoService).deleteTodo(1L);
    }



}