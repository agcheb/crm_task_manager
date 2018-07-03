package com.gb.students.crm_task_manager.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.gb.students.crm_task_manager.R;
import com.gb.students.crm_task_manager.contacts.FragmentClients;
import com.gb.students.crm_task_manager.presenter.MainPresenter;

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
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_contacts:
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_container_main, new FragmentClients());
                    transaction.addToBackStack(null);
                    transaction.commit();
                    return true;
                case R.id.action_tasks:
                    //mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.action_notifications:
                    //mTextMessage.setText(R.string.title_notifications);
                    return true;
                case R.id.action_profile:
                    //mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container_main,new FragmentClients()).commit();

        ButterKnife.bind(this);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


    }

    @ProvidePresenter
    public MainPresenter provideMainPresenter()
    {
        MainPresenter presenter = new MainPresenter();
        return presenter;
    }

    @Override
    public void init() {
        //firstEverTextView.setText("ИНИЦИАЛИЗАЦИЯ!!!");
    }
}
