package com.jf_eam_project.custom;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.text.DecimalFormat;
/**上网电量Y轴数据**/
public class YAxisValueFormatter implements IAxisValueFormatter
{

    private DecimalFormat mFormat;

    public YAxisValueFormatter() {
        mFormat = new DecimalFormat("###,###,###,##");
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {

        return mFormat.format(value);
    }

    @Override
    public int getDecimalDigits() {
        return 1;
    }
}
