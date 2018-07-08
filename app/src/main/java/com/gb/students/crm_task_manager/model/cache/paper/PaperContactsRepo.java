package com.gb.students.crm_task_manager.model.cache.paper;

import com.gb.students.crm_task_manager.model.entity.contact.Contact;
import com.gb.students.crm_task_manager.model.repos.ContactsRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import io.paperdb.Paper;
import io.reactivex.Observable;
import timber.log.Timber;

public class PaperContactsRepo implements ContactsRepo{
    @Override
    public Observable<List<Contact>> getContacts() {
        return Observable.fromCallable(() -> {
            List<Contact> types =readFromPaper();
            Timber.d("Contacts loaded from memory");
            return types;
        });
    }

    @Override
    public Observable<Boolean> addContact(Contact contact) {
        return Observable.fromCallable(() -> {
            List<Contact> savedTasks = readFromPaper();
            contact.setId(UUID.randomUUID().toString());
            savedTasks.add(contact);
            Timber.d("New contact was written to memory");
            Paper.book("contacts").write("all", savedTasks);
            return true;
        });
    }

    @Override
    public Observable<Boolean> addContacts(List<Contact> contacts) {
        return Observable.fromCallable(() -> {
            List<Contact> savedTasks = readFromPaper();
            for(Contact c: contacts){
                c.setId(UUID.randomUUID().toString());
                savedTasks.add(c);
            }
            Timber.d("New contacts was written to memory");
            Paper.book("contacts").write("all", savedTasks);
            return true;
        });
    }

    @Override
    public Observable<Boolean> removeContact(Contact contact) {
        return Observable.fromCallable(() -> {
            List<Contact> contactList = readFromPaper();
            for(Contact c: contactList) {
                if (c.getName().equals(contact.getName())) {
                    contactList.remove(c);
                }
            }
            Paper.book("contacts").write("all", contactList);
            return true;
        });
    }

    private List<Contact>  readFromPaper(){
        List<Contact>  types = Paper.book("contacts").read("all");
        if (types == null) {
            types = new ArrayList<>();
        }
        return types;
    }
}
