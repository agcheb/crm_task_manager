package com.gb.students.crm_task_manager.contacts.profile.tab_fragments.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.gb.students.crm_task_manager.contacts.profile.tab_fragments.adapters.notifications.IListNotificationPresenter;
import com.gb.students.crm_task_manager.contacts.profile.tab_fragments.adapters.notifications.IListNotificationsRow;
import com.gb.students.crm_task_manager.contacts.profile.tab_fragments.view.abstractions.ProfieNotificationView;
import com.gb.students.crm_task_manager.model.cache.paper.PaperNotificationRepo;
import com.gb.students.crm_task_manager.model.entity.contact.Notification;
import com.gb.students.crm_task_manager.model.repos.NotificationsRepo;
import com.gb.students.crm_task_manager.view.BasePresenter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

@InjectViewState
public class ProfileNotificationPresenter extends BasePresenter<ProfieNotificationView> {

    private List<Notification> notifications;
    public ProfileNotificationPresenter(Scheduler scheduler) {
        super(scheduler);
    }
    NotificationListPresenter notificationListPresenter;

    public NotificationListPresenter getNotificationListPresenter() {
        return notificationListPresenter;
    }

    NotificationsRepo notificationsRepo = new PaperNotificationRepo();

    class NotificationListPresenter implements IListNotificationPresenter {
        List<Notification> items = new ArrayList<>();

        @Override
        public void bindView(IListNotificationsRow view) {
            view.setNotificaton(items.get(view.getPos()));
        }

        @Override
        public int getViewCount() {
            return items.size();
        }

        @Override
        public void delRow(int pos) {
            Notification notification = items.get(pos);
            items.remove(notification);
            getViewState().toast("Notification " + notification.getLabel() + " was deleted");
            getViewState().update();
        }
    }

    @Override
    protected void init() {
        notificationListPresenter =new NotificationListPresenter();
        notificationsRepo = new PaperNotificationRepo();
    }

    @Override
    protected void loadData() {
        Observable<List<Notification>> tasksObservable = notificationsRepo.getNotifications();
        tasksObservable.subscribeOn(scheduler)
                .observeOn(scheduler)
                .subscribe(notifications -> {
                    this.notifications=notifications;
                    Notification nt= new Notification();
                    nt.setLabel("Important notification");
                    nt.setDate(new Date());
                    notifications.add(nt);
                    this.notificationListPresenter.items = notifications;
                    getViewState().update();
                });

    }


    public List<Notification> getNotifications() {
        return notifications;
    }
}
