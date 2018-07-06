package com.gb.students.crm_task_manager.contacts;

public interface RepoRowView {
    void setTitle(String title, String number, String tags);

    void setCheckboxInHolder(boolean isCheched);
}
