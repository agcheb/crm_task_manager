package com.gb.students.crm_task_manager.contacts.tasks.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.gb.students.crm_task_manager.contacts.tasks.adapters.IDayRaw;
import com.gb.students.crm_task_manager.contacts.tasks.adapters.IDaysListPresenter;
import com.gb.students.crm_task_manager.contacts.tasks.views.FragmentTasksView;
import com.gb.students.crm_task_manager.custom.Helper;
import com.gb.students.crm_task_manager.view.BasePresenter;

import java.util.ArrayList;

import java.util.Date;
import java.util.List;

import io.reactivex.Scheduler;

import static com.gb.students.crm_task_manager.custom.Helper.DAY;
import static com.gb.students.crm_task_manager.custom.Helper.Pattern.DAY_TITLE;
import static com.gb.students.crm_task_manager.custom.Helper.Pattern.DAY_VALUE;

@InjectViewState
public class TasksPresenter extends BasePresenter<FragmentTasksView> {

    private static final long DAYS_COUNT = 90;
//    private List<Date> days;

    public class Day {
        public Day(String dayTitle, String dayValue) {
            this.dayTitle = dayTitle;
            this.dayValue = dayValue;
        }

        public String dayTitle;
        public String dayValue;

    }

    public TasksPresenter(Scheduler scheduler) {
        super(scheduler);

    }

    public IDaysListPresenter getDaysListPresenter() {
        return listPresenter;
    }

    private DaysListPresenter listPresenter;


    class DaysListPresenter implements IDaysListPresenter {

        List<Day> items = new ArrayList<>();
        int selectedRow = 0;

        @Override
        public void bindView(IDayRaw view) {
            view.setDay(items.get(view.getPos()));
        }

        @Override
        public int getViewCount() {
            return items.size();
        }

        @Override
        public void selectRow(int pos) {
            selectedRow = pos;
            getViewState().updateList();
            getViewState().toast("selected " + pos);
        }

        @Override
        public boolean isSelected(int pos) {
            return selectedRow == pos;
        }
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        this.listPresenter.items = daysss;
    }

    @Override
    protected void init() {
        listPresenter = new DaysListPresenter();
        getViewState().init();
    }

    private List<Day> daysss;

    @Override
    protected void loadData() {
//        days = new ArrayList<>();
        daysss = new ArrayList<>();

        long l = System.currentTimeMillis();
        for (int i = 0; i < DAYS_COUNT; i++) {
            Date today = new Date();
            today.setTime(l);
            Day d = new Day(Helper.getDateFormat(DAY_TITLE).format(today),
                    Helper.getDateFormat(DAY_VALUE).format(today));
            daysss.add(d);
//            days.add(today);
            l += DAY;


        }
        listPresenter.items = daysss;
        getViewState().updateList();
    }


}
