package com.gb.students.crm_task_manager.presenter;

import android.text.Editable;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.gb.students.crm_task_manager.model.cache.paper.PaperContactsRepo;
import com.gb.students.crm_task_manager.model.cache.paper.PaperTaskRepo;
import com.gb.students.crm_task_manager.model.cache.paper.PaperTypesRepo;
import com.gb.students.crm_task_manager.model.entity.Task;
import com.gb.students.crm_task_manager.model.entity.types.Types;
import com.gb.students.crm_task_manager.view.AddTaskView;

import java.util.Date;
import java.util.List;

import io.paperdb.Paper;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

@InjectViewState
public class AddTaskPresenter extends MvpPresenter<AddTaskView> {
    private Scheduler scheduler;



    public AddTaskPresenter(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    private PaperContactsRepo contactsRepo;
    private PaperTaskRepo taskRepo;
    private PaperTypesRepo typesRepo;

    private Task task;
    private List<String> types;
    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        //todo убрать очистку тасков
        Paper.book("tasks").destroy();

        contactsRepo = new PaperContactsRepo();
        taskRepo = new PaperTaskRepo();
        typesRepo = new PaperTypesRepo();
        task = new Task();
        Observable<Types> single = typesRepo.loadTypes();
        single.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(types -> {
                    this.types=types.getTaskTypes().getAll();
                    getViewState().init();
                    getViewState().fillTaskTypesSpinner(types.getTaskTypes());
                });

    }

    public void applyNewTask(String title, String note, String type) {
        if (task != null && title != null && !title.equals("")) {
            task.setCreationDate(new Date());
            task.setNote(note);
            task.setTitle(title);
            task.setType(type);

            Observable<Boolean> single = taskRepo.addTask(task);
            single.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(boo -> {
                        Timber.d("Task saved successfully");
                        getViewState().toast("Task saved");
                    });
        } else {
            getViewState().showError("Task is empty or has no title");
        }

    }

    public void addContact() {
        //todo реализовать добавление контакта. диалоговое окно или фрагменты?
        getViewState().toast("add contact to task ");
    }

    public void setDate(Date date) {
        task.setExpDate(date);
    }


    public void setTime(int hour, int minute) {
        Date date = task.getExpDate();
        date.setHours(hour);
        date.setMinutes(minute);
    }

    public void addSubtask(String s) {
        task.addSubtask(s);
        getViewState().toast("Subtask "+s+" added");
    }

    public void setTaskType(int pos) {
        task.setType(this.types.get(pos));
        getViewState().toast(this.types.get(pos)+" is selected");
    }
}
