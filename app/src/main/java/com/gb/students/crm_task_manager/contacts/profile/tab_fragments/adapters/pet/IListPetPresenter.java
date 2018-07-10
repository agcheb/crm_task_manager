package com.gb.students.crm_task_manager.contacts.profile.tab_fragments.adapters.pet;

/**
 * Created by avetc on 09.07.2018.
 */

public interface IListPetPresenter {
    int pos = -1;
    void bindView(IListPetRaw view);
    int getViewCount();
    void delRow(int pos);
}
