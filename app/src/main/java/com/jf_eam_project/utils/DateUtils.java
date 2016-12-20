package com.jf_eam_project.utils;

import java.util.Calendar;

/**时间计算天数**/
public class DateUtils {

    private static final String TAG="DateUtils";


    public static int getDays() {
        Calendar c = Calendar.getInstance();
        int days=c.getActualMaximum(Calendar.DAY_OF_MONTH); //获取天数
        return days;
    }



}
