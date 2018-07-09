package com.gb.students.crm_task_manager.contacts.addcontact;


import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;


import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.gb.students.crm_task_manager.R;
import com.jakewharton.rxbinding2.widget.RxTextView;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
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


    private static final String[] permissons = { Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE };
    private static final int PERMISSIONS_REQUEST_ID = 0;

    private static final int RESULT_LOAD_IMG = 1111;
    @BindView(R.id.addcontact_toolbar)    Toolbar toolbar;
    @BindView(R.id.date_contact)    EditText datePicked;
    @BindView(R.id.title_contact) EditText titleContact;
    @BindView(R.id.number_contact) EditText number;
    @BindView(R.id.email_contact) EditText email;
    @BindView(R.id.note_contact) EditText note;
    @BindView(R.id.category_contact) EditText category;
    @BindView(R.id.imageView2)   ImageButton imageButton;


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
        checkPermissions();
    }


    private void checkPermissions()
    {
        for(String permission : permissons)
        {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED)
            {
                requestPermissions();
                return;
            }
        }

        onPermissionsGranted();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_REQUEST_ID: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    onPermissionsGranted();
                }
                else
                {
                    new AlertDialog.Builder(this)
                            .setTitle(R.string.permissons_required)
                            .setMessage(R.string.permissions_required_message)
                            .setPositiveButton("OK", (dialog, which) -> requestPermissions())
                            .setOnCancelListener(dialog -> requestPermissions())
                            .create()
                            .show();
                }
            }
        }
    }


    private void requestPermissions()
    {
        ActivityCompat.requestPermissions(this, permissons, PERMISSIONS_REQUEST_ID);
    }

    private void onPermissionsGranted()
    {
        addContactPresenter.onPermissionsGranted();
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

        imageButton.setOnClickListener(v -> pickImage());

    }

    private void pickImage(){
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, RESULT_LOAD_IMG);
    }

    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                imageButton.setImageBitmap(selectedImage);
                addContactPresenter.setImageBitmap(selectedImage,getMimeType(imageUri));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
            }

        }else {
            Toast.makeText(this, "You haven't picked Image",Toast.LENGTH_LONG).show();
        }
    }


    public String getMimeType(Uri uri) {

        String extension;

        //Check uri format to avoid null
        if (uri.getScheme().equals(ContentResolver.SCHEME_CONTENT)) {
            //If scheme is a content
            final MimeTypeMap mime = MimeTypeMap.getSingleton();
            extension = mime.getExtensionFromMimeType(getContentResolver().getType(uri));
        } else {
            //If scheme is a File
            //This will replace white spaces with %20 and also other special characters. This will avoid returning null values on file name with spaces and special characters.
            extension = MimeTypeMap.getFileExtensionFromUrl(Uri.fromFile(new File(uri.getPath())).toString());

        }

        return extension;
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

