package com.gb.students.crm_task_manager.contacts.tasks.adapters;

public interface IDaysListPresenter {
    int pos = -1;
    void bindView(IDayRaw view);
    int getViewCount();
    void selectRow(int pos);
    boolean isSelected(int pos);
}
