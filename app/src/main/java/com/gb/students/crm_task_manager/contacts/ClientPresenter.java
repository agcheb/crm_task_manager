package com.gb.students.crm_task_manager.contacts;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.gb.students.crm_task_manager.model.cache.paper.PaperContactsRepo;
import com.gb.students.crm_task_manager.model.entity.contact.Contact;
import com.gb.students.crm_task_manager.model.repos.ContactsRepo;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

@InjectViewState
public class ClientPresenter extends MvpPresenter<FragmentClientView> {

    private Scheduler scheduler;

    private List<Contact> tempContactList;

    private HashMap<String, Bitmap> cashedImages = new HashMap<>();

    private ContactsRepo contactsRepo = new PaperContactsRepo();

    public ClientPresenter(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().init();
    }


    private String userName = "qwerty";

    @SuppressLint("CheckResult")
    public void loadData() {

        contactsRepo.getContacts()
                .subscribeOn(Schedulers.io())
                .observeOn(scheduler)
                .subscribe(contacts -> {
                    tempContactList = new ArrayList<>();
                    tempContactList.addAll(contacts);
                    getViewState().updateClientsList();
                });

    }

    public void bindRepoListRow(int position, RepoRowView holder) {

        Contact tempC = tempContactList.get(position);

        Bitmap bitmap = null;

        if (tempC.getImagePath() != null) {
            String contactId = tempC.getId();
            if (cashedImages.containsKey(contactId)) bitmap = cashedImages.get(contactId);
            else {
                bitmap = BitmapFactory.decodeFile(tempC.getImagePath());
                cashedImages.put(contactId, bitmap);
            }
        }

        holder.setTitle(tempC.getName(), bitmap);
    }

    public int getRepoCount() {

        return tempContactList == null ? 0 : tempContactList.size();

    }

    public void onItemClick(int adapterPosition) {
        Timber.d("Position clicked  %s", adapterPosition);

        contactsRepo.setCurrentContact(tempContactList.get(adapterPosition))
                .observeOn(scheduler)
                .subscribeOn(Schedulers.io())
                .subscribe(boo ->
                        getViewState().openProfile(tempContactList.get(adapterPosition))
               );

    }


    @SuppressLint("CheckResult")
    private void getClientList() {
//        userData.getClients(userCRM.getId())
//                .subscribeOn(Schedulers.io())
//                .observeOn(scheduler)
//                .subscribe(clients -> {
//                    userCRM.setClientsList(clients);
//                    getViewState().updateClientsList();
//                });
    }

    public void setContactList(List<Contact> contactList) {
        tempContactList = new ArrayList<>();
        tempContactList.addAll(contactList);
        getViewState().updateClientsList();
    }
}
