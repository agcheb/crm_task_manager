package com.gb.students.crm_task_manager.contacts.addcontact;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.format.DateUtils;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.EditText;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.gb.students.crm_task_manager.R;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;


public class AddContactActivity extends MvpAppCompatActivity implements AddContactView, DatePickerDialog.OnDateSetListener {


    @BindView(R.id.addcontact_toolbar)
    Toolbar toolbar;

    @BindView(R.id.date_contact)
    EditText datePicked;


    @InjectPresenter
    AddContactPresenter addContactPresenter;

    @ProvidePresenter
    public AddContactPresenter provideMainPresenter()
    {
        AddContactPresenter presenter = new AddContactPresenter(AndroidSchedulers.mainThread());
        return presenter;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        ButterKnife.bind(this);
    }


    @Override
    public void init() {

        toolbar.setTitle("Add contact");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        addContactPresenter.loadData();

        datePicked.setOnClickListener(v -> {
            onCreateDialog(null).show();
        });

    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(this, this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {

        String format = "dd/mm/yyyy";
        DateFormat sdf = new SimpleDateFormat(format, Locale.US);

        Date today = new Date();

        String month1 = String.valueOf(month + 1);
        String day1 = String.valueOf(day);

        if (day >= 1 && day <= 9) {
            day1 = "0" + day;
        }
        if (month >= 0 && month <= 8) {
            month1 = "0" + month;
        }

        String pickedDate = day1 + "/" + month1 + "/" + year;

        try {
            today = sdf.parse(pickedDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        datePicked.setText(sdf.format(today));

        //datePicked.setText(pickedDate);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }
}

