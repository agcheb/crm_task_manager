package com.gb.students.crm_task_manager.contacts.profile;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.gb.students.crm_task_manager.R;
import com.gb.students.crm_task_manager.contacts.profile.tab_fragments.ContactDataMapper;
import com.gb.students.crm_task_manager.contacts.profile.tab_fragments.view.FragmentTabInfo;
import com.gb.students.crm_task_manager.contacts.profile.tab_fragments.view.FragmentTabNotifications;
import com.gb.students.crm_task_manager.contacts.profile.tab_fragments.view.FragmentTabTasks;
import com.gb.students.crm_task_manager.custom.CustomFragmentPA;
import com.gb.students.crm_task_manager.model.entity.contact.Contact;
import com.gb.students.crm_task_manager.view.base_views.BaseAbstractActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import timber.log.Timber;

public class ProfileActivity extends BaseAbstractActivity implements ProfileView, ContactDataMapper {

    @BindView(R.id.profile_toolbar)
    Toolbar toolbar;

    @BindView(R.id.fab_profile)
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ButterKnife.bind(this);
    }

    @InjectPresenter
    ProfilePresenter profilePresenter;

    @ProvidePresenter
    public ProfilePresenter provideMainPresenter() {
        Intent intent = getIntent();
        String id = intent.getStringExtra("contactId");
        ProfilePresenter presenter = new ProfilePresenter(AndroidSchedulers.mainThread(), id);
        return presenter;
    }


    @Override
    public void init() {
        setSupportActionBar(toolbar);


        fab.setOnClickListener(view ->
                showDialog("Add")
                        .addChoiceList((pos, choises) -> {
                            switch (pos) {
                                case 0:
                                    toast(choises[pos]);
                                    break;
                                case 1:
                                    toast(choises[pos]);
                                    break;
                            }
                        }, "Tabs", "Notification")
                        .show());

    }

    @Override
    public void initTabFragments() {
        CustomFragmentPA customFragmentPA = new CustomFragmentPA(getSupportFragmentManager());

        FragmentTabInfo fragmentTabInfo = FragmentTabInfo.newInstance(null);
        FragmentTabTasks fragmentTabTasks = FragmentTabTasks.newInstance(null);
        FragmentTabNotifications fragmentTabNotifications = FragmentTabNotifications.newInstance(null);

        customFragmentPA.addFragment(fragmentTabInfo, getString(R.string.info));
        customFragmentPA.addFragment(fragmentTabTasks, getString(R.string.tasks));
        customFragmentPA.addFragment(fragmentTabNotifications, getString(R.string.notifications));

        ViewPager mViewPager = findViewById(R.id.container_tabs);
        mViewPager.setAdapter(customFragmentPA);

        TabLayout tabLayout = findViewById(R.id.tabs_profile);
        tabLayout.setupWithViewPager(mViewPager);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Timber.d("onTabSelected: " + tab.getText());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                Timber.d("onTabUnselected: " + tab.getText());
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                Timber.d("onTabReselected: " + tab.getText());
            }
        });

        toolbar.setTitle(profilePresenter.getContact().getName());

    }


    @Override
    public Contact getContact() {
        return profilePresenter.getContact();
    }

    @Override
    public void saveContact(Contact contact) {
        profilePresenter.saveContact(contact);
    }
}
