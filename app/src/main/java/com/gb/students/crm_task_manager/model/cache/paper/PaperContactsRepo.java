package com.gb.students.crm_task_manager.model.cache.paper;

import com.gb.students.crm_task_manager.model.entity.Task;
import com.gb.students.crm_task_manager.model.entity.contact.Contact;
import com.gb.students.crm_task_manager.model.entity.contact.Notification;
import com.gb.students.crm_task_manager.model.repos.ContactsRepo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.paperdb.Paper;
import io.reactivex.Observable;
import io.reactivex.annotations.Nullable;
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
            List<Contact> savedContacts = readFromPaper();
            savedContacts.add(contact);
            Timber.d("New contact was written to memory");
            Paper.book("contacts").write("all", savedContacts);
            return true;
        });
    }

    @Override
    public Observable<Boolean> addContacts(List<Contact> contacts) {
        return Observable.fromCallable(() -> {
            List<Contact> savedTasks = readFromPaper();
            savedTasks.addAll(contacts);
            Timber.d("New contacts was written to memory");
            Paper.book("contacts").write("all", savedTasks);
            return true;
        });
    }

    @Override
    public Observable<Contact> getCurrentContact() {
        return Observable.create(emitter -> {
            Contact current= Paper.book("contacts").read("current");

           if (current==null){
                current=dummy();
          }
            Timber.d("Current contact loaded from memory");
            emitter.onNext(current);

        });
    }


    Contact dummy() {
        Contact c = new Contact("John Smith");
        c.setPhone("88003009999");
        c.setMail("sas@saa.ru");
        List<Task> tasks = new ArrayList<>();
        Task task = new Task();
        task.setExpDate(new Date());
        task.setTitle("Super Task");
        tasks.add(task);

        tasks.add(task);
        List<Notification> nts = new ArrayList<>();
        Notification nt = new Notification();
        nt.setDate(new Date());
        nt.setLabel("To understand what is notification");
        nts.add(nt);
        c.setTasks(tasks);
        c.setNotifications(nts);
        return c;
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
