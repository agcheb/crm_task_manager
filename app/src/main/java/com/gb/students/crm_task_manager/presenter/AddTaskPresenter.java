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
}
