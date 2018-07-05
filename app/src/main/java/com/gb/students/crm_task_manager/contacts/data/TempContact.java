package com.gb.students.crm_task_manager.contacts.data;

import java.util.Objects;

public class TempContact {

    private String name;
    private String number;
    private String id;

    public TempContact(String name, String id) {
        this.name = name;
        this.id = id;
        this.number = "123123123";
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TempContact that = (TempContact) o;
        return that.getId().equals(this.id) &&
                that.getNumber().equals(this.number) &&
                 that.getName().equals(this.name);
    }

}
