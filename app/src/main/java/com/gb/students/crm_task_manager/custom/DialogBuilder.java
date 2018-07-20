package com.gb.students.crm_task_manager.custom;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.gb.students.crm_task_manager.R;

import java.util.HashMap;
import java.util.List;

public class DialogBuilder {

    public interface OnButtonListener {
        void click(HashMap<String, View> views, Boolean isClosable, AlertDialog dialog);
    }

    public interface OnSpinnerSelected {
        void select(int pos);
    }

    public interface OnTextDateSelected {
        void select(EditText text);
    }

    private AlertDialog.Builder builder;
    private HashMap<String, View> views;
    private Context context;
    private LinearLayout linearLayout;
    private LinearLayout.LayoutParams params;
    private String title;

    public DialogBuilder(Context context) {
        this.context = context;
        views = new HashMap<>();
        linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setPadding(20, 10, 20, 10);
        params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(10, 10, 10, 30);

    }

    public DialogBuilder initDialog(String title) {
        this.title = title;
        builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        return this;
    }

    public DialogBuilder addEditText(String name, String hint) {
        EditText editText = new EditText(this.context);
        editText.setLayoutParams(params);
        editText.setHint(hint);
        acceptView(editText, name);
        return this;
    }

    public DialogBuilder addSpinner(String name, List<String> vals, OnSpinnerSelected spinnerListener) {
        String[] typesArr = vals.toArray(new String[0]);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, typesArr);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spinner = new Spinner(this.context);
        spinner.setAdapter(spinnerAdapter);
        spinner.setLayoutParams(params);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerListener.select(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        acceptView(spinner, name);

        return this;
    }

    public DialogBuilder addDateText(String name, OnTextDateSelected listener) {
        EditText editText = new EditText(this.context);
        editText.setLayoutParams(params);
        Drawable drawable = context.getResources().getDrawable(R.drawable.ic_date_range_black_24dp);
        editText.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);
        editText.setFocusable(false);
        editText.setLongClickable(false);
        editText.setOnClickListener(v -> listener.select(editText));
        editText.setHint("Выбирите дату");
        acceptView(editText, name);
        return this;
    }

    public DialogBuilder addDatePicker(String name) {
        final DatePicker datePicker = new DatePicker(this.context);
        datePicker.setLayoutParams(params);
        acceptView(datePicker, name);
        return this;
    }
    public DialogBuilder addTimePicker(String name){
        final TimePicker picker = new TimePicker(this.context);
        picker.setLayoutParams(params);
        acceptView(picker, name);
        return this;
    }


    public DialogBuilder onOkClick(OnButtonListener buttonListener) {
        builder.setView(linearLayout);
        builder.setPositiveButton(context.getResources().getString(R.string.ok), (dialog, which) -> {});
        final AlertDialog dialog = builder.create();
        dialog.show();

        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean isClosable = false;
                buttonListener.click(views, isClosable, dialog);
            }
        });
        return this;
    }

    private void acceptView(View view, String name) {
        views.put(name, view);
        linearLayout.addView(view);
    }

    public static Boolean checkEmptyEditableText(EditText... editTexts) {
        for (EditText et : editTexts)
            if (et.getText() == null || et.getText().toString().equals(""))
                return false;

        return true;
    }

    public static Boolean checkViewForNotNull(View... views) {
        for (View view : views)
            if (view  == null )
                return false;

        return true;
    }


}
