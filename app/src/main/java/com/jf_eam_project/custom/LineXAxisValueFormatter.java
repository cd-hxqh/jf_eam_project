package com.jf_eam_project.custom;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.jf_eam_project.model.Fgsyudlview;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**X轴分公司**/
public class LineXAxisValueFormatter implements IAxisValueFormatter {

    private static final String TAG="LineXAxisValueFormatter";
    private DecimalFormat mFormat;
    private ArrayList<Fgsyudlview> fgsyudlviews;
    public LineXAxisValueFormatter(ArrayList<Fgsyudlview> fgsyudlviews) {
        mFormat = new DecimalFormat("###,###,###,##");
        this.fgsyudlviews=fgsyudlviews;
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {

        return  fgsyudlviews.get(Integer.valueOf(mFormat.format(value))).FGSDES;

    }

    @Override
    public int getDecimalDigits() {
        return 0;
    }
}
