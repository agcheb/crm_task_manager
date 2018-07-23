package com.gb.students.crm_task_manager.model.repos;

import com.gb.students.crm_task_manager.model.entity.contact.Notification;
import java.util.List;
import io.reactivex.Observable;

public interface NotificationsRepo {
    Observable<List<Notification>> getNotifications();
    Observable<Boolean> addNotification(Notification task);
    Observable<Boolean> removeNotification(Notification task);
}
