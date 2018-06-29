package com.gb.students.crm_task_manager.model.entity.contact;

import java.util.Date;

// информация об отношениях, романтические, родственные ...
public class Relation {
    private Integer type;
    private String name;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    private Date birth;

}
