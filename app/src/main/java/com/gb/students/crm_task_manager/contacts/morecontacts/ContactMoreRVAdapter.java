package com.gb.students.crm_task_manager.contacts.morecontacts;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.gb.students.crm_task_manager.R;
import com.gb.students.crm_task_manager.contacts.ClientPresenter;
import com.gb.students.crm_task_manager.contacts.RepoRowView;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ContactMoreRVAdapter extends RecyclerView.Adapter<ContactMoreRVAdapter.ViewHolder>
{
    private ContactMorePresenter presenter;

    public ContactMoreRVAdapter(ContactMorePresenter presenter)
    {
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_more_item, parent, false));
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

        @BindView(R.id.contact_item__more_title)    TextView itemTitle;
        @BindView(R.id.image_contact_more)         ImageView imageView;
        @BindView(R.id.checkBox_more)             CheckBox checkBox;

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
    }
}