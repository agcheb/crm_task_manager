package com.gb.students.crm_task_manager.view;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.gb.students.crm_task_manager.view.base_views.BaseView;

import io.reactivex.Scheduler;

 
public abstract class BasePresenter<T extends BaseView>  extends MvpPresenter<T>{

    protected Scheduler scheduler;

    public BasePresenter(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        loadData();
        getViewState().init();

    }

    protected abstract void loadData();
    protected abstract void init();
}
