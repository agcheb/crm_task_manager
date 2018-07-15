package com.gb.students.crm_task_manager.contacts.profile.tab_fragments.adapters.notifications;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.gb.students.crm_task_manager.R;
import com.gb.students.crm_task_manager.custom.StringHelper;
import com.gb.students.crm_task_manager.model.entity.contact.Notification;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerNotificationAdapter extends RecyclerView.Adapter<RecyclerNotificationAdapter.ViewHolder> {
    IListNotificationPresenter presenter;

    public RecyclerNotificationAdapter(IListNotificationPresenter presenter) {
        this.presenter = presenter;
    }


    public void setPresenter(IListNotificationPresenter presenter) {
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecyclerNotificationAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_profile_notification, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.pos = position;
        presenter.bindView(holder);
    }

    @Override
    public int getItemCount() {
        return presenter.getViewCount();
    }


    class ViewHolder extends RecyclerView.ViewHolder implements IListNotificationsRow,  View.OnClickListener {
        int pos =-1;

        @BindView(R.id.btn_notif_del)
        ImageButton button;
        @BindView(R.id.tv_notif_name)
        TextView nameTv;
        @BindView(R.id.tv_notif_exp_date)
        TextView dateTv;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            button.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            presenter.delRow(pos);
        }

        @Override
        public int getPos() {
            return 0;
        }

        @Override
        public void setNotificaton(Notification notification) {
            nameTv.setText(notification.getLabel());
            dateTv.setText(StringHelper.getDateFormat(StringHelper.Pattern.DOT_NUMERIC).format(notification.getDate()));
        }
    }


}
