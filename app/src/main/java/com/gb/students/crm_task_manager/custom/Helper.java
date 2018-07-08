package com.gb.students.crm_task_manager.custom;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by avetc on 08.07.2018.
 */

public class Helper {
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
