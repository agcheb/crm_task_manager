package com.gb.students.crm_task_manager.view.base_views;

import com.arellomobile.mvp.MvpView;

/**
 * Created by avetc on 14.07.2018.
 */

public interface BaseView extends MvpView {
    void toast (String msg);
    void init();
}
