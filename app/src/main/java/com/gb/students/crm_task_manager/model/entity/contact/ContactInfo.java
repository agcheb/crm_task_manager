package com.gb.students.crm_task_manager.model.entity.contact;

// к какому типу контактов принадлежит (братюни, коллеги, родственники), краткое описание
class  ContactInfo {
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    private  String content;
}
