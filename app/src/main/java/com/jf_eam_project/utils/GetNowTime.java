package com.jf_eam_project.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by think on 2015/12/23.
 * yyyy-MM-dd HH:mm:ss格式获取当前时间
 */
public class GetNowTime {
    public static String getTime(){
        SimpleDateFormat df = new SimpleDateFormat("yy-MM-dd HH:mm:ss");//设置日期格式
        return df.format(new Date());
    }
}
