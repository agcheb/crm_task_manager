package com.gb.students.crm_task_manager.contacts.profile.tab_fragments.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.gb.students.crm_task_manager.contacts.profile.tab_fragments.adapters.IListInfoRaw;
import com.gb.students.crm_task_manager.contacts.profile.tab_fragments.adapters.IListPresenter;
import com.gb.students.crm_task_manager.contacts.profile.tab_fragments.view.FragmentTabInfo;
import com.gb.students.crm_task_manager.contacts.profile.tab_fragments.view.ProfileInfoView;
import com.gb.students.crm_task_manager.model.entity.contact.Relation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.reactivex.Scheduler;

@InjectViewState
public class ProfileInfoPresenter extends MvpPresenter<ProfileInfoView> {

    private Scheduler scheduler;

    public ProfileInfoPresenter(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public ListPresenter getListPresenter() {
        return listPresenter;
    }
    private ListPresenter listPresenter = new ListPresenter();
    private List<Relation> relations;

    class ListPresenter implements IListPresenter {
        List<Relation> items = new ArrayList<>();
        @Override
        public void bindView(IListInfoRaw view) {
            view.setRelative(items.get(view.getPos()));
        }

        @Override
        public int getViewCount() {
            return items.size();
        }

        @Override
        public void delRelative(int pos) {
            Relation rel = items.get(pos);
            items.remove(rel);
            getViewState().toast("Relation "+rel.getName()+" was deleted");
            getViewState().updateList(FragmentTabInfo.Lists.RELATIONS);
        }
    }


    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().init();

        initRelations();
        listPresenter.items=relations;
        getViewState().updateList(FragmentTabInfo.Lists.RELATIONS);
    }

    private void initRelations() {
        Relation r1 = new Relation();
        r1.setName("Petr Petrov");
        r1.setType("Brother");
        r1.setBirth( new Date());
        r1.setNote("Badass!");
        Relation r2 = new Relation();
        r2.setName("Masha Mashina");
        r2.setType("Wife");
        r2.setBirth( new Date());
        r2.setNote("Crazy bitch!");

        relations=new ArrayList<>();
        relations.add(r1);
        relations.add(r2);
    }
}
