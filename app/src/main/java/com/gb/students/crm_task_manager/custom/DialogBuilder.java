package com.gb.students.crm_task_manager.custom;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.gb.students.crm_task_manager.R;

import java.util.HashMap;
import java.util.List;

public class DialogBuilder {
    private AlertDialog.Builder builder;
    private HashMap<String, View> views;
    private Context context;
    private LinearLayout linearLayout;
    private LinearLayout.LayoutParams params;
    private ArrayAdapter<String> spinnerAdapter;
    private DialogListener listener;
    private String title;
    public DialogBuilder(Context context, DialogListener listener) {
        this.context = context;
        this.listener = listener;
        views = new HashMap<>();
        linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(5, 5, 5, 5);

    }

    public DialogBuilder initDialog(String title) {
        this.title=title;
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

    public DialogBuilder addSpinner(String name, List<String> vals) {
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, vals.toArray(new String[0]));
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spinner = new Spinner(this.context);
        spinner.setLayoutParams(params);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                listener.onSpinnerSelected(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        views.put(name, spinner);
        return this;
    }

    public DialogBuilder addOkButton() {
        builder.setPositiveButton(context.getResources().getString(R.string.ok),
                (dialog, which) -> {
                  listener.onOkClicked(views, title);
                });
        return this;
    }

    public void show() {
        builder.setView(linearLayout);
        builder.show();
    }

    private void acceptView(View view, String name){
        views.put(name, view);
        linearLayout.addView(view);
    }

}
