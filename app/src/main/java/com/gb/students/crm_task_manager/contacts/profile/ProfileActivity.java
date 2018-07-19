package com.gb.students.crm_task_manager.contacts.profile;

import android.os.Bundle;
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
import com.gb.students.crm_task_manager.view.base_views.BaseAbstractView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import timber.log.Timber;

public class ProfileActivity extends BaseAbstractView implements ProfileView, ContactDataMapper {

    @BindView(R.id.profile_toolbar)
    Toolbar toolbar;
//    @BindView(R.id.profile_fab)
//    FloatingActionButton fab;


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
        ProfilePresenter presenter = new ProfilePresenter(AndroidSchedulers.mainThread());
        return presenter;
    }


    @Override
    public void init() {
//        label.setText(name);
        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show());

        //  profilePresenter.loadData();
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
