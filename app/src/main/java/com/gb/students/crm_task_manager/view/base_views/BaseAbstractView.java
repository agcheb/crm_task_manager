package com.gb.students.crm_task_manager.view.base_views;

import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.gb.students.crm_task_manager.custom.DialogBuilder;


public abstract class BaseAbstractView extends MvpAppCompatActivity implements BaseView {
    @Override
    public void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    protected DialogBuilder showDialog(String title) {
        DialogBuilder builder = new DialogBuilder(this);
        builder.initDialog(title);
        return builder;
    }

    public abstract void init();
}
