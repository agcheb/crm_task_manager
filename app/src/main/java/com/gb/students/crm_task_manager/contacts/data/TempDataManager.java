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

            List<Contact> tempContacts = new ArrayList<>();
            Cursor cursor = c.query(ContactsContract.Contacts.CONTENT_URI,null,null, null,null);
            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                tempContacts.add(new Contact(name));
            }
            cursor.close();
            e.onNext(tempContacts);
            e.onComplete();
        });
    }





}
