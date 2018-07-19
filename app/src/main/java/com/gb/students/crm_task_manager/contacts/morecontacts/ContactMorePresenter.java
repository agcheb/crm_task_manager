package com.gb.students.crm_task_manager.contacts.morecontacts;

import android.annotation.SuppressLint;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.gb.students.crm_task_manager.contacts.RepoRowView;
import com.gb.students.crm_task_manager.model.cache.paper.PaperContactsRepo;
import com.gb.students.crm_task_manager.model.entity.contact.Contact;
import com.gb.students.crm_task_manager.model.repos.ContactsRepo;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

@InjectViewState
public class ContactMorePresenter extends MvpPresenter<ActivityContactMoreView> {

    private Scheduler scheduler;

    private ContactsRepo contactsRepo = new PaperContactsRepo();
    private List<Contact> tempContactList;
    private List<Contact> forAddingContactList;

    public ContactMorePresenter(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    @Override
    protected void onFirstViewAttach()
    {
        super.onFirstViewAttach();
        getViewState().init();
        forAddingContactList = new ArrayList<>();
    }


    private String userName = "qwerty";

    @SuppressLint("CheckResult")
    public void loadData(){

        getViewState().getContacts();

    }

    public void bindRepoListRow(int position, RepoRowView holder) {

        Contact tempC = tempContactList.get(position);
        holder.setTitle(tempC.getName(),null);
        //holder.setCheckboxInHolder(doWithContact(position));

        if (forAddingContactList.contains(tempC)) {
            holder.setCheckboxInHolder(true);
        }
        else {
            holder.setCheckboxInHolder(false);
        }

    }

    public int getRepoCount() {

        return tempContactList == null ? 0 : tempContactList.size();

    }

    public void onItemClick(int adapterPosition, RepoRowView holder ) {
       Timber.d("Position clicked  %s", adapterPosition);

       holder.setCheckboxInHolder(doWithContact(adapterPosition));

    }

    private boolean doWithContact(int position){

        Contact temp = tempContactList.get(position);

        boolean isCheched = false;
        if (forAddingContactList.contains(temp)) {
            forAddingContactList.remove(temp);
        }
        else {
            forAddingContactList.add(temp);
            isCheched = true;
        }

        return isCheched;
    }



    @SuppressLint("CheckResult")
    private void getClientList() {
    }

    public void setForAddingContactList(List<Contact> forAddingContactList) {
        tempContactList = new ArrayList<>();
        tempContactList.addAll(forAddingContactList);
        getViewState().updateClientsList();
    }

    public void addContactToDB() {

        contactsRepo.addContacts(forAddingContactList)
                    .subscribeOn(Schedulers.io())
                    .observeOn(scheduler)
                    .subscribe();

    }
}
