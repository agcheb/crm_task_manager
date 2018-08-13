package com.gb.students.crm_task_manager.contacts.profile.tab_fragments.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.gb.students.crm_task_manager.contacts.profile.tab_fragments.adapters.tasks.IListTasksPresenter;
import com.gb.students.crm_task_manager.contacts.profile.tab_fragments.adapters.tasks.IListTasksRaw;
import com.gb.students.crm_task_manager.contacts.profile.tab_fragments.view.abstractions.ProfileTasksView;
import com.gb.students.crm_task_manager.model.cache.paper.PaperTaskRepo;
import com.gb.students.crm_task_manager.model.entity.Task;
import com.gb.students.crm_task_manager.model.entity.contact.Contact;
import com.gb.students.crm_task_manager.model.repos.TaskRepo;
import com.gb.students.crm_task_manager.view.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Scheduler;

@InjectViewState
public class ProfileTaskPresenter extends BasePresenter<ProfileTasksView> {

    private final Contact contact;
    private List<Task> tasks;
    private TaskListPresenter taskListPresenter;

    public TaskListPresenter getTaskListPresenter() {
        return taskListPresenter;
    }

    private TaskRepo taskRepo;

    public ProfileTaskPresenter(Scheduler scheduler, Contact contact) {
        super(scheduler);
        this.contact = contact;
    }

    private class TaskListPresenter implements IListTasksPresenter {
        List<Task> items = new ArrayList<>();

        @Override
        public void bindView(IListTasksRaw view) {
            view.setTask(items.get(view.getPos()));
        }

        @Override
        public int getViewCount() {
            return items.size();
        }

        @Override
        public void setComplete(int pos) {
            items.get(pos).setComplete(true);
            getViewState().toast(items.get(pos).getTitle() + "is complete");
            items.remove(pos);
            getViewState().completeTask(items.get(pos));
            getViewState().updateList();
        }
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        this.taskListPresenter.items = tasks;

    }

    @Override
    protected void init() {
        taskRepo = new PaperTaskRepo();
        taskListPresenter = new TaskListPresenter();
        getViewState().init();
    }

    @Override
    protected void loadData() {
        tasks = contact.getTasks();
        getViewState().updateList();
    }



}
