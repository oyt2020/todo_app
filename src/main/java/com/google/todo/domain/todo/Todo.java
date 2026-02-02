package com.google.todo.domain.todo;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Calendar;
import java.util.Date;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "todos")
public class Todo extends BaseTimeEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 100)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TodoStatus status;

    protected Todo(String title){
        this.title = title;
        this.status = TodoStatus.PENDING;
    }

    public static Todo create(String title){
        return new Todo(title);
    }

    public void complete(){
        this.status = TodoStatus.COMPLETED;
    }

    public void pending(){ this.status = TodoStatus.PENDING; }

    public void updateTitle(String title){
        this.title = title;
    }
}
