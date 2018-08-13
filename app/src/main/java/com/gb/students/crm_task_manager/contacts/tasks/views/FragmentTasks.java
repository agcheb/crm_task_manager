package com.gb.students.crm_task_manager.contacts.tasks.views;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.gb.students.crm_task_manager.R;
import com.gb.students.crm_task_manager.contacts.tasks.adapters.IDaysListPresenter;
import com.gb.students.crm_task_manager.contacts.tasks.adapters.RecyclerDateAdapter;
import com.gb.students.crm_task_manager.contacts.tasks.presenter.TasksPresenter;
import com.gb.students.crm_task_manager.view.MainActivity;
import com.gb.students.crm_task_manager.view.base_views.BaseAbstractFragment;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class FragmentTasks extends BaseAbstractFragment implements FragmentTasksView {

    @ProvidePresenter
    TasksPresenter providePresenter() {
        return new TasksPresenter(AndroidSchedulers.mainThread());
    }

    @InjectPresenter
    TasksPresenter presenter;
    @BindView(R.id.toolbar_main_tasks)
    Toolbar toolbar;

    @BindView(R.id.recycler_days)
    RecyclerView recyclerDays;

    RecyclerDateAdapter recyclerDateAdapter;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_tasks, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void init() {
        toolbar.setTitle(R.string.tasks);
        toolbar.setTitleTextColor(Color.WHITE);
        ((MainActivity) Objects.requireNonNull(getActivity())).setSupportActionBar(toolbar);

        recyclerDays.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        recyclerDateAdapter = new RecyclerDateAdapter(presenter.getDaysListPresenter());
        recyclerDays.setAdapter(recyclerDateAdapter);
    }

    @Override
    public void updateList() {
        recyclerDateAdapter.notifyDataSetChanged();
    }
}
