package com.gb.students.crm_task_manager.contacts.profile.tab_fragments.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.gb.students.crm_task_manager.R;
import com.gb.students.crm_task_manager.contacts.profile.tab_fragments.adapters.notifications.RecyclerNotificationAdapter;
import com.gb.students.crm_task_manager.contacts.profile.tab_fragments.presenter.ProfileNotificationPresenter;
import com.gb.students.crm_task_manager.view.base_views.BaseAbstractFragment;

import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class FragmentTabNotifications extends BaseAbstractFragment implements UpdatableView {

    @InjectPresenter
    ProfileNotificationPresenter presenter;

    @ProvidePresenter
    ProfileNotificationPresenter providePresenter() {
        return new ProfileNotificationPresenter(AndroidSchedulers.mainThread());
    }

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
    public void updateList() {

    }

    @Override
    public void init() {

    }
}