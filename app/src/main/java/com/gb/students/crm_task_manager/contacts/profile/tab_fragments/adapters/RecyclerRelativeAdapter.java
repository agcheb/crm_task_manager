package com.gb.students.crm_task_manager.contacts.profile.tab_fragments.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import com.gb.students.crm_task_manager.R;
import com.gb.students.crm_task_manager.custom.Helper;
import com.gb.students.crm_task_manager.model.entity.contact.Relation;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * RecyclerView адаптер для списка просмотренных городов
 */

public class RecyclerRelativeAdapter extends RecyclerView.Adapter<RecyclerRelativeAdapter.ViewHolder> {
    private IListPresenter presenter;

    public RecyclerRelativeAdapter(IListPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_profile_relative, parent, false));
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

    class ViewHolder extends RecyclerView.ViewHolder implements IListInfoRaw, View.OnClickListener {
        int pos = -1;
        @BindView(R.id.tv_relative_name)
        TextView nameTV;
        @BindView(R.id.text_relative_type)
        TextView typeTV;
        @BindView(R.id.tv_relative_date)
        TextView dateTV;
        @BindView(R.id.tv_relative_descrtiption)
        TextView descriptTV;
        @BindView(R.id.iv_relative_remove)
        Button removeBtn;

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
        public void setWeather(Relation relation) {
            nameTV.setText(relation.getName());
            typeTV.setText(relation.getType());
            dateTV.setText(Helper.getDateFormat(Helper.Pattern.DOT_NUMERIC).format(relation.getBirth()));
            descriptTV.setText(relation.getNote());
        }


        @Override
        public void onClick(View view) {

            presenter.delRelative(pos);
        }
    }


}
