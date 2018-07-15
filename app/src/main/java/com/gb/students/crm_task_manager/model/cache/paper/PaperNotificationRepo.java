package com.gb.students.crm_task_manager.model.cache.paper;

import com.gb.students.crm_task_manager.model.entity.Task;
import com.gb.students.crm_task_manager.model.entity.contact.Notification;
import com.gb.students.crm_task_manager.model.repos.NotificationsRepo;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;
import io.reactivex.Observable;
import io.reactivex.internal.operators.observable.ObservableFromCallable;
import timber.log.Timber;

public class PaperNotificationRepo implements NotificationsRepo{
    @Override
    public Observable<List<Notification>> getNotifications() {
        return Observable.fromCallable(() -> {
            List<Notification> notifications = readFromPaper();
            Timber.d("Notifications list loaded from memory");
            return notifications;
        });
    }

    @Override
    public Observable<Boolean> addNotification(Notification notification) {
       return new ObservableFromCallable<>(() -> {
           List <Notification> nts = readFromPaper();
           nts.add(notification);
            Timber.d("New notification list was written to memory");
            Paper.book("notifications").write("all", nts);
            return true;
        });
    }

    @Override
    public Observable<Boolean> removeNotification(Notification notification) {
        return Observable.fromCallable(() -> {
            List<Notification> nts = Paper.book("tasks").read("all");
            for (Notification t : nts) {
                if (t.getLabel().equals(notification.getLabel()))
                    nts.remove(t);
            }
            Paper.book("tasks").write("all", nts);
            return true;
        });
    }

    private List<Notification> readFromPaper() {
        List<Notification> notifications = Paper.book("notifications").read("all");
        if (notifications == null) {
            Timber.d("notifications is empty, new one created");
            return new ArrayList<>();
        }
        return notifications;
    }
}
