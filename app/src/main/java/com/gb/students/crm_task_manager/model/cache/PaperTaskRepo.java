package com.gb.students.crm_task_manager.model.cache;

import com.gb.students.crm_task_manager.model.entity.Task;
import com.gb.students.crm_task_manager.model.repos.TaskRepo;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;
import io.reactivex.Observable;
import timber.log.Timber;

public class PaperTaskRepo implements TaskRepo {

    @Override
    public Observable<List<Task>> getTasks() {
        return Observable.fromCallable(() -> {
            List<Task> tasks = Paper.book("tasks").read("all");
            if (tasks == null) {
                Timber.d("TaskList is empty, new one created");
                return new ArrayList<>();
            }
            Timber.d("TaskList loader from memory");
            return tasks;
        });
    }

    @Override
    public Observable<Boolean> addTask(Task task) {
        return Observable.fromCallable(() -> {
            List<Task> savedTasks = Paper.book("tasks").read("all");
            if (savedTasks == null) {
                savedTasks = new ArrayList<>();
            }
            savedTasks.add(task);
            Timber.d("New task was written to memory");
            Paper.book("task").write("all", savedTasks);
            return true;
        });
    }

    @Override
    public Observable<Boolean> removeTask(Task task) {
        return Observable.fromCallable(() -> {
            List<Task> savedTasks = Paper.book("tasks").read("all");
            savedTasks.remove(task);
            return true;
        });
    }


}
