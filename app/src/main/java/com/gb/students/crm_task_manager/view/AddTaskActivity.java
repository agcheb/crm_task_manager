package com.gb.students.crm_task_manager.view;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.gb.students.crm_task_manager.presenter.AddTaskPresenter;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class AddTaskActivity extends MvpAppCompatActivity implements AddTaskView {

    @ProvidePresenter
    AddTaskPresenter provide(){
        return new AddTaskPresenter(AndroidSchedulers.mainThread());
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    public void init() {

    }
}
