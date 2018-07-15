package com.gb.students.crm_task_manager.contacts.profile.tab_fragments.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.gb.students.crm_task_manager.contacts.profile.tab_fragments.adapters.pet.IListPetPresenter;
import com.gb.students.crm_task_manager.contacts.profile.tab_fragments.adapters.pet.IListPetRaw;
import com.gb.students.crm_task_manager.contacts.profile.tab_fragments.adapters.relations.IListRelativePresenter;
import com.gb.students.crm_task_manager.contacts.profile.tab_fragments.adapters.relations.IListRelativeRaw;
import com.gb.students.crm_task_manager.contacts.profile.tab_fragments.view.FragmentTabInfo;
import com.gb.students.crm_task_manager.contacts.profile.tab_fragments.view.abstractions.ProfileInfoView;
import com.gb.students.crm_task_manager.model.cache.paper.PaperContactsRepo;
import com.gb.students.crm_task_manager.model.cache.paper.PaperTypesRepo;
import com.gb.students.crm_task_manager.model.entity.contact.Pet;
import com.gb.students.crm_task_manager.model.entity.contact.Relation;
import com.gb.students.crm_task_manager.model.entity.types.Types;
import com.gb.students.crm_task_manager.model.repos.ContactsRepo;
import com.gb.students.crm_task_manager.model.repos.TypesRepo;
import com.gb.students.crm_task_manager.view.BasePresenter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class ProfileInfoPresenter extends BasePresenter<ProfileInfoView> {


    public ProfileInfoPresenter(Scheduler scheduler) {
        super(scheduler);
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
    private Types types;
    private ContactsRepo contactsRepo;

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
            getViewState().toast("Relation " + rel.getName() + " was deleted");
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
            getViewState().toast("Pet " + pet.getName() + " was deleted");
            getViewState().updateList(FragmentTabInfo.Lists.PETS);
        }
    }

    TypesRepo typesRepo;
    @Override
    protected void onFirstViewAttach() {
      new PaperTypesRepo();
        super.onFirstViewAttach();
        typesRepo = new PaperTypesRepo();
        relativeListPresenter.items = relations;
        petsListPresenter.items = pets;
        getViewState().updateList(FragmentTabInfo.Lists.RELATIONS);
        getViewState().updateList(FragmentTabInfo.Lists.PETS);
    }

    @Override
    protected void init() {
        contactsRepo=new PaperContactsRepo();
    }

    @Override
    protected void loadData() {
        Observable<Types> typesObservable = typesRepo.loadTypes();
        typesObservable.subscribeOn(Schedulers.io())
                .observeOn(scheduler)
                .subscribe(types -> {
                    this.types = types;
                });

        // todo аменить на реальных
        Relation r1 = new Relation();
        r1.setName("Petr Petrov");
        r1.setType("Brother");
        r1.setBirth(new Date());
        r1.setNote("Badass!");
        Relation r2 = new Relation();
        r2.setName("Masha Mashina");
        r2.setType("Wife");
        r2.setBirth(new Date());
        r2.setNote("Crazy bitch!");

        Pet pet = new Pet();
        pet.setType("Cat");
        pet.setName("Barsik");
        pet.setNote("Likes to pee everywhere.");

        relations = new ArrayList<>();
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

    public void addPet(Pet pet) {
        pets.add(pet);
        getViewState().updateList(FragmentTabInfo.Lists.PETS);
        getViewState().toast(pet.toString());
    }


    public Types getTypes() {
        return types;
    }


}
