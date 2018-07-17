package com.gb.students.crm_task_manager.contacts.profile;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.gb.students.crm_task_manager.view.base_views.BaseView;

@StateStrategyType(value = AddToEndSingleStrategy.class)
public interface ProfileView extends BaseView {
    void initTabFragments();
}
