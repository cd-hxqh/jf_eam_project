package com.jf_eam_project.custom;

import android.util.Log;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.text.DecimalFormat;

/**
 * 月
 * Created by philipp on 02/06/16.
 */
public class MonthAxisValueFormatter implements IAxisValueFormatter {

    private DecimalFormat mFormat;




    public MonthAxisValueFormatter() {
        mFormat = new DecimalFormat("###,###,###,##");
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        Log.i("月度=",mFormat.format(value));
        if (mFormat.format(value).equals("0")) {
            return "";
        }
        return mFormat.format(value) + "月";
    }


    @Override
    public int getDecimalDigits() {
        return 0;
    }
}
