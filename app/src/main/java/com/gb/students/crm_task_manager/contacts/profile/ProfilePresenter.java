package com.gb.students.crm_task_manager.contacts.profile;

import android.annotation.SuppressLint;
import android.app.TaskStackBuilder;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.gb.students.crm_task_manager.contacts.RepoRowView;
import com.gb.students.crm_task_manager.contacts.morecontacts.ActivityContactMoreView;
import com.gb.students.crm_task_manager.contacts.morecontacts.ContactMorePresenter;
import com.gb.students.crm_task_manager.contacts.morecontacts.ContactMoreRVAdapter;
import com.gb.students.crm_task_manager.model.cache.paper.PaperContactsRepo;
import com.gb.students.crm_task_manager.model.cache.paper.PaperTaskRepo;
import com.gb.students.crm_task_manager.model.cache.paper.PaperTypesRepo;
import com.gb.students.crm_task_manager.model.entity.Task;
import com.gb.students.crm_task_manager.model.entity.contact.Contact;
import com.gb.students.crm_task_manager.model.entity.types.PetTypes;
import com.gb.students.crm_task_manager.model.entity.types.Types;
import com.gb.students.crm_task_manager.model.repos.ContactsRepo;
import com.gb.students.crm_task_manager.model.repos.TaskRepo;
import com.gb.students.crm_task_manager.model.repos.TypesRepo;
import com.gb.students.crm_task_manager.view.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

@InjectViewState
public class ProfilePresenter extends BasePresenter<ProfileView> {

    private List<Task> tasks;
    private Types types;
    private TaskRepo taskRepo;
    private TypesRepo typesRepo;
    private ContactsRepo contactsRepo;

    private Contact contact;

    public Contact getContact() {
        return contact;
    }

    public ProfilePresenter(Scheduler scheduler) {
        super(scheduler);
    }


    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

    }


    public void loadData() {
//        Observable<Types> typesObservable = typesRepo.loadTypes();
//        typesObservable.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(types -> {
//                    this.types = types;
//                });
//
//        Observable<List<Task>> tasksObservable = taskRepo.getTasks();
//        tasksObservable.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(tasks -> {
//                    this.tasks = tasks;
//                });

        Observable<Contact> contactObservable = contactsRepo.getCurrentContact();
        contactObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(contact -> {
                    this.contact = contact;
                    getViewState().initTabFragments();
                });

        //getViewState().getContacts();

//          dataManager.getContactsFromPhone()
//                  .subscribeOn(Schedulers.io())
//                  .observeOn(scheduler)
//                  .subscribe(tempContacts -> {
//                      tempContactList = new ArrayList<>();
//                      tempContactList.addAll(tempContacts);
//                      getViewState().updateClientsList();
//                  });

//        userData.putTags(ClientAddEditPresenter.cs);
//
//        userData.getUser(userName)
//                .subscribeOn(Schedulers.io())
//                .observeOn(scheduler)
//                .subscribe(user ->
//                {
//                    userCRM = user;
//                    getViewState().updateClientsList();
//                    //                    this.user = user;
////                    usersRepo.getUserRepos(user)
////                            .subscribeOn(Schedulers.io())
////                            .observeOn(scheduler)
////                            .subscribe(userRepositories ->
////                            {
////                                this.user.setRepos(userRepositories);
////                                getViewState().hideLoading();
////                                getViewState().showAvatar(user.getAvatarUrl());
////                                getViewState().setUsername(user.getLogin());
////                                getViewState().updateRepoList();
////                            }, throwable ->
////                            {
////                                Timber.e(throwable,"Failed to get user repos");
////                                getViewState().showError(throwable.getMessage());
////                                getViewState().hideLoading();
////                            });
//                }, throwable ->
//                {
//                    Timber.e(throwable, "Failed to get user");
//                    //getViewState().showError(throwable.getMessage());
//                    //getViewState().hideLoading();
//                });

    }

    @Override
    protected void init() {
        contactsRepo = new PaperContactsRepo();
        taskRepo = new PaperTaskRepo();
        typesRepo = new PaperTypesRepo();
    }

    PetTypes getPetTypes() {
        return types.getPetTypes();
    }

    PetTypes getTaskTypes() {
        return types.getPetTypes();
    }


    public List<Task> getTasks() {
        return tasks;
    }

    public Types getTypes() {
        return types;
    }

    public void saveContact(Contact contact) {
        Observable<Boolean> contactObservable = contactsRepo.setCurrentContact(contact);
        contactObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(boo -> {
                     getViewState().toast("Contant information saved");
                });

    }
}
