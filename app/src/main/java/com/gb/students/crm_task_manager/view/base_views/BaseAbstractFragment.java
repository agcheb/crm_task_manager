package com.gb.students.crm_task_manager.view.base_views;

import android.widget.Toast;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpAppCompatFragment;
import com.gb.students.crm_task_manager.view.BasePresenter;

public abstract class BaseAbstractFragment<T extends BasePresenter> extends MvpAppCompatFragment implements BaseView {

//    @InjectPresenter
//    T presenter;
//
//    @ProvidePresenter
//    T providePresenter(){
//        return new (BasePresenter)T(AndroidSchedulers.mainThread());
//    }
//


    @Override
    public void toast(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }


}
