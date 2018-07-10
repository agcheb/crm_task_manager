package com.gb.students.crm_task_manager.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.gb.students.crm_task_manager.model.entity.types.TaskTypes;

@StateStrategyType(value = AddToEndSingleStrategy.class)
public interface AddTaskView extends MvpView {
    void init();
    void toast(String msg);

    void fillTaskTypesSpinner(TaskTypes taskTypes);

    void showDateDialog();

    void showError(String error);
}
