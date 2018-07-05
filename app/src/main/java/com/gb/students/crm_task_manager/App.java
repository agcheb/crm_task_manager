package com.gb.students.crm_task_manager;

import android.app.Application;

import io.paperdb.Paper;


public class App extends Application
{
    private static App instance;

    //private AppComponent appComponent;

    @Override
    public void onCreate()
    {
        super.onCreate();
        instance = this;
        Paper.init(this);


//        appComponent = DaggerAppComponent.builder()
//                .appModule(new AppModule(this))
//                .build();
    }

    public static App getInstance()
    {
        return instance;
    }

//    public AppComponent getAppComponent()
//    {
//        return appComponent;
//    }
}
