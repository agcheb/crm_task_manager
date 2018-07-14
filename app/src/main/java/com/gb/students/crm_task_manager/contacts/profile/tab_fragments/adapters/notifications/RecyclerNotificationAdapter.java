package com.gb.students.crm_task_manager.contacts.profile.tab_fragments.adapters.notifications;

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
    private IListNotificationPresenter presenter;

    public RecyclerNotificationAdapter(IListNotificationPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_profile_notification, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.pos = position;
        presenter.bindView(holder);
    }

    @Override
    public int getItemCount() {
        return presenter.getViewCount();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements IListNotificationRaw, View.OnClickListener {
        int pos = -1;
        @BindView(R.id.tv_notif_name)
        TextView nameTV;
        @BindView(R.id.tv_notif_exp_date)
        TextView dateTV;

        @BindView(R.id.btn_notif_del)
        ImageButton removeBtn;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            removeBtn.setOnClickListener(this);
        }

        @Override
        public int getPos() {
            return pos;
        }

        @Override
        public void setNotification(Notification notification) {
            nameTV.setText(notification.getTitle());
            dateTV.setText(StringHelper.getDateFormat(StringHelper.Pattern.DOT_NUMERIC).format(notification.getExpDate()));
        }




        @Override
        public void onClick(View view) {

            presenter.delRow(pos);
        }
    }


}
