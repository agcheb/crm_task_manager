package com.gb.students.crm_task_manager.contacts.profile.tab_fragments.adapters.relative;


public interface IListRelativePresenter {
    int pos = -1;
    void bindView(IListRelativeRaw view);
    int getViewCount();
    void delRow(int pos);
}
