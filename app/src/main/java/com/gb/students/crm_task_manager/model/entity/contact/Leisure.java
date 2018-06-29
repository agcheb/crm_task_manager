package com.gb.students.crm_task_manager.model.entity.contact;

import java.util.Date;
import java.util.List;

// совместный досуг. с кем, что делали, когда
class Leisure {
    private List<Contact> band;
    private String description;
    private Date date;
    private Integer type;
    private String comment;

    public List<Contact> getBand() {
        return band;
    }

    public void setBand(List<Contact> band) {
        this.band = band;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
