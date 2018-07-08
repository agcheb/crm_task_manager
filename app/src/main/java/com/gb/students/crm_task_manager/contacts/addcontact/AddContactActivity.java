package com.gb.students.crm_task_manager.contacts.addcontact;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.gb.students.crm_task_manager.R;
import com.jakewharton.rxbinding2.widget.RxTextView;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;


public class AddContactActivity extends MvpAppCompatActivity implements AddContactView, DatePickerDialog.OnDateSetListener {


    @BindView(R.id.addcontact_toolbar)    Toolbar toolbar;
    @BindView(R.id.date_contact)    EditText datePicked;
    @BindView(R.id.title_contact) EditText titleContact;
    @BindView(R.id.number_contact) EditText number;
    @BindView(R.id.email_contact) EditText email;
    @BindView(R.id.note_contact) EditText note;
    @BindView(R.id.category_contact) EditText category;

    @BindView(R.id.add_contact_listView)   ListView listView;


    private ListViewCustomAdapter customeAdapter;
    private ArrayList<AdditionalFields> imageModelArrayList;

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


    @SuppressLint("CheckResult")
    @Override
    public void init() {

        toolbar.setTitle("Add contact");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        addContactPresenter.loadData();

        datePicked.setOnClickListener(v -> {
            onCreateDialog(null).show();
        });

        RxTextView.textChanges(titleContact).subscribe(charSequence ->
                addContactPresenter.setContactName(charSequence.toString()));

        RxTextView.textChanges(number).subscribe(charSequence ->
                addContactPresenter.setNumber(charSequence.toString()));

        RxTextView.textChanges(email).subscribe(charSequence ->
                addContactPresenter.setEmail(charSequence.toString()));

        RxTextView.textChanges(note).subscribe(charSequence ->
                addContactPresenter.setNote(charSequence.toString()));

        RxTextView.textChanges(category).subscribe(charSequence ->
                addContactPresenter.setCategory(charSequence.toString()));

        addListViewItems();

        customeAdapter = new ListViewCustomAdapter(this,imageModelArrayList);
        listView.setAdapter(customeAdapter);

    }

    private void addListViewItems() {
        imageModelArrayList = new ArrayList<>();
        imageModelArrayList.add(new AdditionalFields("Notifications",R.drawable.ic_notifications_black_24dp));
        imageModelArrayList.add(new AdditionalFields("Relatives",R.drawable.ic_group_black_24dp));
        imageModelArrayList.add(new AdditionalFields("Tasks",R.drawable.ic_playlist_add_check_black_24dp));
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
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
            day1 = "0" + day1;
        }
        if (month >= 0 && month <= 8) {
            month1 = "0" + month1;
        }

        String pickedDate = day1 + "/" + month1 + "/" + year;

        try {
            today = sdf.parse(pickedDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        datePicked.setText(sdf.format(today));
        addContactPresenter.setDate(today);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
            case R.id.action_done:
                addContactPresenter.addNewContactToDB();
                setResult(Activity.RESULT_OK);
                finish();
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.contact_more_actions, menu);
        return true;
    }

}

