package com.gb.students.crm_task_manager.view;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.gb.students.crm_task_manager.R;

import com.gb.students.crm_task_manager.presenter.AddTaskPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class AddTaskActivity extends MvpAppCompatActivity implements AddTaskView, View.OnClickListener {

    @InjectPresenter
    AddTaskPresenter presenter;

    @ProvidePresenter
    public AddTaskPresenter provide() {
        return new AddTaskPresenter(AndroidSchedulers.mainThread());
    }

    @BindView(R.id.etDatePicker)
    EditText datePickerTv;
    @BindView(R.id.btnSubTask)
    ImageView addSubtask;
    @BindView(R.id.etContact)
    EditText addContact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        ButterKnife.bind(this);

    }

    @Override
    public void init() {
        datePickerTv.setOnClickListener(this);
        addSubtask.setOnClickListener(this);
        addContact.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_add_task, menu);
        return true;
    }

    @Override
    public void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.etDatePicker:
                presenter.onDateClicked();
                break;
            case R.id.btnSubTask:
                presenter.onAddSubtaskClicked();
                break;
            case R.id.etContact:
                presenter.addContact();
                break;
            default:
                break;
        }
    }

    public void showRegistrationActivity(MenuItem item) {
        presenter.applyNewTask();
    }
}
