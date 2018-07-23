package com.gb.students.crm_task_manager.contacts.profile.tab_fragments.adapters.notifications;

import com.gb.students.crm_task_manager.model.entity.contact.Notification;

/**
 * Created by avetc on 15.07.2018.
 */

public interface IListNotificationsRow {
    int getPos();
    void setNotificaton(Notification notification);
}
