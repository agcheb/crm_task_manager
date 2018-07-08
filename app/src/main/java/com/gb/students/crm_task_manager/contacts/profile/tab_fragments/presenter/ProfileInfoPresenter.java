package com.gb.students.crm_task_manager.contacts.profile.tab_fragments.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.gb.students.crm_task_manager.contacts.profile.tab_fragments.view.ProfileInfoView;

import io.reactivex.Scheduler;

@InjectViewState
public class ProfileInfoPresenter extends MvpPresenter<ProfileInfoView> {

    private Scheduler scheduler;

    public ProfileInfoPresenter(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().init();
    }
}
