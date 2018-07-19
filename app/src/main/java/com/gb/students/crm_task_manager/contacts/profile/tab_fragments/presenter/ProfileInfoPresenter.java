package com.gb.students.crm_task_manager.contacts.profile.tab_fragments.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.gb.students.crm_task_manager.contacts.profile.tab_fragments.adapters.pet.IListPetPresenter;
import com.gb.students.crm_task_manager.contacts.profile.tab_fragments.adapters.pet.IListPetRaw;
import com.gb.students.crm_task_manager.contacts.profile.tab_fragments.adapters.relations.IListRelativePresenter;
import com.gb.students.crm_task_manager.contacts.profile.tab_fragments.adapters.relations.IListRelativeRaw;
import com.gb.students.crm_task_manager.contacts.profile.tab_fragments.view.FragmentTabInfo;
import com.gb.students.crm_task_manager.contacts.profile.tab_fragments.view.abstractions.ProfileInfoView;
import com.gb.students.crm_task_manager.model.cache.paper.PaperTypesRepo;
import com.gb.students.crm_task_manager.model.entity.contact.Contact;
import com.gb.students.crm_task_manager.model.entity.contact.Pet;
import com.gb.students.crm_task_manager.model.entity.contact.Relation;
import com.gb.students.crm_task_manager.model.entity.types.Types;
import com.gb.students.crm_task_manager.model.repos.TypesRepo;
import com.gb.students.crm_task_manager.view.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class ProfileInfoPresenter extends BasePresenter<ProfileInfoView> {

    private Contact contact;

    public ProfileInfoPresenter(Scheduler scheduler, Contact contact) {
        super(scheduler);
        this.contact = contact;
    }

    public RelativeListPresenter getRelativeListPresenter() {
        return relativeListPresenter;
    }

    private RelativeListPresenter relativeListPresenter = new RelativeListPresenter();

    public PetsListPresenter getPetsListPresenter() {
        return petsListPresenter;
    }

    private PetsListPresenter petsListPresenter = new PetsListPresenter();

    private Types types;


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
            // items.remove(rel);
            getViewState().toast("Relation " + rel.getName() + " was deleted");
            getViewState().removeRelation(rel);
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
            //items.remove(pet);
            getViewState().toast("Pet " + pet.getName() + " was deleted");
            getViewState().removePet(pet);
            getViewState().updateList(FragmentTabInfo.Lists.PETS);
        }
    }

    private TypesRepo typesRepo;

    @Override
    protected void init() {
        typesRepo = new PaperTypesRepo();
        getViewState().init();
    }

    @Override
    protected void loadData() {
        Observable<Types> typesObservable = typesRepo.loadTypes();
        typesObservable.subscribeOn(Schedulers.io())
                .observeOn(scheduler)
                .subscribe(types -> {
                    this.types = types;
                    relativeListPresenter.items = contact.getRelations();
                    petsListPresenter.items = contact.getPets();
                    getViewState().updateList(FragmentTabInfo.Lists.RELATIONS);
                    getViewState().updateList(FragmentTabInfo.Lists.PETS);
                });
    }


    public void addRelation(Relation relation) {
        getViewState().addRelation(relation);
        getViewState().updateList(FragmentTabInfo.Lists.RELATIONS);
    }

    public void addPet(Pet pet) {
        contact.getPets().add(pet);
        getViewState().addPet(pet);
        getViewState().updateList(FragmentTabInfo.Lists.PETS);
        getViewState().toast(pet.toString());
    }


    public Types getTypes() {
        return types;
    }


}
