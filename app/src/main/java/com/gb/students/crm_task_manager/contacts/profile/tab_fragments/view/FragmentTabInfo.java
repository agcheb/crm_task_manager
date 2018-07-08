package com.gb.students.crm_task_manager.contacts.profile.tab_fragments.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.gb.students.crm_task_manager.R;
import com.gb.students.crm_task_manager.contacts.profile.tab_fragments.presenter.ProfileInfoPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class FragmentTabInfo extends MvpAppCompatFragment implements ProfileInfoView, View.OnClickListener {

    @InjectPresenter
    ProfileInfoPresenter presenter;

    @ProvidePresenter
    ProfileInfoPresenter providePresenter() {
        return new ProfileInfoPresenter(AndroidSchedulers.mainThread());
    }

    @BindView(R.id.info_profile_note)
    TextView infoProfileNote;
    @BindView(R.id.tv_phone_number)
    TextView phoneNumberTv;
    @BindView(R.id.tv_phone_type)
    TextView phoneTypeTv;
    @BindView(R.id.tv_mail)
    TextView mailTv;
    @BindView(R.id.tv_mail_type)
    TextView mailTypeTv;
    @BindView(R.id.list_view_pets)
    ListView petsLV;
    @BindView(R.id.list_view_relatives)
    ListView relativesLV;
    @BindView(R.id.btn_add_relative)
    Button addRelativeBtn;
    @BindView(R.id.btn_add_pet)
    Button addPetBtn;

    public static FragmentTabInfo newInstance(Bundle bundle) {
        FragmentTabInfo currentFragment = new FragmentTabInfo();
        Bundle args = new Bundle();
        args.putBundle("gettedArgs", bundle);
        currentFragment.setArguments(args);
        return currentFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile_info, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void init() {
        addRelativeBtn.setOnClickListener(this);
        addPetBtn.setOnClickListener(this);
    }

    @Override
    public void toast(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add_relative:
                addRelative();
                break;
            case R.id.btn_add_pet:
            addPet();
                break;
            default:
                break;

        }
    }

    private void addPet() {
        //todo даоговое окно нового животного
        toast("pet clicked");
    }

    private void addRelative() {
        toast("relatives clicked");
    }
}
