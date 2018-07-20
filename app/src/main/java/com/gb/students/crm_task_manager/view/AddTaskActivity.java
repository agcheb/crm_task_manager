package com.gb.students.crm_task_manager.view;

import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.gb.students.crm_task_manager.R;
import com.gb.students.crm_task_manager.model.entity.types.TaskTypes;
import com.gb.students.crm_task_manager.presenter.AddTaskPresenter;
import com.gb.students.crm_task_manager.view.base_views.BaseAbstractActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;

import static com.gb.students.crm_task_manager.custom.DialogBuilder.checkViewForNotNull;

public class AddTaskActivity extends BaseAbstractActivity implements AddTaskView, View.OnClickListener {

    @InjectPresenter
    AddTaskPresenter presenter;

    @ProvidePresenter
    public AddTaskPresenter provide() {
        return new AddTaskPresenter(AndroidSchedulers.mainThread());
    }

    @BindView(R.id.etTaskLabel)
    EditText taskTitle;
    @BindView(R.id.etNote)
    EditText etNote;
    @BindView(R.id.etDatePicker)
    EditText datePickerTv;
    @BindView(R.id.etTimePicker)
    EditText timePickerTv;
    @BindView(R.id.btnSubTask)
    ImageView addSubtask;
    @BindView(R.id.etContact)
    EditText addContact;
    @BindView(R.id.subtastsList)
    ListView subtaskListView;

    ArrayAdapter<String> adapter;
    @BindView(R.id.spinnerTaskTypes)
    Spinner spinnerTaskTypes;

    private SimpleDateFormat dateFormat;
    private List<String> subtasks;

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
        timePickerTv.setOnClickListener(this);
        dateFormat = new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault());
        subtasks = new ArrayList<>();
        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, subtasks);
        subtaskListView.setAdapter(adapter);
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
    public void fillTaskTypesSpinner(TaskTypes taskTypes) {
        //todo реализовать добавление нового эелемента
        String[] taskTypesArr = taskTypes.getAll().toArray(new String[0]);
        ArrayAdapter<String> spinnerAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, taskTypesArr);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTaskTypes.setAdapter(spinnerAdapter);
        spinnerTaskTypes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {
                presenter.setTaskType(selectedItemPosition);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void showDateDialog() {

        showDialog(getString(R.string.select_date))
                .addDatePicker("date")
                .onOkClick((views, isClosable, dialog) -> {
                    final DatePicker datePicker = (DatePicker) views.get("date");
                    if (checkViewForNotNull(datePicker)) {

                        GregorianCalendar calendarBeg = new GregorianCalendar(datePicker.getYear(),
                                datePicker.getMonth(), datePicker.getDayOfMonth());
                        Date date = calendarBeg.getTime();

                        timePickerTv.setEnabled(true);
                        datePickerTv.setText(dateFormat.format(date));
                        presenter.setDate(date);
                        dialog.dismiss();
                    }

                }) ;

    }

    @Override
    public void showError(String error) {
        toast(error);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.etDatePicker:
                showDateDialog();
                break;
            case R.id.btnSubTask:
                addNewSubtask();
                break;
            case R.id.etContact:
                presenter.addContact();
                break;
            case R.id.etTimePicker:
                showTimeDialog();
                break;
            default:
                break;
        }
    }

    private void addNewSubtask() {

        showDialog(getString(R.string.add_subtask))
                .addEditText("subatask", "Ведите название")
                .onOkClick((views, isTrue, dialog) -> {
                    EditText etName = (EditText) views.get("subatask");
                    if (checkViewForNotNull())
                        if (checkViewForNotNull(etName)) {
                            String sbtLbl = etName.getText().toString();
                            presenter.addSubtask(sbtLbl);
                            subtasks.add(sbtLbl);
                            adapter.notifyDataSetChanged();
                            dialog.dismiss();
                        }
                });

    }

    private void showTimeDialog() {
        showDialog(getString(R.string.select_time))
                .addTimePicker("time")
                .onOkClick((views, isTrue, dialog) -> {
                    TimePicker picker = (TimePicker) views.get("time");
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        timePickerTv.setText(picker.getHour() + ":" + picker.getMinute());
                        presenter.setTime(picker.getHour(), picker.getMinute());
                        dialog.dismiss();
                    }
                });

    }

    public void showRegistrationActivity(MenuItem item) {
        presenter.applyNewTask(taskTitle.getText().toString(), etNote.getText().toString(), spinnerTaskTypes.getSelectedItem().toString());

    }
}

