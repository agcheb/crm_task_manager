package com.gb.students.crm_task_manager.contacts.morecontacts;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(value = AddToEndSingleStrategy.class)
public interface ActivityContactMoreView extends MvpView {
    void init();
    void updateClientsList();

    void getContacts();
}
