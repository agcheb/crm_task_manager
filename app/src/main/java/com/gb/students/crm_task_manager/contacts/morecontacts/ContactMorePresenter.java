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

    public void bindRepoListRow(int position, RepoRowView holder) {
//        if (userCRM != null)
//        {
//            StringBuilder str = new StringBuilder();
//            List<String> tags = userCRM.getClientsList().get(position).getTags();
//            for(String s: tags){
//                str.append(s + "; ");
//            }
//
//            holder.setTitle(userCRM.getClientsList().get(position).getName(),
//                    userCRM.getClientsList().get(position).getContact(),
//                    str.toString());
//        }
        Contact tempC = tempContactList.get(position);
        holder.setTitle(tempC.getName(),null,null);
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



//    @SuppressLint("CheckResult")
//    public void addEditClient(Client newEditClient) {
//
////        userData.putClient(newEditClient,userCRM.getId())
////                //.subscribeOn(Schedulers.io())
////                .observeOn(scheduler)
////                .subscribe(aLong -> {
////                    if (aLong > 0) getClientList();
////                });
//    }

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
