package com.gb.students.crm_task_manager.view.base_views;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.gb.students.crm_task_manager.custom.DialogBuilder;

public abstract class BaseAbstractFragment extends MvpAppCompatFragment implements BaseView {

    @Override
    public void toast(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    public abstract void init();

    protected DialogBuilder showDialog(String title) {
        DialogBuilder builder = new DialogBuilder(getContext());
        builder.initDialog(title);
        return builder;
    }


}
