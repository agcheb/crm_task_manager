package com.gb.students.crm_task_manager.model.entity;

import com.gb.students.crm_task_manager.model.entity.contact.Contact;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Task {
    private String title;
    private Boolean isComplete;
    private Date creationDate;
    private Date expDate;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String type;
    private List<Subtask> subtasks = new ArrayList<>();
    private List<Contact> contacts;

    public String getTitle() {
        return title;
    }
    public void addSubtask(String subtaskName){
        subtasks.add(new Subtask(subtaskName));
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

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getExpDate() {
        return expDate;
    }

    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }

    public List<Subtask> getSubtasks() {
        return subtasks;
    }

    public void setSubtasks(List<Subtask> subtasks) {
        this.subtasks = subtasks;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    private  String note;
}
