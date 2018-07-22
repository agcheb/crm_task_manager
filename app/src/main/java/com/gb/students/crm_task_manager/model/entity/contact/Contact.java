package com.gb.students.crm_task_manager.model.entity.contact;

import com.gb.students.crm_task_manager.model.entity.Task;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Contact {

    private String id;
    private String name;
    private Date birth;
    private String number;
    private String email;
    private String note;
    private String category;


    private List<Relation> relations;
    private List<Notification> notifications;
    private List<Pet> pets;

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    private List<Task> tasks;
    private List<ContactInfo> contactInfos;
    private List<Address> adresses;
    private Acquaintance aqt;
    private Work work;
    private List<Leisure> leisures;

    private List<Gift> gifts;
    private String imagePath;
    public Contact(){
        initLists();
    }

    private void initLists(){
        relations = new ArrayList<>();
        notifications = new ArrayList<>();
        tasks = new ArrayList<>();
        pets=new ArrayList<>();
        contactInfos=new ArrayList<>();
        adresses=new ArrayList<>();
        leisures=new ArrayList<>();
        gifts=new ArrayList<>();
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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
        initLists();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return ((Contact) o).getName().equals(this.getName());
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", birth=" + birth +
                ", number='" + number + '\'' +
                ", email='" + email + '\'' +
                ", note='" + note + '\'' +
                ", category='" + category + '\'' +
                '}';
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getImagePath() {
        return imagePath;
    }
}
