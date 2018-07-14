package com.gb.students.crm_task_manager.contacts.profile.tab_fragments.adapters.notifications;

/**
 * Created by avetc on 09.07.2018.
 */

public interface IListNotificationPresenter {
    int pos = -1;
    void bindView(IListNotificationRaw view);
    int getViewCount();
    void delRow(int pos);
}
