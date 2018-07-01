package com.gb.students.crm_task_manager.contacts;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(value = AddToEndSingleStrategy.class)
public interface FragmentClientView extends MvpView {
    void init();
    void updateClientsList();
    //void openNewEditClientA(Client client);

    void getContacts();
}
