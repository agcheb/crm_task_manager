package com.gb.students.crm_task_manager.contacts.profile.tab_fragments.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.gb.students.crm_task_manager.R;
import com.gb.students.crm_task_manager.contacts.profile.tab_fragments.ContactDataMapper;
import com.gb.students.crm_task_manager.contacts.profile.tab_fragments.adapters.notifications.RecyclerNotificationAdapter;
import com.gb.students.crm_task_manager.contacts.profile.tab_fragments.presenter.ProfileNotificationPresenter;
import com.gb.students.crm_task_manager.contacts.profile.tab_fragments.view.abstractions.ProfieNotificationView;
import com.gb.students.crm_task_manager.model.entity.contact.Contact;
import com.gb.students.crm_task_manager.model.entity.contact.Notification;
import com.gb.students.crm_task_manager.view.base_views.BaseAbstractFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class FragmentTabNotifications extends BaseAbstractFragment implements ProfieNotificationView {

    @InjectPresenter
    public ProfileNotificationPresenter presenter;

    @ProvidePresenter
    ProfileNotificationPresenter providePresenter() {
        return new ProfileNotificationPresenter(AndroidSchedulers.mainThread(), dataMapper.getContact());
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        dataMapper = (ContactDataMapper) context;
    }

    private ContactDataMapper dataMapper;
    @BindView(R.id.recycler_profile_notifications)
    RecyclerView recyclerView;

    RecyclerNotificationAdapter adapter;

    public static FragmentTabNotifications newInstance(Bundle bundle) {
        FragmentTabNotifications currentFragment = new FragmentTabNotifications();
        Bundle args = new Bundle();
        args.putBundle("gettedArgs", bundle);
        currentFragment.setArguments(args);
        return currentFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile_notification, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void init() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        adapter = new RecyclerNotificationAdapter(presenter.getNotificationListPresenter());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void update() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void completeNotification(Notification notification) {
        Contact c = dataMapper.getContact();
        List<Notification> notifications = c.getNotifications();
        notifications.remove(notification);
        dataMapper.saveContact(c);
    }
}