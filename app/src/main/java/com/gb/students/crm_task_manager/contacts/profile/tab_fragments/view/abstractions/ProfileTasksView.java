package com.gb.students.crm_task_manager.contacts.profile.tab_fragments.view.abstractions;

import com.gb.students.crm_task_manager.model.entity.Task;
import com.gb.students.crm_task_manager.view.base_views.BaseView;

public interface ProfileTasksView extends BaseView{
    void updateList();
    void completeTask(Task task);
}
