package com.gb.students.crm_task_manager.contacts.profile.tab_fragments.adapters.tasks;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import com.gb.students.crm_task_manager.R;
import com.gb.students.crm_task_manager.custom.StringHelper;
import com.gb.students.crm_task_manager.model.entity.Task;
import butterknife.BindView;
import butterknife.ButterKnife;


public class RecyclerTasksAdapter extends RecyclerView.Adapter<RecyclerTasksAdapter.ViewHolder> {
      IListTasksPresenter presenter;

    public RecyclerTasksAdapter(IListTasksPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public RecyclerTasksAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerTasksAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_profile_task, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerTasksAdapter.ViewHolder holder, int position) {
        holder.pos = position;
        presenter.bindView(holder);
    }

    @Override
    public int getItemCount() {
        return presenter.getViewCount();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements IListTasksRaw, View.OnClickListener {
        int pos = -1;
        @BindView(R.id.tv_task_name)
        TextView nameTV;

        @BindView(R.id.is_complete_cb)
        CheckBox isComplCB;
        @BindView(R.id.tv_task_exp_date)
        TextView dateTV;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            isComplCB.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            presenter.setComplete(pos);
        }

        @Override
        public int getPos() {
            return pos;
        }

        @Override
        public void setTask(Task task) {
            nameTV.setText(task.getTitle());
            isComplCB.setChecked(task.getComplete());
            dateTV.setText(StringHelper.getDateFormat(StringHelper.Pattern.DOT_NUMERIC).format(task.getExpDate()));
        }
    }


}
