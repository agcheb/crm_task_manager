package com.gb.students.crm_task_manager.view;

import android.app.AlertDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.gb.students.crm_task_manager.R;
import com.gb.students.crm_task_manager.model.entity.types.TaskTypes;
import com.gb.students.crm_task_manager.presenter.AddTaskPresenter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

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

    @BindView(R.id.spinnerTaskTypes)
    Spinner spinnerTaskTypes;
    SimpleDateFormat dateFormat;

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
        dateFormat=new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault());
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
        String[] array = taskTypes.getAll().toArray(new String[0]);
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, array);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTaskTypes.setAdapter(adapter);
    }

    @Override
    public void showDateDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle(getString(R.string.select_date));
        final DatePicker datePicker = new DatePicker(this);
        builder.setView(datePicker);
        builder.setPositiveButton(getResources().getString(R.string.ok),
                (dialog, which) -> {
                    GregorianCalendar calendarBeg=new GregorianCalendar(datePicker.getYear(),
                            datePicker.getMonth(),datePicker.getDayOfMonth());
                    Date date=calendarBeg.getTime();

                    timePickerTv.setEnabled(true);
                    datePickerTv.setText(dateFormat.format(date));
                    presenter.setDate(date);
                }
        );
        builder.show();
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
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle(getString(R.string.add_subtask));
        final EditText editText = new EditText(this);
        builder.setView(editText);
        builder.setPositiveButton(getResources().getString(R.string.ok),
                (dialog, which) -> {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        presenter.addSubtask(editText.getText().toString());
                    }
                }
        );
        builder.show();
    }

    private void showTimeDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle(getString(R.string.select_time));
        final TimePicker picker = new TimePicker(this);
        builder.setView(picker);
        builder.setPositiveButton(getResources().getString(R.string.ok),
                (dialog, which) -> {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        timePickerTv.setText(picker.getHour()+":"+picker.getMinute());
                        presenter.setTime(picker.getHour(), picker.getMinute());
                    }
                }
        );
        builder.show();
    }

    public void showRegistrationActivity(MenuItem item) {
        presenter.applyNewTask(taskTitle.getText().toString(),etNote.getText().toString(), spinnerTaskTypes.getSelectedItem().toString());

    }
}

