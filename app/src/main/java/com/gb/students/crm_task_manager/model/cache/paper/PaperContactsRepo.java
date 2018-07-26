package com.gb.students.crm_task_manager.model.cache.paper;

import com.gb.students.crm_task_manager.model.entity.Task;
import com.gb.students.crm_task_manager.model.entity.contact.Contact;
import com.gb.students.crm_task_manager.model.entity.contact.Notification;
import com.gb.students.crm_task_manager.model.repos.ContactsRepo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import io.paperdb.Paper;
import io.reactivex.Observable;
import io.reactivex.annotations.Nullable;
import timber.log.Timber;

public class PaperContactsRepo implements ContactsRepo {


    @Override
    public Observable<List<Contact>> getContacts() {
        return Observable.fromCallable(() -> {
            List<Contact> types = readFromPaper();
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
            for (Contact c : contacts) {
                c.setId(UUID.randomUUID().toString());
                savedTasks.add(c);
            }
            Timber.d("New contacts was written to memory");
            Paper.book("contacts").write("all", savedTasks);
            return true;
        });
    }

    @Override
    public Observable<Contact> getCurrentContact() {
        return Observable.create(emitter -> {
            Contact current = Paper.book("contacts").read("current");

            if (current == null) {
                throw new Exception("Contact is out of memory");
            }
            Timber.d("Current contact loaded from memory");
            emitter.onNext(current);

        });
    }


    @Override
    public Observable<Boolean> setCurrentContact(Contact contact) {
        return Observable.fromCallable(() -> {
            Paper.book("contacts").write("current", contact);
            Timber.d("Current contacts was written to memory");
            return true;
        });
    }

    @Override
    public Observable<Contact> getContactById(String id) {
        return Observable.fromCallable(() -> {
            List<Contact> contacts = readFromPaper();
            if (contacts != null && contacts.size() > 0)
                for (Contact c : contacts)
                    if (c.getId().equals(id))
                        return c;

            return null;
        });
    }

    @Override
    public Observable<Boolean> saveContact(Contact contact) {
        return Observable.fromCallable(() -> {
            List<Contact> contactList = readFromPaper();
            for (int i = 0; i < contactList.size(); i++)

                if (contactList.get(i).getId().equals(contact.getId())) {
                    contactList.set(i, contact);
                    break;
                }

            Paper.book("contacts").write("all", contactList);
            return true;
        });
    }

    @Override
    public Observable<Boolean> removeContact(Contact contact) {
        return Observable.fromCallable(() -> {
            List<Contact> contactList = readFromPaper();
            for (Contact c : contactList) {
                if (c.getName().equals(contact.getName())) {
                    contactList.remove(c);
                }
            }
            return true;
        });
    }

    private List<Contact> readFromPaper() {
        List<Contact> types = Paper.book("contacts").read("all");
        if (types == null) {
            types = new ArrayList<>();
        }
        return types;
    }
}
