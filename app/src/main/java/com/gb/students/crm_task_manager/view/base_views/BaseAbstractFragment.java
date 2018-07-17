package com.gb.students.crm_task_manager.view.base_views;

import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;

public abstract class BaseAbstractFragment extends MvpAppCompatFragment implements BaseView {

    @Override
    public void toast(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    public abstract void init();
}
