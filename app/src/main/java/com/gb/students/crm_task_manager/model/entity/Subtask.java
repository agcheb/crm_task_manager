package com.gb.students.crm_task_manager.model.entity;

public class Subtask {
    private String title;
    private Boolean isComplete;

    public Subtask(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getComplete() {
        return isComplete;
    }

    public void setComplete(Boolean complete) {
        isComplete = complete;
    }
}
