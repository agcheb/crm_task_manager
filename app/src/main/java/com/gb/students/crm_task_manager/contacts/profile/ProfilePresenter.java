package com.gb.students.crm_task_manager.contacts.profile;

import com.arellomobile.mvp.InjectViewState;
import com.gb.students.crm_task_manager.model.cache.paper.PaperContactsRepo;
import com.gb.students.crm_task_manager.model.cache.paper.PaperTaskRepo;
import com.gb.students.crm_task_manager.model.cache.paper.PaperTypesRepo;
import com.gb.students.crm_task_manager.model.entity.Task;
import com.gb.students.crm_task_manager.model.entity.contact.Contact;
import com.gb.students.crm_task_manager.model.entity.contact.Notification;
import com.gb.students.crm_task_manager.model.entity.types.PetTypes;
import com.gb.students.crm_task_manager.model.entity.types.Types;
import com.gb.students.crm_task_manager.model.repos.ContactsRepo;
import com.gb.students.crm_task_manager.model.repos.TaskRepo;
import com.gb.students.crm_task_manager.model.repos.TypesRepo;
import com.gb.students.crm_task_manager.view.BasePresenter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class ProfilePresenter extends BasePresenter<ProfileView> {

    private List<Task> tasks;
    private Types types;
    private TaskRepo taskRepo;
    private TypesRepo typesRepo;
    private ContactsRepo contactsRepo;

    private Contact contact;

    public Contact getContact() {
        return contact;
    }

    public ProfilePresenter(Scheduler scheduler) {
        super(scheduler);
    }


    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

    }


    Contact dummy() {
        Contact c = new Contact("John Smith");
        c.setPhone("88003009999");
        c.setMail("sas@saa.ru");
        List<Task> tasks = new ArrayList<>();
        Task task = new Task();
        task.setExpDate(new Date());
        task.setTitle("Super Task");

        List<Notification> nts = new ArrayList<>();
        Notification nt = new Notification();
        nt.setDate(new Date());
        nt.setLabel("To understand what is notification");


        return c;
    }

    public void loadData() {


//        Observable<Types> typesObservable = typesRepo.loadTypes();
//        typesObservable.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(types -> {
//                    this.types = types;
//                });
//
//        Observable<List<Task>> tasksObservable = taskRepo.getTasks();
//        tasksObservable.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(tasks -> {
//                    this.tasks = tasks;
//                });

        Observable<Contact> contactObservable = contactsRepo.getCurrentContact();
        contactObservable.subscribeOn(Schedulers.io())
                .observeOn(scheduler)
                .subscribe(contact -> {
                    if (contact == null) this.contact = dummy();
                    else this.contact = contact;

                    getViewState().init();
                    getViewState().initTabFragments();
                });


    }

    @Override
    protected void init() {
        contactsRepo = new PaperContactsRepo();
        taskRepo = new PaperTaskRepo();
        typesRepo = new PaperTypesRepo();
    }

    PetTypes getPetTypes() {
        return types.getPetTypes();
    }

    PetTypes getTaskTypes() {
        return types.getPetTypes();
    }


    public List<Task> getTasks() {
        return tasks;
    }

    public Types getTypes() {
        return types;
    }

    public void saveContact(Contact contact) {


        Observable<Boolean> contactObservable = contactsRepo.setCurrentContact(contact);
        contactObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(boo -> {
                    getViewState().toast("Contant information saved");
                });

    }
}
