package com.gb.students.crm_task_manager.contacts.profile.tab_fragments.view.abstractions;

import com.gb.students.crm_task_manager.contacts.profile.tab_fragments.view.FragmentTabInfo;
import com.gb.students.crm_task_manager.view.base_views.BaseView;

public interface ProfileInfoView extends BaseView {
    void updateList(FragmentTabInfo.Lists lists);
}
