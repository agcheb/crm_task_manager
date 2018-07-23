package com.gb.students.crm_task_manager.contacts.profile.tab_fragments.adapters.tasks;

public interface IListTasksPresenter {
    int pos = -1;
    void bindView(IListTasksRaw view);
    int getViewCount();
    void setComplete(int pos);
}
