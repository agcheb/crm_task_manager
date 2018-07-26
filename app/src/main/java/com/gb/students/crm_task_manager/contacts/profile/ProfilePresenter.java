package com.gb.students.crm_task_manager.contacts.profile;

import android.annotation.SuppressLint;

import com.arellomobile.mvp.InjectViewState;
import com.gb.students.crm_task_manager.model.cache.paper.PaperContactsRepo;
import com.gb.students.crm_task_manager.model.entity.contact.Contact;
import com.gb.students.crm_task_manager.model.repos.ContactsRepo;
import com.gb.students.crm_task_manager.view.BasePresenter;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class ProfilePresenter extends BasePresenter<ProfileView> {

    private final String id;
    private ContactsRepo contactsRepo;
    private Contact contact;


    public Contact getContact() {
        return contact;
    }

      ProfilePresenter(Scheduler scheduler, String id) {
        super(scheduler);
        this.id = id;
    }


    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

    }

    @SuppressLint("CheckResult")
    public void loadData() {
        contactsRepo.getContactById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(scheduler)
                .subscribe(contact -> {
                    if (contact == null) throw new Exception("Read contact errer\nContact is null");
                    else this.contact = contact;
                    getViewState().init();
                    getViewState().initTabFragments();
                });

    }

    @Override
    protected void init() {
        contactsRepo = new PaperContactsRepo();
    }

    @SuppressLint("CheckResult")
    public void saveContact(Contact contact) {
        contactsRepo.saveContact(contact)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(boo -> getViewState().toast("Contant information saved"));

    }

}
