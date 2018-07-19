package com.gb.students.crm_task_manager.contacts.addcontact;

public class AdditionalFields {

    private String name;
    private int image_drawable;
    private int count;


    public AdditionalFields(String name, int image_drawable) {
        this.name = name;
        this.image_drawable = image_drawable;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage_drawable() {
        return image_drawable;
    }

    public void setImage_drawable(int image_drawable) {
        this.image_drawable = image_drawable;
    }
}