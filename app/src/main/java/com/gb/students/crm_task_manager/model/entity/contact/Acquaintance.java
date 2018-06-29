package com.gb.students.crm_task_manager.model.entity.contact;

import java.util.Date;

// информация о знакомстве, когда, кто познакомил
class  Acquaintance {

    private String description;
    private Contact introducedBy;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Contact getIntroducedBy() {
        return introducedBy;
    }

    public void setIntroducedBy(Contact introducedBy) {
        this.introducedBy = introducedBy;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    Date date;


}
