package com.gb.students.crm_task_manager.contacts.data;

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
}
