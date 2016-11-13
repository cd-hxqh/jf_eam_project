package com.jf_eam_project.ui.fragment;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.jf_eam_project.R;
import com.jf_eam_project.api.HttpManager;
import com.jf_eam_project.api.HttpRequestHandler;
import com.jf_eam_project.api.ig.json.Ig_Json_Model;
import com.jf_eam_project.custom.MonthAxisValueFormatter;
import com.jf_eam_project.custom.PieXYMarkerView;
import com.jf_eam_project.custom.YDSSDLXYMarkerView;
import com.jf_eam_project.model.Fgsnussdlview;
import com.jf_eam_project.model.Fgsyussdlview;
import com.jf_eam_project.utils.MessageUtils;

import java.io.IOException;
import java.util.ArrayList;

/**
 * 损失电量**
 */

public class SSDL_fragment extends BaseFragment {
    // 分公司年度上网电量 饼状图
    protected PieChart piechart;
    //分公司月度上网电量 线形图
    protected LineChart lineChart;

    protected Typeface mTfRegular;
    protected Typeface mTfLight;


    /**
     * 月份
     **/
    private String[] months = null;

    protected String[] mMonths = new String[]{
            "1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月"
    };


    /**
     * 分公司年度电量统计
     **/
    private ArrayList<Fgsnussdlview> fgsnussdlviews;

    private View view;


    protected String[] mParties = new String[]{
            "华北分公司", "西北分公司", "华东分公司", "东北分公司", "南方分公司"};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        view = inflater.inflate(R.layout.ssdl_fragment,
                container, false);

        mTfRegular = Typeface.createFromAsset(getActivity().getAssets(), "OpenSans-Regular.ttf");
        mTfLight = Typeface.createFromAsset(getActivity().getAssets(), "OpenSans-Light.ttf");

        findViewById(view);
        initView();



        return view;
    }


    protected void findViewById(View view) {
        piechart = (PieChart) view.findViewById(R.id.chart1);
        lineChart = (LineChart) view.findViewById(R.id.chart2);
    }

    private void initView() {
        getFgsnussdlview(); //年度损失电量
        getFGSYUSSDLVIEW(); //月度损失电量

    }

    private void setPieChart() {

        piechart.setUsePercentValues(true);
        piechart.getDescription().setEnabled(false);
        piechart.setExtraOffsets(5, 10, 5, 5);

        piechart.setDragDecelerationFrictionCoef(0.95f);

        piechart.setCenterTextTypeface(mTfLight);
        piechart.setCenterText(generateCenterSpannableText());

        piechart.setDrawHoleEnabled(true);
        piechart.setHoleColor(Color.WHITE);

        piechart.setTransparentCircleColor(Color.WHITE);
        piechart.setTransparentCircleAlpha(110);

        piechart.setHoleRadius(58f);
        piechart.setTransparentCircleRadius(61f);

        piechart.setDrawCenterText(true);

        piechart.setRotationAngle(0);
        // enable rotation of the chart by touch
        piechart.setRotationEnabled(true);
        piechart.setHighlightPerTapEnabled(true);

        // piechart.setUnit(" €");
        // piechart.setDrawUnitsInChart(true);

        // add a selection listener
        piechart.setOnChartValueSelectedListener(piechartOnChartValueSelectedListener);


        piechart.animateY(1400, Easing.EasingOption.EaseInOutQuad);

        piechart.setNoDataText("您需要提供的数据图表");
        setPieChartData(fgsnussdlviews);

        PieXYMarkerView mv = new PieXYMarkerView(getActivity(), fgsnussdlviews);
        mv.setChartView(piechart); // For bounds control
        piechart.setMarker(mv); // Set the marker to the chart
    }


    private OnChartValueSelectedListener piechartOnChartValueSelectedListener = new OnChartValueSelectedListener() {
        @Override
        public void onValueSelected(Entry e, Highlight h) {

            if (e == null)
                return;
        }

        @Override
        public void onNothingSelected() {
            Log.i("PieChart", "nothing selected");
        }
    };


    private SpannableString generateCenterSpannableText() {

        SpannableString s = new SpannableString("年度损失电量");
        s.setSpan(new RelativeSizeSpan(1.7f), 0, 6, 0);
        return s;
    }


    /**
     * 设置饼状图的数据
     **/
    private void setPieChartData(ArrayList<Fgsnussdlview> fgsnussdlviews) {

        float mult = 100;

        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();

        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.
        for (int i = 0; i < fgsnussdlviews.size(); i++) {
            entries.add(new PieEntry((float) fgsnussdlviews.get(i).TOTAL, fgsnussdlviews.get(i % mParties.length).FGSDES));
        }

        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);

        // add a lot of colors

        ArrayList<Integer> colors = new ArrayList<Integer>();

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());

        dataSet.setColors(colors);
        //dataSet.setSelectionShift(0f);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);
        data.setValueTypeface(mTfLight);
        piechart.setData(data);

        // undo all highlights
        piechart.highlightValues(null);

        piechart.invalidate();
    }


    /**
     * 获取损失的数据
     **/
    private void getFgsnussdlview() {
        HttpManager.getData(getActivity(), HttpManager.Fgsnussdlview(), new HttpRequestHandler<String>() {

            @Override
            public void onSuccess(String data) {
                if (!data.equals("") && data != null) {
                    fgsnussdlviews = new ArrayList<Fgsnussdlview>();
                    try {
                        fgsnussdlviews = Ig_Json_Model.parsingFgsnussdlview(data);
                        if (fgsnussdlviews == null || fgsnussdlviews.isEmpty()) {
                            MessageUtils.showMiddleToast(getActivity(), "无法获取损失电量数据");
                        } else {
                            setPieChart();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onSuccess(String data, int totalPages, int currentPage) {
            }

            @Override
            public void onFailure(String error) {

            }
        });

    }


    /**
     * 分公司月度上网电量
     **/
    private void getFGSYUSSDLVIEW() {
        HttpManager.getData(getActivity(), HttpManager.Fgsyussdlview(), new HttpRequestHandler<String>() {

            @Override
            public void onSuccess(String data) {
                if (!data.equals("") || data != null) {
                    ArrayList<Fgsyussdlview> item = null;
                    try {
                        item = Ig_Json_Model.parsingFgsyussdlview(data);
                        if (item == null || item.isEmpty()) {
                            MessageUtils.showMiddleToast(getActivity(), "无法获取月度损失数据");
                        } else {
                            setlineChart(item);

                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onSuccess(String data, int totalPages, int currentPage) {
            }

            @Override
            public void onFailure(String error) {

            }
        });

    }


    /**
     * 月度损失电量
     **/
    private void setlineChart(ArrayList<Fgsyussdlview> fgsyussdlviews) {

        //Y轴左边
        YAxis leftAxis = lineChart.getAxisLeft();
        leftAxis.setTypeface(mTfLight);
        leftAxis.setLabelCount(5, false);
        leftAxis.setAxisMinimum(0f);


        lineChart.getDescription().setEnabled(false);

        //X轴下边
        MonthAxisValueFormatter monthAxisValueFormatter = new MonthAxisValueFormatter();

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTypeface(mTfLight);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(true);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return mMonths[(int) value % mMonths.length];
            }

            @Override
            public int getDecimalDigits() {
                return 0;
            }
        });


        lineChart.getAxisRight().setEnabled(false);
        // set data
        lineChart.setData(generateDataLine(fgsyussdlviews));

        lineChart.animateX(750);


        lineChart.setScaleEnabled(false);
        lineChart.setPinchZoom(false);//
        lineChart.moveViewToX(2);

        YDSSDLXYMarkerView mv = new YDSSDLXYMarkerView(getActivity(), fgsyussdlviews);
        mv.setChartView(lineChart); // For bounds control
        lineChart.setMarker(mv); // Set the marker to the chart


    }


    /**
     * 设置月度损失电量
     **/
    private LineData generateDataLine(ArrayList<Fgsyussdlview> fgsyussdflviews) {
        ArrayList<ILineDataSet> sets = new ArrayList<ILineDataSet>();
        for (int i = 0; i < fgsnussdlviews.size(); i++) {
            // y轴的数据
            ArrayList<Entry> yValues = new ArrayList<Entry>();
            ArrayList<Fgsyussdlview> item = getFgsyussdlview(fgsnussdlviews.get(i).BRANCH, fgsyussdflviews);
            for (int j = 0; j < item.size(); j++) {
                float value = (float) item.get(j).TOTAL;

                yValues.add(new Entry(j, value));
            }



            LineDataSet d = new LineDataSet(yValues, item.get(0).FGSDES);
            d.setLineWidth(4.0f);
            d.setCircleRadius(4.5f);
            d.setHighLightColor(ColorTemplate.VORDIPLOM_COLORS[i]);
            d.setColor(ColorTemplate.VORDIPLOM_COLORS[i]);
            d.setCircleColor(ColorTemplate.VORDIPLOM_COLORS[i]);
            d.setDrawValues(false);
            sets.add(d);
        }
        LineData cd = new LineData(sets);
        return cd;
    }


    /**
     * 获取每个分公司的1-12月份的损失电量
     **/

    private ArrayList<Fgsyussdlview> getFgsyussdlview(String branch, ArrayList<Fgsyussdlview> fgsyussdlviews) {

        ArrayList<Fgsyussdlview> fgsyussdlview1 = new ArrayList<Fgsyussdlview>();
        for (Fgsyussdlview fgsyussdlview : fgsyussdlviews) {
            if (branch.equals(fgsyussdlview.BRANCH)) {
                fgsyussdlview1.add(fgsyussdlview);
            }
        }


        return fgsyussdlview1;
    }


}
