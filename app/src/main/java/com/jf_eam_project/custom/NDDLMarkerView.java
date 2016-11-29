
package com.jf_eam_project.custom;

import android.content.Context;
import android.widget.TextView;

import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;
import com.jf_eam_project.R;
import com.jf_eam_project.model.Fgsnudlview;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Custom implementation of the MarkerView.
 *
 * @author Philipp Jahoda
 */
public class NDDLMarkerView extends MarkerView {

    private TextView tvContent;

    private DecimalFormat format;

    private ArrayList<Fgsnudlview> fgsnudlviews;
    private int mark;


    public NDDLMarkerView(Context context, ArrayList<Fgsnudlview> fgsnudlviews,int mark) {
        super(context, R.layout.custom_marker_view);
        this.fgsnudlviews=fgsnudlviews;
        this.mark=mark;
        tvContent = (TextView) findViewById(R.id.tvContent);
        format = new DecimalFormat("###.0");
    }

    // callbacks everytime the MarkerView is redrawn, can be used to update the
    // content (user-interface)
    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        if(mark==0) {
            tvContent.setText(fgsnudlviews.get((int) highlight.getX()).SWDL + "万kWh");
        }else if(mark==1){
            tvContent.setText(fgsnudlviews.get((int) highlight.getX()).XDL + "万kWh");
        }

        super.refreshContent(e, highlight);
    }

    @Override
    public MPPointF getOffset() {
        return new MPPointF(-(getWidth() / 2), -getHeight());
    }
}
