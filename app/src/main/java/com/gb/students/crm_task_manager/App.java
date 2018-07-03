package com.gb.students.crm_task_manager;

import android.app.Application;

import io.paperdb.Paper;
import timber.log.Timber;

/**
 * Created by avetc on 03.07.2018.
 */

    public class App extends Application {

    private static App instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Timber.plant(new Timber.DebugTree());
        Paper.init(this);

    }

    public static App getInstance()
    {
        return instance;
    }

}
