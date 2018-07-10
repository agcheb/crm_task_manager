package com.gb.students.crm_task_manager.custom;

import android.view.View;

import java.util.HashMap;

/**
 * Created by avetc on 10.07.2018.
 */

public interface DialogListener {
    void onSpinnerSelected(int pos);
    void onOkClicked(HashMap<String, View> views, String title);
}
