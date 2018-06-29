package com.gb.students.crm_task_manager.model.repos;

import com.gb.students.crm_task_manager.model.entity.Task;

import java.util.List;

import io.reactivex.Observable;


public interface TaskRepo {
    Observable<List<Task>> getTasks();
    Observable<Boolean> addTask(Task task);
    Observable<Boolean> removeTask(Task task);
}
