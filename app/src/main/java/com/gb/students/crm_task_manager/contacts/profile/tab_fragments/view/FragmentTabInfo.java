package com.gb.students.crm_task_manager.contacts.profile.tab_fragments.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.gb.students.crm_task_manager.R;
import com.gb.students.crm_task_manager.contacts.profile.tab_fragments.adapters.pet.RecyclerPetAdapter;
import com.gb.students.crm_task_manager.contacts.profile.tab_fragments.adapters.relative.RecyclerRelativeAdapter;
import com.gb.students.crm_task_manager.contacts.profile.tab_fragments.presenter.ProfileInfoPresenter;
import com.gb.students.crm_task_manager.custom.DialogBuilder;
import com.gb.students.crm_task_manager.custom.StringHelper;
import com.gb.students.crm_task_manager.model.entity.contact.Relation;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class FragmentTabInfo extends MvpAppCompatFragment implements ProfileInfoView, View.OnClickListener {


    public enum Lists {RELATIONS, PETS}

    @InjectPresenter
    ProfileInfoPresenter presenter;

    @ProvidePresenter
    ProfileInfoPresenter providePresenter() {
        return new ProfileInfoPresenter(AndroidSchedulers.mainThread());
    }

    RecyclerRelativeAdapter relativeAdapter;
    RecyclerPetAdapter petAdapter;

    @BindView(R.id.info_profile_note)
    TextView infoProfileNote;
    @BindView(R.id.tv_phone_number)
    TextView phoneNumberTv;
    @BindView(R.id.tv_phone_type)
    TextView phoneTypeTv;
    @BindView(R.id.iv_profile_mail)
    ImageButton profileMailIV;
    @BindView(R.id.iv_profile_phone)
    ImageButton profilePhoneIV;
    @BindView(R.id.tv_mail)
    TextView mailTv;
    @BindView(R.id.tv_mail_type)
    TextView mailTypeTv;

    @BindView(R.id.list_view_relatives)
    RecyclerView relativesRV;
    @BindView(R.id.list_view_pets)
    RecyclerView petsRV;
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
        relativesRV.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        relativeAdapter = new RecyclerRelativeAdapter(presenter.getRelativeListPresenter());
        petsRV.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        petAdapter = new RecyclerPetAdapter(presenter.getPetsListPresenter());

        relativesRV.setAdapter(relativeAdapter);
        petsRV.setAdapter(petAdapter);

        addRelativeBtn.setOnClickListener(this);
        addPetBtn.setOnClickListener(this);
        profileMailIV.setOnClickListener(this);
        profilePhoneIV.setOnClickListener(this);
        mailTv.setOnClickListener(this);
        phoneNumberTv.setOnClickListener(this);
    }

    @Override
    public void toast(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateList(Lists lists) {
        switch (lists) {
            case RELATIONS:
                relativeAdapter.notifyDataSetChanged();
                break;
            case PETS:
                petAdapter.notifyDataSetChanged();
                break;
        }
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
            case R.id.iv_profile_phone:
            case R.id.tv_phone_number:
                callContact();
                break;
            case R.id.tv_mail:
            case R.id.iv_profile_mail:
                mailContact();
                break;
            default:
                break;
        }
    }

    private void mailContact() {
        toast("mail contact clicked");
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{mailTv.getText().toString()});
        startActivity(intent);
    }

    private void callContact() {
        toast("call contact clicked");
        Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumberTv.getText().toString()));
        startActivity(dialIntent);

    }

    private void addPet() {
        //todo даоговое окно нового животного
        toast("pet clicked");
    }

    private void addRelative() {
        List<String> types = new ArrayList<>();
        types.add("Тип1");
        types.add("Тип2");
        types.add("Тип3");
        Relation rel = new Relation();

        DialogBuilder dialog = new DialogBuilder(getContext());
        dialog.initDialog("Добавить родственника")
                .addEditText("name", "Ведите имя")
                .addEditText("note", "Заметка")
                .addSpinner("type", types, pos -> {
                })

                .addDateText("date", text -> {
                    DialogBuilder dialogDate = new DialogBuilder(getContext());
                    dialogDate.initDialog("ВЫбирите дату")
                            .addDatePicker("date")
                            .addOkButton(views -> {
                                DatePicker datePicker = (DatePicker) views.get("date");
                                GregorianCalendar calendarBeg = new GregorianCalendar(datePicker.getYear(),
                                        datePicker.getMonth(), datePicker.getDayOfMonth());
                                Date date = calendarBeg.getTime();
                                rel.setBirth(calendarBeg.getTime());
                                text.setText(StringHelper.getDateFormat(StringHelper.Pattern.DOT_NUMERIC).format(date));
                            })
                            .show();
                })
                .addOkButton(views -> {
                    EditText etName = (EditText) views.get("name");
                    EditText etNote = (EditText) views.get("note");
                    Spinner spinner = (Spinner) views.get("type");

                    rel.setName(etName.getText().toString());
                    rel.setNote(etNote.getText().toString());
                    rel.setType(types.get(spinner.getSelectedItemPosition()));

                    presenter.addRelation(rel);

                });
        dialog.show();

    }

}
