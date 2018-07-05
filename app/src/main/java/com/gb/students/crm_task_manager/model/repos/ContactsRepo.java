package com.gb.students.crm_task_manager.model.repos;

import com.gb.students.crm_task_manager.model.entity.Task;
import com.gb.students.crm_task_manager.model.entity.contact.Contact;

import java.util.List;

import io.reactivex.Observable;

public interface ContactsRepo {
    Observable<List<Contact>> getContacts();
    Observable<Boolean> addContact(Contact contact);
    Observable<Boolean> removeContact(Contact contact);
    Observable<Boolean> addContacts(List<Contact> contacts);
}
