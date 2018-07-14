package com.gb.students.crm_task_manager.contacts.profile.tab_fragments.presenter;


import com.arellomobile.mvp.InjectViewState;
import com.gb.students.crm_task_manager.contacts.profile.tab_fragments.view.UpdatableView;
import com.gb.students.crm_task_manager.view.BasePresenter;

import io.reactivex.Scheduler;

@InjectViewState
public class ProfileNotificationPresenter extends BasePresenter<UpdatableView> {


    public ProfileNotificationPresenter(Scheduler scheduler) {
        super(scheduler);
    }

    @Override
    protected void init() {

    }

    @Override
    protected void loadData() {

    }
}
