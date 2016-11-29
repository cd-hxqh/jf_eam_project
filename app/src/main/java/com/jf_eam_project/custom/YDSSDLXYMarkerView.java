
package com.jf_eam_project.custom;

import android.content.Context;
import android.widget.TextView;

import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;
import com.jf_eam_project.R;
import com.jf_eam_project.model.Fgsyussdlview;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Custom implementation of the MarkerView.
 *
 * @author Philipp Jahoda
 */
public class YDSSDLXYMarkerView extends MarkerView {

    private TextView tvContent;


    private DecimalFormat format;

    private ArrayList<Fgsyussdlview> fgsnussdlviews;

    public YDSSDLXYMarkerView(Context context, ArrayList<Fgsyussdlview> fgsnussdlviews) {
        super(context, R.layout.custom_marker_view);
        this.fgsnussdlviews = fgsnussdlviews;

        tvContent = (TextView) findViewById(R.id.tvContent);
        format = new DecimalFormat("###");
    }

    // callbacks everytime the MarkerView is redrawn, can be used to update the
    // content (user-interface)
    @Override
    public void refreshContent(Entry e, Highlight highlight) {

        Fgsyussdlview item = getFgsyussdlview(format.format(e.getY()), fgsnussdlviews);

        tvContent.setText("输变电非计划:" + item.SBDFJH + "万kWh"
                + "\n" + "输变电计划:" + item.SBDJH + "万kWh"
                + "\n" + "风机非计划:" + item.FJFJH + "万kWh"
                + "\n" + "风机非计划:" + item.FJJH + "万kWh"
                + "\n" + "电网故障:" + item.DWGZ + "万kWh"
                + "\n" + "电网故障:" + item.ZRZH + "万kWh"
                + "\n" + "共计:" + format.format(e.getY()) + "万kWh");

        super.refreshContent(e, highlight);
    }

    @Override
    public MPPointF getOffset() {
        return new MPPointF(-(getWidth() / 2), -getHeight());
    }


    /**
     * 判断显示的电量
     **/

    private Fgsyussdlview getFgsyussdlview(String branch, ArrayList<Fgsyussdlview> fgsyussdlviews) {
        for (Fgsyussdlview fgsyussdlview : fgsyussdlviews) {
            if (branch.equals(fgsyussdlview.TOTAL + "")) {
                return fgsyussdlview;
            }

        }
        return null;
    }
}
