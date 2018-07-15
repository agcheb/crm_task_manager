package com.gb.students.crm_task_manager.contacts.profile.tab_fragments.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.gb.students.crm_task_manager.contacts.profile.tab_fragments.adapters.tasks.IListTasksPresenter;
import com.gb.students.crm_task_manager.contacts.profile.tab_fragments.adapters.tasks.IListTasksRaw;
import com.gb.students.crm_task_manager.contacts.profile.tab_fragments.view.abstractions.ProfileTasksView;
import com.gb.students.crm_task_manager.model.cache.paper.PaperTaskRepo;
import com.gb.students.crm_task_manager.model.entity.Task;
import com.gb.students.crm_task_manager.model.repos.TaskRepo;
import com.gb.students.crm_task_manager.view.BasePresenter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;

@InjectViewState
public class ProfileTaskPresenter extends BasePresenter<ProfileTasksView>{

    private List<Task> tasks;
    private TaskListPresenter taskListPresenter;
    public TaskListPresenter getTaskListPresenter() {
        return taskListPresenter;
    }
    private TaskRepo taskRepo;

    public ProfileTaskPresenter(Scheduler scheduler) {
        super(scheduler);
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
            getViewState().toast(items.get(pos).getTitle()+ "is complete");
            items.remove(pos);
            getViewState().updateList();
        }
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        this.taskListPresenter.items=tasks;
        getViewState().updateList();
    }

    @Override
    protected void init() {
        taskRepo = new PaperTaskRepo();
        taskListPresenter=new TaskListPresenter();
    }

    @Override
    protected void loadData() {
        Observable<List<Task>> tasksObservable = taskRepo.getTasks();
        tasksObservable.subscribeOn(scheduler)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(tasks -> {
                    this.tasks=tasks;
                });
        //todo заменить на реальные
        tasks=new ArrayList<>();
        Task task = new Task();
        task.setExpDate(new Date());
        task.setTitle("Expanse the worlsd");
        task.setNote("As fast as I can");

        tasks.add(task);

    }

  public List<Task> getTasks()
    {
        return tasks;
    }

}
