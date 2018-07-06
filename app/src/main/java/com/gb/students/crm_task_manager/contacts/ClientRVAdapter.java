package com.gb.students.crm_task_manager.contacts;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.gb.students.crm_task_manager.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ClientRVAdapter extends RecyclerView.Adapter<ClientRVAdapter.ViewHolder>
{
    ClientPresenter presenter;

    public ClientRVAdapter(ClientPresenter presenter)
    {
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.client_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        presenter.bindRepoListRow(position, holder);
    }

    @Override
    public int getItemCount()
    {
        return presenter.getRepoCount();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements RepoRowView, View.OnClickListener {

        @BindView(R.id.client_item_title)
        TextView itemTitle;
//        @BindView(R.id.client_item_number)
//        TextView itemNumber;
//        @BindView(R.id.client_item_tags)
//        TextView itemTags;

        public ViewHolder(View itemView)
        {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            presenter.onItemClick(getAdapterPosition());
        }

        @Override
        public void setTitle(String title, String number, String tags)
        {
            itemTitle.setText(title);
            //itemNumber.setText(number);
            //itemTags.setText(tags);
        }

        @Override
        public void setCheckboxInHolder(boolean isCheched) {

        }
    }
}
