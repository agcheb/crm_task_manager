package com.gb.students.crm_task_manager.view.base_views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.Toast;
import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.gb.students.crm_task_manager.contacts.profile.tab_fragments.presenter.ProfileTaskPresenter;
import com.gb.students.crm_task_manager.contacts.profile.tab_fragments.view.FragmentTabInfo;
import com.gb.students.crm_task_manager.view.BasePresenter;

import io.reactivex.android.schedulers.AndroidSchedulers;

public abstract class BaseAbstractFragment extends MvpAppCompatFragment implements BaseView {

//    @InjectPresenter  <T extends BasePresenter>
//    public T presenter;

    @Override
    public void toast(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }


}
