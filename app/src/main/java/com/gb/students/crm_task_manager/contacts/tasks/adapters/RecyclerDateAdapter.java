package com.gb.students.crm_task_manager.contacts.tasks.adapters;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gb.students.crm_task_manager.R;
import com.gb.students.crm_task_manager.contacts.tasks.presenter.TasksPresenter;
import com.gb.students.crm_task_manager.custom.Helper;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.gb.students.crm_task_manager.custom.Helper.Pattern.DAY_TITLE;
import static com.gb.students.crm_task_manager.custom.Helper.Pattern.DAY_VALUE;

public class RecyclerDateAdapter  extends RecyclerView.Adapter<RecyclerDateAdapter.ViewHolder>{
    private IDaysListPresenter presenter;

    public RecyclerDateAdapter(IDaysListPresenter presenter) {
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new  ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_day, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.pos = position;
        presenter.bindView(holder);
    }

    @Override
    public int getItemCount(){return presenter.getViewCount();}

    class ViewHolder extends RecyclerView.ViewHolder implements IDayRaw, View.OnClickListener {
        int pos = -1;

        @BindView(R.id.parent)
        ConstraintLayout parent;
        @BindView(R.id.tv_day)
        TextView dayTitleTv;
        @BindView(R.id.bg_circle)
        ImageView circleIv;
        @BindView(R.id.tv_date)
        TextView dayValueTv;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            parent.setOnClickListener(this);
        }


        @Override
        public int getPos() {
            return pos;
        }

        @Override
        public void setDay(TasksPresenter.Day date) {
            dayTitleTv.setText(date.dayTitle);
            dayValueTv.setText(date.dayValue);

//            if (presenter.isSelected(pos)){
//                circleIv.setVisibility(View.VISIBLE);
//                dayValueTv.setTextColor(Color.WHITE);
//            }
        }

        @Override
        public void onClick(View v) {
            presenter.selectRow(pos);
        }

    }


}
