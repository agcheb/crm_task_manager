package com.gb.students.crm_task_manager.contacts.data;

import java.util.List;

import io.reactivex.Observable;

public class TempDataManager {

    public Observable<List<TempContact>> getContactsFromPhone() {

        return  Observable.create(e -> {

//            if (id == ExtraFields.EMPTY_CLIENT) {
//                e.onNext(null);
//            } else {
//                BoxStore boxStore = TelegramApplication.getInstance().getBoxStore();
//                Box<ObjectBoxClient> clientBox = boxStore.boxFor(ObjectBoxClient.class);
//                List<ObjectBoxClient> clientList = clientBox.query().equal(ObjectBoxClient_.userId, id).build().find();
//                e.onNext(clientsListConverter(clientList));
//                //boxStore.close();
//            }

            e.onNext(null);
            e.onComplete();
        });
    }

}
