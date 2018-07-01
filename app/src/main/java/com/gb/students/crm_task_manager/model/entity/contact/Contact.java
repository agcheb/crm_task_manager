package com.gb.students.crm_task_manager.model.entity.contact;

import java.util.Date;
import java.util.List;

public class Contact {
    private String name;
    private Date birth;
    private List<Relation> relations;
    private List<Pet> pets;
    private List<ContactInfo> contactInfos;
    private List<Address> adresses;
    private Acquaintance aqt;
    private Work work;
    private List<Leisure> leisures;
    private List<Notification> notifications;
    private List<Gift> gifts;

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

    public List<Relation> getRelations() {
        return relations;
    }

    public void setRelations(List<Relation> relations) {
        this.relations = relations;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

    public List<ContactInfo> getContactInfos() {
        return contactInfos;
    }

    public void setContactInfos(List<ContactInfo> contactInfos) {
        this.contactInfos = contactInfos;
    }

    public List<Address> getAdresses() {
        return adresses;
    }

    public void setAdresses(List<Address> adresses) {
        this.adresses = adresses;
    }

    public Acquaintance getAqt() {
        return aqt;
    }

    public void setAqt(Acquaintance aqt) {
        this.aqt = aqt;
    }

    public Work getWork() {
        return work;
    }

    public void setWork(Work work) {
        this.work = work;
    }

    public List<Leisure> getLeisures() {
        return leisures;
    }

    public void setLeisures(List<Leisure> leisures) {
        this.leisures = leisures;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

    public List<Gift> getGifts() {
        return gifts;
    }

    public void setGifts(List<Gift> gifts) {
        this.gifts = gifts;
    }

    public List<Credit> getCredits() {
        return credits;
    }

    public void setCredits(List<Credit> credits) {
        this.credits = credits;
    }

    private List<Credit> credits;

    public Contact(String name) {
        this.name = name;
    }
}
