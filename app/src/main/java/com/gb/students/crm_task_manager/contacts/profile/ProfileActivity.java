package com.gb.students.crm_task_manager.contacts.profile;

import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.gb.students.crm_task_manager.R;
import com.gb.students.crm_task_manager.contacts.profile.tab_fragments.ContactDataMapper;
import com.gb.students.crm_task_manager.contacts.profile.tab_fragments.view.FragmentTabInfo;
import com.gb.students.crm_task_manager.contacts.profile.tab_fragments.view.FragmentTabMoreInfo;
import com.gb.students.crm_task_manager.contacts.profile.tab_fragments.view.FragmentTabNotifications;
import com.gb.students.crm_task_manager.contacts.profile.tab_fragments.view.FragmentTabTasks;
import com.gb.students.crm_task_manager.custom.CustomFragmentPA;
import com.gb.students.crm_task_manager.model.entity.Task;
import com.gb.students.crm_task_manager.model.entity.contact.Contact;
import com.gb.students.crm_task_manager.model.entity.types.Types;
import com.gb.students.crm_task_manager.view.base_views.BaseAbstractView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import timber.log.Timber;

public class ProfileActivity extends BaseAbstractView implements ProfileView, ContactDataMapper {

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


//        title.setText(name);
        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);





//        fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show());

        //  profilePresenter.loadData();
    }

    @Override
    public void initTabFragments() {
        customFragmentPA = new CustomFragmentPA(getSupportFragmentManager());

        fragmentTabInfo = FragmentTabInfo.newInstance(null);
        fragmentTabTasks = FragmentTabTasks.newInstance(null);
        fragmentTabNotifications = FragmentTabNotifications.newInstance(null);
        fragmentTabMoreInfo = FragmentTabMoreInfo.newInstance(null);
        customFragmentPA.addFragment(fragmentTabInfo, getString(R.string.info));
        customFragmentPA.addFragment(fragmentTabTasks, getString(R.string.tasks));
        customFragmentPA.addFragment(fragmentTabNotifications, getString(R.string.notifications));
//        customFragmentPA.addFragment(fragmentTabMoreInfo, getString(R.string.more_info));

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
    public void setCcntact(Contact contact) {
        profilePresenter.saveContact(contact);
    }
}
