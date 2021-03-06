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
import com.gb.students.crm_task_manager.contacts.profile.tab_fragments.adapters.tasks.RecyclerTasksAdapter;
import com.gb.students.crm_task_manager.contacts.profile.tab_fragments.presenter.ProfileTaskPresenter;
import com.gb.students.crm_task_manager.contacts.profile.tab_fragments.view.abstractions.ProfileTasksView;
import com.gb.students.crm_task_manager.model.entity.Task;
import com.gb.students.crm_task_manager.model.entity.contact.Contact;
import com.gb.students.crm_task_manager.view.base_views.BaseAbstractFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class FragmentTabTasks extends BaseAbstractFragment implements ProfileTasksView {

    @InjectPresenter
    public ProfileTaskPresenter presenter;

    @ProvidePresenter
    ProfileTaskPresenter providePresenter() {
        return new ProfileTaskPresenter(AndroidSchedulers.mainThread(), dataMapper.getContact());
    }

    private RecyclerTasksAdapter recyclerTasksAdapter;

    @BindView(R.id.recycler_profile_task)
    RecyclerView recyclerView;

    private ContactDataMapper dataMapper;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        dataMapper= (ContactDataMapper) context;
    }

    public static FragmentTabTasks newInstance(Bundle bundle) {
        FragmentTabTasks currentFragment = new FragmentTabTasks();
        Bundle args = new Bundle();
        args.putBundle("gettedArgs", bundle);
        currentFragment.setArguments(args);
        return currentFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile_tasks, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void init() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerTasksAdapter = new RecyclerTasksAdapter(presenter.getTaskListPresenter());
        recyclerView.setAdapter(recyclerTasksAdapter);
    }

    @Override
    public void updateList() {
        recyclerTasksAdapter.notifyDataSetChanged();
    }

    @Override
    public void completeTask(Task task) {
        Contact c = dataMapper.getContact();
        c.getTasks().remove(task);
        dataMapper.saveContact(c);
    }
}
