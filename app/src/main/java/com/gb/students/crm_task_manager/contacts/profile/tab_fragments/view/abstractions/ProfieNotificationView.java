package com.gb.students.crm_task_manager.contacts.profile.tab_fragments.view.abstractions;

import com.gb.students.crm_task_manager.model.entity.contact.Notification;
import com.gb.students.crm_task_manager.view.base_views.BaseView;

/**
 * Created by avetc on 15.07.2018.
 */

public interface ProfieNotificationView extends BaseView {

    void update();

    void completeNotification(Notification notification);
}
