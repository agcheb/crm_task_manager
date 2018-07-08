package com.gb.students.crm_task_manager.contacts.profile.tab_fragments.adapters;

/**
 * Created by avetc on 01.04.2018.
 */

public interface IListPresenter {
    int pos = -1;
    void bindView(IListInfoRaw view);
    int getViewCount();
    void delRelative(int pos);
}
