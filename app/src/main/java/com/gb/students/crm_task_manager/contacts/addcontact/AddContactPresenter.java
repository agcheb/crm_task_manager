package com.gb.students.crm_task_manager.contacts.addcontact;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.gb.students.crm_task_manager.contacts.profile.ProfileView;
import com.gb.students.crm_task_manager.model.cache.paper.PaperContactsRepo;
import com.gb.students.crm_task_manager.model.cache.paper.PaperImageRepo;
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
    private PaperImageRepo imageRepo = new PaperImageRepo();
    private Contact contact = new Contact();
    private Bitmap tempImage;
    private String tempImageFormat;

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

        String imagePath = imageRepo.saveImage(tempImageFormat,tempImage);
        contact.setImagePath(imagePath);

        contactsRepo.addContact(contact)
                .subscribeOn(Schedulers.io())
                .observeOn(scheduler)
                .subscribe();
    }


    public void setImageBitmap(Bitmap bitmap, String mimeType){
        tempImage = bitmap;
        tempImageFormat = mimeType;
    }
}
