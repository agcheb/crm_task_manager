package com.gb.students.crm_task_manager.contacts.tasks.adapters;

import com.gb.students.crm_task_manager.contacts.tasks.presenter.TasksPresenter;

import java.util.Date;

public interface IDayRaw {
    int getPos();
    void setDay(TasksPresenter.Day date);
}
