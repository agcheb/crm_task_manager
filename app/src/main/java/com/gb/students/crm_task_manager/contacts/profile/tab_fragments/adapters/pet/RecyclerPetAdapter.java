package com.gb.students.crm_task_manager.contacts.profile.tab_fragments.adapters.pet;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.gb.students.crm_task_manager.R;
import com.gb.students.crm_task_manager.model.entity.contact.Pet;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by avetc on 09.07.2018.
 */


public class RecyclerPetAdapter extends RecyclerView.Adapter<RecyclerPetAdapter.ViewHolder> {
    private IListPetPresenter presenter;

    public RecyclerPetAdapter(IListPetPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_profile_pet, parent, false));
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

    class ViewHolder extends RecyclerView.ViewHolder implements IListPetRaw, View.OnClickListener {
        int pos = -1;
        @BindView(R.id.petTypeTv)
        TextView typeTV;
        @BindView(R.id.petNameTv)
        TextView nameTV;
        @BindView(R.id.petNoteTv)
        TextView noteTV;
        @BindView(R.id.iv_pet_remove)
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
        public void setPet(Pet pet) {
            typeTV.setText(pet.getType());
            nameTV.setText(pet.getName());
            noteTV.setText(pet.getNote());
        }


        @Override
        public void onClick(View view) {

            presenter.delRow(pos);
        }
    }


}
