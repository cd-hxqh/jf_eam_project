
package com.jf_eam_project.custom;

import android.content.Context;
import android.widget.TextView;

import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;
import com.jf_eam_project.R;
import com.jf_eam_project.model.Fgsrudlview;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Custom implementation of the MarkerView.
 *
 * @author Philipp Jahoda
 */
public class DYMRDLMarkerView extends MarkerView {

    private TextView tvContent;

    private DecimalFormat format;

    private ArrayList<Fgsrudlview> fgsrudlviews;

    public DYMRDLMarkerView(Context context, ArrayList<Fgsrudlview> fgsrudlviews) {
        super(context, R.layout.custom_marker_view);
        this.fgsrudlviews=fgsrudlviews;
        tvContent = (TextView) findViewById(R.id.tvContent);
        format = new DecimalFormat("###.0");
    }

    // callbacks everytime the MarkerView is redrawn, can be used to update the
    // content (user-interface)
    @Override
    public void refreshContent(Entry e, Highlight highlight) {

        tvContent.setText(fgsrudlviews.get((int)highlight.getX()).SWDL);

        super.refreshContent(e, highlight);
    }

    @Override
    public MPPointF getOffset() {
        return new MPPointF(-(getWidth() / 2), -getHeight());
    }
}
