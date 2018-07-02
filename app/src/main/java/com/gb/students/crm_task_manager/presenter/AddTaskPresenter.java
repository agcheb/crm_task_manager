package com.gb.students.crm_task_manager.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.gb.students.crm_task_manager.view.AddTaskView;

import io.reactivex.Scheduler;

@InjectViewState
public class AddTaskPresenter extends MvpPresenter<AddTaskView> {
    private Scheduler scheduler;

    public AddTaskPresenter(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().init();
    }

    public void onDateClicked() {
        getViewState().toast("date picker clicked");
    }

    public void onAddSubtaskClicked() {
       getViewState(). toast("add subtask clicked");
    }

    public void applyNewTask() {
        getViewState(). toast("add new task applied ");
    }

    public void addContact() {
        getViewState(). toast("add contact to task ");
    }
}
