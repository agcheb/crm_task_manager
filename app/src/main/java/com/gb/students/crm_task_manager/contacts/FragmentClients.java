package com.gb.students.crm_task_manager.contacts;

import android.annotation.SuppressLint;
import android.app.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;


import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.gb.students.crm_task_manager.R;
import com.gb.students.crm_task_manager.contacts.data.TempDataManager;
import com.gb.students.crm_task_manager.contacts.morecontacts.MoreContactsActivity;
import com.gb.students.crm_task_manager.contacts.profile.ProfileActivity;
import com.gb.students.crm_task_manager.model.entity.contact.Contact;
import com.gb.students.crm_task_manager.view.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class FragmentClients extends MvpAppCompatFragment implements FragmentClientView {

    @BindView(R.id.toolbar_clients)
    Toolbar toolbar;
    @BindView(R.id.fab_clients)
    FloatingActionButton fab;
    @BindView(R.id.recycler_clients)
    RecyclerView recyclerView;


    //moxy for MVP
    @InjectPresenter ClientPresenter clientPresenter;

    ClientRVAdapter adapter;

    @ProvidePresenter
    public ClientPresenter provideMainPresenter()
    {

        ClientPresenter presenter = new ClientPresenter(AndroidSchedulers.mainThread());
        return presenter;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        super.onCreateView(inflater,parent,savedInstanceState);
        View view = inflater.inflate(R.layout.activity_fragment_clients, parent, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void init() {

        toolbar.setTitle("My Contacts");
        toolbar.setTitleTextColor(Color.WHITE);

        ((MainActivity) getActivity()).setSupportActionBar(toolbar);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        adapter = new ClientRVAdapter(clientPresenter);
        recyclerView.setAdapter(adapter);
        fab.setOnClickListener(view -> showDial());
        setHasOptionsMenu(true);

        clientPresenter.loadData();

    }


    private void showDial(){

        CharSequence options[] = new CharSequence[] {"New one", "Phone contacts"};

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Add contact");
        builder.setItems(options, (dialog, which) -> {
            if (which == 1) {
                 Intent intent = new Intent(getContext(), MoreContactsActivity.class);
                 startActivityForResult(intent,REQUEST_CODE_CLIENT);
            }
        });
        builder.show();

    }


    @Override
    public void updateClientsList() {
        adapter.notifyDataSetChanged();
    }


    @SuppressLint("CheckResult")
    @Override
    public void getContacts() {
        TempDataManager.getContactsFromPhone(getActivity().getContentResolver())
                  .subscribeOn(Schedulers.io())
                  .observeOn(AndroidSchedulers.mainThread())
                  .subscribe(clientPresenter::setContactList);
    }

    private static Integer REQUEST_CODE_CLIENT = 101;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if ((requestCode == REQUEST_CODE_CLIENT) && (resultCode == Activity.RESULT_OK)) {
             clientPresenter.loadData();
        }

    }


    @Override
    public void openProfile(Contact contact) {

        Intent intent = new Intent(getActivity(),ProfileActivity.class);
        intent.putExtra("name", contact.getName());
        startActivity(intent);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //finish();
            case R.id.action_done:
                //contactMorePresenter.addContactToDB();
                //finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.contact_actions, menu);

    }


}
