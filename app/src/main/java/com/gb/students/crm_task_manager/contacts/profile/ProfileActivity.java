package com.gb.students.crm_task_manager.contacts.profile;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.gb.students.crm_task_manager.R;
import com.gb.students.crm_task_manager.contacts.profile.tab_fragments.FragmentTabInfo;
import com.gb.students.crm_task_manager.contacts.profile.tab_fragments.FragmentTabMoreInfo;
import com.gb.students.crm_task_manager.contacts.profile.tab_fragments.FragmentTabNotifications;
import com.gb.students.crm_task_manager.contacts.profile.tab_fragments.FragmentTabTasks;
import com.gb.students.crm_task_manager.custom.CustomFragmentPA;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import timber.log.Timber;

public class ProfileActivity extends MvpAppCompatActivity implements ProfileView {

    @BindView(R.id.profile_toolbar)
    Toolbar toolbar;
//    @BindView(R.id.profile_fab)
//    FloatingActionButton fab;
//@BindView(R.id.content_title)
//TextView title;

    private CustomFragmentPA customFragmentPA;
    private FragmentTabInfo fragmentTabInfo;
    private FragmentTabTasks fragmentTabTasks;
    private FragmentTabNotifications fragmentTabNotifications;
    private FragmentTabMoreInfo fragmentTabMoreInfo;

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

        String name = "Ivan Ivanov";

//        if (getIntent()!=null) {
//            name = getIntent().getExtras().getString("name");
//        }
        toolbar.setTitle(name);
//        title.setText(name);
        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        customFragmentPA = new CustomFragmentPA(getSupportFragmentManager());
        initTabFragments();

        ViewPager mViewPager = findViewById(R.id.container_tabs);
        mViewPager.setAdapter(customFragmentPA);

        TabLayout tabLayout = findViewById(R.id.tabs_profile);
        tabLayout.setupWithViewPager(mViewPager);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Timber.d( "onTabSelected: " + tab.getText());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                Timber.d(  "onTabUnselected: " + tab.getText());
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                Timber.d( "onTabReselected: " + tab.getText());
            }
        });





//        fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show());

        profilePresenter.loadData();
    }

    @RequiresApi
    private void initTabFragments() {
        fragmentTabInfo = FragmentTabInfo.newInstance(null);
        fragmentTabTasks = FragmentTabTasks.newInstance(null);
        fragmentTabNotifications = FragmentTabNotifications.newInstance(null);
        fragmentTabMoreInfo = FragmentTabMoreInfo.newInstance(null);
        customFragmentPA.addFragment(fragmentTabInfo, getString(R.string.info));
        customFragmentPA.addFragment(fragmentTabTasks, getString(R.string.tasks));
        customFragmentPA.addFragment(fragmentTabNotifications, getString(R.string.notifications));
//        customFragmentPA.addFragment(fragmentTabMoreInfo, getString(R.string.more_info));

    }


}
