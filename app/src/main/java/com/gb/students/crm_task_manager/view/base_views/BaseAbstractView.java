package com.gb.students.crm_task_manager.view.base_views;

import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;


public abstract class BaseAbstractView extends MvpAppCompatActivity implements BaseView {
        @Override
        public void toast(String msg) {
                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        }

        public abstract void init();
}
