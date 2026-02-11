package com.google.todo.domain.todo;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
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

    @Column(name="scheduled_date", nullable=false)
    private LocalDate scheduledDate;

    protected Todo(String title){
        this.title = title;
        this.status = TodoStatus.PENDING;
        this.scheduledDate = LocalDate.now();
    }

    protected Todo(String title,LocalDate scheduledDate){
        this.title = title;
        this.status = TodoStatus.PENDING;
        this.scheduledDate = scheduledDate != null ? scheduledDate : LocalDate.now();
    }

    public static Todo create(String title){
        return new Todo(title);
    }

    public static Todo create(String title,LocalDate scheduledDate){

        return new Todo(title,scheduledDate);
    }

    public void complete(){
        this.status = TodoStatus.COMPLETED;
    }

    public void pending(){ this.status = TodoStatus.PENDING; }

    public void updateTitle(String title){
        this.title = title;
    }

    public void updateScheduledDate(LocalDate scheduledDate){
        this.scheduledDate = scheduledDate != null ? scheduledDate : LocalDate.now();
    }
}
