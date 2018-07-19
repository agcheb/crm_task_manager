package com.gb.students.crm_task_manager.contacts;

import android.graphics.Bitmap;

public interface RepoRowView {
    void setTitle(String title, Bitmap bitmap);

    void setCheckboxInHolder(boolean isCheched);
}
