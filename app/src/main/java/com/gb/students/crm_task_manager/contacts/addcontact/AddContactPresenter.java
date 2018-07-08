package com.gb.students.crm_task_manager.contacts.addcontact;

import android.annotation.SuppressLint;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.gb.students.crm_task_manager.contacts.profile.ProfileView;
import com.gb.students.crm_task_manager.model.cache.paper.PaperContactsRepo;
import com.gb.students.crm_task_manager.model.entity.contact.Contact;
import com.gb.students.crm_task_manager.model.repos.ContactsRepo;

import java.util.Date;
import java.util.UUID;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

@InjectViewState
public class AddContactPresenter extends MvpPresenter<AddContactView> {

    private Scheduler scheduler;
    private ContactsRepo contactsRepo = new PaperContactsRepo();
    private Contact contact = new Contact();

    public AddContactPresenter(Scheduler scheduler) {
        this.scheduler = scheduler;
    }


    @Override
    protected void onFirstViewAttach()
    {
        super.onFirstViewAttach();
        getViewState().init();
    }

    @SuppressLint("CheckResult")
    public void loadData(){

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


    public void setContactName(String contactName) {
        if (contactName!=null) contact.setName(contactName);
    }


    public void setDate(Date date) {
        contact.setBirth(date);
    }

    public void setNumber(String number) {
        contact.setNumber(number);
    }

    public void setEmail(String email) {
        contact.setEmail(email);
    }

    public void setNote(String note) {
        contact.setNote(note);
    }

    public void setCategory(String category) {
        contact.setCategory(category);
    }

    public void addNewContactToDB() {
        contactsRepo.addContact(contact)
                .subscribeOn(Schedulers.io())
                .observeOn(scheduler)
                .subscribe();
    }
}
