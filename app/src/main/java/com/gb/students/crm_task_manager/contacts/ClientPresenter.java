package com.gb.students.crm_task_manager.contacts;

import android.annotation.SuppressLint;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.gb.students.crm_task_manager.contacts.data.TempContact;
import com.gb.students.crm_task_manager.contacts.data.TempDataManager;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.Scheduler;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

@InjectViewState
public class ClientPresenter extends MvpPresenter<FragmentClientView> {

    private Scheduler scheduler;

    TempDataManager dataManager = new TempDataManager();
    List<TempContact> tempContactList;

    public ClientPresenter(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    @Override
    protected void onFirstViewAttach()
    {
        super.onFirstViewAttach();
        getViewState().init();
    }


    private String userName = "qwerty";

    @SuppressLint("CheckResult")
    public void loadData(){

          dataManager.getContactsFromPhone()
                  .subscribeOn(Schedulers.io())
                  .observeOn(scheduler)
                  .subscribe(new Consumer<List<TempContact>>() {
                                 @Override
                                 public void accept(List<TempContact> tempContacts) throws Exception {
                                     tempContactList = new ArrayList<>();
                                     tempContactList.addAll(tempContacts);
                                     getViewState().updateClientsList();
                                 }
                             });

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
        TempContact tempC = tempContactList.get(position);
        holder.setTitle(tempC.getName(),tempC.getNumber(),null);
    }

    public int getRepoCount() {

        return tempContactList == null ? 0 : tempContactList.size();

    }

    public void onItemClick(int adapterPosition) {
       Timber.d("Position clicked  %s", adapterPosition);
       //getViewState().openNewEditClientA(userCRM.getClientsList().get(adapterPosition));
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

}
