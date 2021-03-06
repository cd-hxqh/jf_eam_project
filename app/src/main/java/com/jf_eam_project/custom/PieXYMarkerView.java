
package com.jf_eam_project.custom;

import android.content.Context;
import android.widget.TextView;

import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;
import com.jf_eam_project.R;
import com.jf_eam_project.model.Fgsnussdlview;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Custom implementation of the MarkerView.
 *
 * @author Philipp Jahoda
 */
public class PieXYMarkerView extends MarkerView {

    private TextView tvContent;


    private DecimalFormat format;

    private ArrayList<Fgsnussdlview> fgsnussdlviews;

    public PieXYMarkerView(Context context, ArrayList<Fgsnussdlview> fgsnussdlviews) {
        super(context, R.layout.custom_marker_view);
        this.fgsnussdlviews = fgsnussdlviews;

        tvContent = (TextView) findViewById(R.id.tvContent);
        format = new DecimalFormat("###");
    }

    // callbacks everytime the MarkerView is redrawn, can be used to update the
    // content (user-interface)
    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        tvContent.setText("输变电非计划:" + fgsnussdlviews.get((int) highlight.getX()).SBDFJH + "万kWh"
                + "\n" + "输变电计划:" + fgsnussdlviews.get((int) highlight.getX()).SBDJH + "万kWh"
                + "\n" + "风机非计划:" + fgsnussdlviews.get((int) highlight.getX()).FJFJH + "万kWh"
                + "\n" + "风机非计划:" + fgsnussdlviews.get((int) highlight.getX()).FJJH + "万kWh"
                + "\n" + "电网故障:" + fgsnussdlviews.get((int) highlight.getX()).DWGZ + "万kWh"
                + "\n" + "电网故障:" + fgsnussdlviews.get((int) highlight.getX()).ZRZH + "万kWh"
                + "\n" + "共计:" + fgsnussdlviews.get((int) highlight.getX()).TOTAL + "万kWh");

        super.refreshContent(e, highlight);
    }

    @Override
    public MPPointF getOffset() {
        return new MPPointF(-(getWidth() / 2), -getHeight());
    }
}
