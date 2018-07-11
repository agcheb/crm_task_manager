package com.gb.students.crm_task_manager.contacts.profile.tab_fragments.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.gb.students.crm_task_manager.contacts.profile.tab_fragments.adapters.pet.IListPetPresenter;
import com.gb.students.crm_task_manager.contacts.profile.tab_fragments.adapters.pet.IListPetRaw;
import com.gb.students.crm_task_manager.contacts.profile.tab_fragments.adapters.relative.IListRelativePresenter;
import com.gb.students.crm_task_manager.contacts.profile.tab_fragments.adapters.relative.IListRelativeRaw;
import com.gb.students.crm_task_manager.contacts.profile.tab_fragments.view.FragmentTabInfo;
import com.gb.students.crm_task_manager.contacts.profile.tab_fragments.view.ProfileInfoView;
import com.gb.students.crm_task_manager.model.entity.contact.Pet;
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

    public RelativeListPresenter getRelativeListPresenter() {
        return relativeListPresenter;
    }
    private RelativeListPresenter relativeListPresenter = new RelativeListPresenter();

    public PetsListPresenter getPetsListPresenter() {
        return petsListPresenter;
    }

    private PetsListPresenter petsListPresenter = new PetsListPresenter();
    private List<Relation> relations;
    private List<Pet> pets;


    class RelativeListPresenter implements IListRelativePresenter {
        List<Relation> items = new ArrayList<>();
        @Override
        public void bindView(IListRelativeRaw view) {
            view.setRelative(items.get(view.getPos()));
        }

        @Override
        public int getViewCount() {
            return items.size();
        }

        @Override
        public void delRow(int pos) {
            Relation rel = items.get(pos);
            items.remove(rel);
            getViewState().toast("Relation "+rel.getName()+" was deleted");
            getViewState().updateList(FragmentTabInfo.Lists.RELATIONS);
        }
    }

    class PetsListPresenter implements IListPetPresenter {
        List<Pet> items = new ArrayList<>();

        @Override
        public void bindView(IListPetRaw view) {
            view.setPet(items.get(view.getPos()));
        }

        @Override
        public int getViewCount() {
            return items.size();
        }

        @Override
        public void delRow(int pos) {
            Pet pet = items.get(pos);
            items.remove(pet);
            getViewState().toast("Pet "+pet.getName()+" was deleted");
            getViewState().updateList(FragmentTabInfo.Lists.PETS);
        }
    }
    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().init();

        initRelations();
        relativeListPresenter.items=relations;
        petsListPresenter.items=pets;
        getViewState().updateList(FragmentTabInfo.Lists.RELATIONS);
        getViewState().updateList(FragmentTabInfo.Lists.PETS);
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

        Pet pet = new Pet();
        pet.setType("Cat");
        pet.setName("Barsik");
        pet.setNote("Likes to pee everywhere.");

        relations=new ArrayList<>();
        pets = new ArrayList<>();
        pets.add(pet);
        relations.add(r1);
        relations.add(r2);
    }


    public void addRelation(Relation relation) {
        getViewState().toast(relation.toString());
        relations.add(relation);
        getViewState().updateList(FragmentTabInfo.Lists.RELATIONS);

    }

}
