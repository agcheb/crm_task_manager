package com.gb.students.crm_task_manager.contacts.profile.tab_fragments.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.gb.students.crm_task_manager.contacts.profile.tab_fragments.adapters.notifications.IListNotificationPresenter;
import com.gb.students.crm_task_manager.contacts.profile.tab_fragments.adapters.notifications.IListNotificationsRow;
import com.gb.students.crm_task_manager.contacts.profile.tab_fragments.view.abstractions.ProfieNotificationView;
import com.gb.students.crm_task_manager.model.entity.contact.Contact;
import com.gb.students.crm_task_manager.model.entity.contact.Notification;
import com.gb.students.crm_task_manager.view.BasePresenter;
import java.util.ArrayList;
import java.util.List;
import io.reactivex.Scheduler;

@InjectViewState
public class ProfileNotificationPresenter extends BasePresenter<ProfieNotificationView> {

    private final Contact contact;
    private List<Notification> notifications;
    public ProfileNotificationPresenter(Scheduler scheduler, Contact contact) {
        super(scheduler);
        this.contact = contact;
    }
    NotificationListPresenter notificationListPresenter;

    public NotificationListPresenter getNotificationListPresenter() {
        return notificationListPresenter;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        this.notificationListPresenter.items=notifications;
    }

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
            getViewState().completeNotification(notification);
            getViewState().update();
        }
    }

    @Override
    protected void init() {
        notificationListPresenter=new NotificationListPresenter();
        getViewState().init();
    }

    @Override
    protected void loadData() {
        notifications = contact.getNotifications();
        getViewState().update();
    }


    public List<Notification> getNotifications() {
        return notifications;
    }
}
