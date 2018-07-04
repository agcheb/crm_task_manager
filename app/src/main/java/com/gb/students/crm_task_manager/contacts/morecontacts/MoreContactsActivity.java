package com.gb.students.crm_task_manager.contacts.morecontacts;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.gb.students.crm_task_manager.R;
import com.gb.students.crm_task_manager.contacts.data.TempDataManager;


import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MoreContactsActivity extends MvpAppCompatActivity implements ActivityContactMoreView {

    @BindView(R.id.toolbar_clients_more)     Toolbar toolbar;
    @BindView(R.id.recycler_clients_more)    RecyclerView recyclerView;


    @InjectPresenter ContactMorePresenter contactMorePresenter;

    ContactMoreRVAdapter adapter;

    @ProvidePresenter
    public ContactMorePresenter provideMainPresenter()
    {
        ContactMorePresenter presenter = new ContactMorePresenter(AndroidSchedulers.mainThread());
        return presenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_contacts);
        ButterKnife.bind(this);

    }


    @Override
    public void init() {

        toolbar.setTitle("Choose friends");
        toolbar.setTitleTextColor(Color.WHITE);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
////                        .setAction("Action", null).show();
//                  openNewEditClientA(new Client());
//            }
//        });

        //LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        //recyclerView.setLayoutManager(horizontalLayoutManagaer);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        adapter = new ContactMoreRVAdapter(contactMorePresenter);
        recyclerView.setAdapter(adapter);

        contactMorePresenter.loadData();
    }




    @SuppressLint("CheckResult")
    @Override
    public void getContacts() {
        TempDataManager.getContactsFromPhone(getContentResolver())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(contactMorePresenter::setContactList);
    }

    @Override
    public void updateClientsList() {
        adapter.notifyDataSetChanged();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
               case android.R.id.home:
                    finish();
               case R.id.action_done:
                  finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.contact_more_actions, menu);
        return true;
    }



}