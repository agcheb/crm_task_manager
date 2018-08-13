package com.gb.students.crm_task_manager.view;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.gb.students.crm_task_manager.R;
import com.gb.students.crm_task_manager.contacts.FragmentClients;
import com.gb.students.crm_task_manager.contacts.tasks.views.FragmentTasks;
import com.gb.students.crm_task_manager.presenter.MainPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends MvpAppCompatActivity implements MainView {


    @InjectPresenter
    MainPresenter mainPresenter;

//    @BindView(R.id.hello_world_tv)
//    TextView firstEverTextView;

    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNavigationView;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                switch (item.getItemId()) {
                    case R.id.action_contacts:
                        transaction.replace(R.id.fragment_container_main, new FragmentClients());
                        break;
                    case R.id.action_tasks:
                        transaction.replace(R.id.fragment_container_main, new FragmentTasks());
                        break;
                    case R.id.action_notifications:
                        //mTextMessage.setText(R.string.title_notifications);
                        return true;
                    case R.id.action_profile:
                        //mTextMessage.setText(R.string.title_notifications);
                        return true;
                }
                transaction.addToBackStack(null);
                transaction.commit();
                return true;
            };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showContacts();

        ButterKnife.bind(this);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 100) {
            showContacts();
        } else {
            Toast.makeText(this, "Until you grant permission we cannot show your contacts", Toast.LENGTH_SHORT).show();
        }
    }

    private void showContacts() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
        } else {
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container_main, new FragmentClients()).commit();

        }
    }

    @ProvidePresenter
    public MainPresenter provideMainPresenter() {
        MainPresenter presenter = new MainPresenter();
        return presenter;
    }

    @Override
    public void init() {
        //firstEverTextView.setText("ИНИЦИАЛИЗАЦИЯ!!!");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        List<Fragment> list = getSupportFragmentManager().getFragments();
        for (Fragment f : list) {
            f.onActivityResult(requestCode, resultCode, data);
        }
    }
}
