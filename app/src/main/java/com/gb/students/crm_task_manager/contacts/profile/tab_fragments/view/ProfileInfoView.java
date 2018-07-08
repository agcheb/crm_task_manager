package com.gb.students.crm_task_manager.contacts.profile.tab_fragments.view;

import com.arellomobile.mvp.MvpView;

/**
 * Created by avetc on 08.07.2018.
 */

public interface ProfileInfoView extends MvpView {
    void init();
    void toast(String msg);

    void updateList(FragmentTabInfo.Lists lists);
}
