package com.gb.students.crm_task_manager.custom;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class Helper {

    public static final int MILISECOND = 1;
    public static final int SECOND = MILISECOND * 1000;
    public static final int MINUTE = SECOND * 60;
    public static final int HOUR = MINUTE * 60;
    public static final int DAY = 24 * HOUR;

    public enum Pattern {DOT_NUMERIC, LITERAL, DAY_VALUE, DAY_TITLE}

    public static SimpleDateFormat getDateFormat(Pattern p) {
        String pattern = "";
        switch (p) {
            case DOT_NUMERIC:
                pattern = "dd. MM. yyyy";
                break;
            case LITERAL:
                pattern = "dd MMMMM yyyy";
                break;
            case DAY_VALUE:
                pattern = "dd";
                break;
            case DAY_TITLE:
                pattern = "E";
                break;
        }
        return new SimpleDateFormat(pattern, Locale.getDefault());
    }


}
