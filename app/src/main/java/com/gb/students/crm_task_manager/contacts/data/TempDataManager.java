package com.gb.students.crm_task_manager.contacts.data;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.ContactsContract;

import com.gb.students.crm_task_manager.model.entity.contact.Contact;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

public class TempDataManager {

    public static Observable<List<Contact>> getContactsFromPhone(ContentResolver c) {

        return  Observable.create(e -> {

//            if (id == ExtraFields.EMPTY_CLIENT) {
//                e.onNext(null);
//            } else {
//                BoxStore boxStore = TelegramApplication.getInstance().getBoxStore();
//                Box<ObjectBoxClient> clientBox = boxStore.boxFor(ObjectBoxClient.class);
//                List<ObjectBoxClient> clientList = clientBox.query().equal(ObjectBoxClient_.userId, id).build().find();
//                e.onNext(clientsListConverter(clientList));
//                //boxStore.close();
//
//
//
// }
            List<Contact> tempContacts = new ArrayList<>();
            Cursor cursor = c.query(ContactsContract.Contacts.CONTENT_URI,null,null, null,null);
            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                tempContacts.add(new Contact(name));
//                if (Integer.parseInt(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
//                    Cursor pCur = c.query(
//                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = "+cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID)),null, null);
//                    while (pCur.moveToNext()) {
//                        listMobileNo.add(pCur.getString(pCur.getColumnIndex("DATA1")));
//                    }
//                    pCur.close();
//                } else
//                    listMobileNo.add("");
            }
            cursor.close();
            e.onNext(tempContacts);
            e.onComplete();
        });
    }





}
