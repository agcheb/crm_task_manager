package com.gb.students.crm_task_manager.custom;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class StringHelper {
    public enum Pattern {DOT_NUMERIC, LITERAL}

    public static SimpleDateFormat getDateFormat(Pattern p){

        String pattern="";
        switch (p) {
            case DOT_NUMERIC:
                pattern = "dd. MM. yyyy";
                break;
            case LITERAL:
                pattern = "dd MMMMM yyyy";
                break;
        }
        return new SimpleDateFormat(pattern, Locale.getDefault());
    }


}
