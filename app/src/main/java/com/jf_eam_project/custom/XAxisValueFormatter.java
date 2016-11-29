package com.jf_eam_project.custom;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.jf_eam_project.model.Fgsnudlview;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * X轴分公司
 **/
public class XAxisValueFormatter implements IAxisValueFormatter {

    private static final String TAG = "XAxisValueFormatter";
    private DecimalFormat mFormat;
    private ArrayList<Fgsnudlview> fgsnudlviews;


    public XAxisValueFormatter(ArrayList<Fgsnudlview> fgsnudlviews) {
        mFormat = new DecimalFormat("###,###,###,##");
        this.fgsnudlviews = fgsnudlviews;
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {



        return fgsnudlviews.get(Integer.valueOf(mFormat.format(value))).FGSDES;

    }

    @Override
    public int getDecimalDigits() {
        return 0;
    }



}
