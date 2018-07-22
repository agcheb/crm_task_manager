package com.gb.students.crm_task_manager.contacts.profile.tab_fragments.view;

import android.app.AlertDialog;
import android.content.Context;
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

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.gb.students.crm_task_manager.R;
import com.gb.students.crm_task_manager.contacts.profile.tab_fragments.ContactDataMapper;
import com.gb.students.crm_task_manager.contacts.profile.tab_fragments.adapters.pet.RecyclerPetAdapter;
import com.gb.students.crm_task_manager.contacts.profile.tab_fragments.adapters.relations.RecyclerRelativeAdapter;
import com.gb.students.crm_task_manager.contacts.profile.tab_fragments.presenter.ProfileInfoPresenter;
import com.gb.students.crm_task_manager.contacts.profile.tab_fragments.view.abstractions.ProfileInfoView;
import com.gb.students.crm_task_manager.custom.DialogBuilder;
import com.gb.students.crm_task_manager.custom.StringHelper;
import com.gb.students.crm_task_manager.model.entity.contact.Contact;
import com.gb.students.crm_task_manager.model.entity.contact.Pet;
import com.gb.students.crm_task_manager.model.entity.contact.Relation;
import com.gb.students.crm_task_manager.view.base_views.BaseAbstractFragment;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import timber.log.Timber;

import static com.gb.students.crm_task_manager.custom.DialogBuilder.checkEmptyEditableText;
import static com.gb.students.crm_task_manager.custom.DialogBuilder.checkViewForNotNull;

public class FragmentTabInfo extends BaseAbstractFragment implements ProfileInfoView, View.OnClickListener, View.OnLongClickListener {


    public enum Lists {RELATIONS, PETS}

    @InjectPresenter
    ProfileInfoPresenter presenter;

    @ProvidePresenter
    ProfileInfoPresenter providePresenter() {
        return new ProfileInfoPresenter(AndroidSchedulers.mainThread(), dataMapper.getContact());
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

   private ContactDataMapper dataMapper;
    private Contact contact;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        dataMapper = (ContactDataMapper) context;
        contact=dataMapper.getContact();
        Timber.d("FragmentTabInfo successfully created");
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

        if (contact.getNumber()!=null)
            phoneNumberTv.setText(contact.getNumber());
        if (contact.getEmail()!=null)
            mailTv.setText(contact.getEmail());

        addRelativeBtn.setOnClickListener(this);
        addPetBtn.setOnClickListener(this);
        profileMailIV.setOnClickListener(this);
        profilePhoneIV.setOnClickListener(this);
        profileMailIV.setOnLongClickListener(this);
        profilePhoneIV.setOnLongClickListener(this);
        mailTv.setOnClickListener(this);
        mailTv.setOnLongClickListener(this);
        phoneNumberTv.setOnLongClickListener(this);
        phoneNumberTv.setOnClickListener(this);
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
    public void addRelation(Relation relation) {
        Contact c = dataMapper.getContact();
        c.getRelations().add(relation);
        dataMapper.saveContact(c);
    }

    @Override
    public void addPet(Pet pet) {
        Contact c = dataMapper.getContact();
        dataMapper.getContact().getPets().add(pet);
        dataMapper.saveContact(c);
    }

    @Override
    public void removeRelation(Relation rel) {
        Contact c = dataMapper.getContact();
        c.getRelations().remove(rel);
        dataMapper.saveContact(c);
    }

    @Override
    public void removePet(Pet pet) {
        Contact c = dataMapper.getContact();
        c.getPets().remove(pet);
        dataMapper.saveContact(c);
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

        Pet pet = new Pet();
        showDialog(getResources().getString(R.string.add_relative))
                .addEditText("name", getResources().getString(R.string.type_name))
                .addSpinner("spinner", presenter.getTypes().getPetTypes().getAll(), null)
                .onOkClick((views, window, dialog) -> {
                    EditText etName = (EditText) views.get("name");
                    Spinner spinner = (Spinner) views.get("type");
                    pet.setName(etName.getText().toString());
                    pet.setType(presenter.getTypes().getPetTypes().getAll().get(spinner.getSelectedItemPosition()));
                    if (checkEmptyEditableText(etName)) {
                        presenter.addPet(pet);
                        window.setClosable();
                    }
                });

    }

    @Override
    public boolean onLongClick(View v) {
        switch (v.getId()) {
            case R.id.iv_profile_mail:
            case R.id.tv_mail:
                showDialog(getResources().getString(R.string.edit))
                        .addEditText("mail", getResources().getString(R.string.type_mail))
                        .onOkClick((views, window, dialog) -> {
                            EditText etMail = (EditText) views.get("mail");
                            if (checkEmptyEditableText(etMail)) {
                                dataMapper.getContact().setEmail(etMail.getText().toString());
                                mailTv.setText(etMail.getText().toString());
                                dataMapper.saveContact(dataMapper.getContact());
                                window.setClosable();
                            }
                        });
                break;
            case R.id.iv_profile_phone:
            case R.id.tv_phone_number:
                showDialog(getResources().getString(R.string.edit))
                        .addEditText("phone", getResources().getString(R.string.type_phone))
                        .onOkClick((HashMap<String, View> views, DialogBuilder.Window window, AlertDialog dialog) -> {
                            EditText etPhone = (EditText) views.get("phone");
                            if (checkEmptyEditableText(etPhone)) {
                                dataMapper.getContact().setNumber(etPhone.getText().toString());
                                phoneNumberTv.setText(etPhone.getText().toString());
                                dataMapper.saveContact(dataMapper.getContact());
                                window.setClosable();
                            }
                        });
                break;
            default:
                break;

        }
        return true;
    }


    private void addRelative() {

        Relation rel = new Relation();


        showDialog(getResources().getString(R.string.add_relative))
                .addEditText("name", getResources().getString(R.string.type_name))
                .addEditText("note", getResources().getString(R.string.add_note))
                .addSpinner("type", presenter.getTypes().getRelationTypes().getAll(), pos -> {
                })

                .addDateText("date", text -> {

                    showDialog("Выбирите дату")
                            .addDatePicker("date")
                            .onOkClick((views, window, dialog) -> {
                                DatePicker datePicker = (DatePicker) views.get("date");
                                if (checkViewForNotNull(datePicker)) {
                                    GregorianCalendar calendarBeg = new GregorianCalendar(datePicker.getYear(),
                                            datePicker.getMonth(), datePicker.getDayOfMonth());
                                    Date date = calendarBeg.getTime();
                                    rel.setBirth(calendarBeg.getTime());
                                    text.setText(StringHelper.getDateFormat(StringHelper.Pattern.DOT_NUMERIC).format(date));
                                   window.setClosable();
                                }
                            })
                    ;
                })
                .onOkClick((views, window, dialog) -> {

                    EditText etName = (EditText) views.get("name");
                    EditText etNote = (EditText) views.get("note");
                    Spinner spinner = (Spinner) views.get("type");
                    if (checkViewForNotNull(etName, etNote, spinner))
                        if (checkEmptyEditableText(etName, etNote)) {
                            rel.setName(etName.getText().toString());
                            rel.setNote(etNote.getText().toString());
                            rel.setType(presenter.getTypes().getRelationTypes().getAll().get(spinner.getSelectedItemPosition()));
                            presenter.addRelation(rel);
                            window.setClosable();
                        }
                });


    }

}
