package com.gb.students.crm_task_manager.contacts.profile.tab_fragments;

import com.gb.students.crm_task_manager.model.entity.contact.Contact;


public interface ContactDataMapper {
    Contact getContact();
    void saveContact(Contact contact);

}
