package com.jf_eam_project.ui.fragment;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.jf_eam_project.R;
import com.jf_eam_project.api.HttpManager;
import com.jf_eam_project.api.HttpRequestHandler;
import com.jf_eam_project.api.ig.json.Ig_Json_Model;
import com.jf_eam_project.custom.DYMRDLMarkerView;
import com.jf_eam_project.custom.DayAxisValueFormatter;
import com.jf_eam_project.custom.LineXAxisValueFormatter;
import com.jf_eam_project.custom.NDDLMarkerView;
import com.jf_eam_project.custom.XAxisValueFormatter;
import com.jf_eam_project.custom.XYMarkerView;
import com.jf_eam_project.custom.YAxisValueFormatter;
import com.jf_eam_project.model.Fgsnudlview;
import com.jf_eam_project.model.Fgsrudlview;
import com.jf_eam_project.model.Fgsyudlview;
import com.jf_eam_project.utils.DateUtils;
import com.jf_eam_project.utils.MessageUtils;

import java.io.IOException;
import java.util.ArrayList;

/**
 * 限电量**
 */

public class XDL_fragment extends BaseFragment {


    // 分公司年度限电量 柱状图
    protected BarChart barChart;
    //分公司月度限电量 线形图
    protected LineChart lineChart;
    //分公司当月单日限电量 线行图
    protected LineChart dayLineChart;

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
    private ArrayList<Fgsnudlview> fgsnudlviews;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        View settingLayout = inflater.inflate(R.layout.xdl_fragment,
                container, false);


        mTfRegular = Typeface.createFromAsset(getActivity().getAssets(), "OpenSans-Regular.ttf");
        mTfLight = Typeface.createFromAsset(getActivity().getAssets(), "OpenSans-Light.ttf");


        findViewById(settingLayout);

        getFgsnudlview();  //年度
        getFGSYUDLVIEW();  //月度
        getFGSRUDLVIEW();  //当月单日


        return settingLayout;
    }

    protected void findViewById(View view) {
        barChart = (BarChart) view.findViewById(R.id.chart1);
        lineChart = (LineChart) view.findViewById(R.id.chart2);

        dayLineChart = (LineChart) view.findViewById(R.id.chart3);

//        initView();


    }
//    /**设置事件监听**/
//    private void initView() {
//        lineChart.setOnClickListener(lineChartOnClickListener);
//    }


    private View.OnClickListener lineChartOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            MessageUtils.showMiddleToast(getActivity(), "月度限电量");
            getFGSYUDLVIEW();
        }
    };


    /**
     * 获取上网电量的数据
     **/
    private void getFgsnudlview() {
        HttpManager.getData(getActivity(), HttpManager.Fgsnudlview(), new HttpRequestHandler<String>() {

            @Override
            public void onSuccess(String data) {
                if (!data.equals("") && data != null) {
                    fgsnudlviews = new ArrayList<Fgsnudlview>();
                    try {
                        fgsnudlviews = Ig_Json_Model.parsingFgsnudlview(data);
                        if (fgsnudlviews == null || fgsnudlviews.isEmpty()) {
                            MessageUtils.showMiddleToast(getActivity(), "无法获取上网电量数据");
                        } else {
                            setbarChart(fgsnudlviews);
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
     * 年累计上网电量
     **/
    private void setbarChart(ArrayList<Fgsnudlview> fgsnudlviews) {
        barChart.setPinchZoom(false);//
        barChart.setScaleEnabled(false);// 是否可以缩放
        barChart.setDrawBarShadow(false);
        barChart.setDrawValueAboveBar(true);
        barChart.getDescription().setEnabled(false);
        barChart.setDrawGridBackground(false); //设置图表内格子背景是否显示，默认是false
        barChart.animateY(700);
        barChart.fitScreen(); //适应屏幕

//        barChart.setBorderWidth(20);
        barChart.setNoDataText("您需要提供的数据图表");


        XAxisValueFormatter xAxisFormatter = new XAxisValueFormatter(fgsnudlviews);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTypeface(mTfLight);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f);
        xAxis.setLabelCount(6, false);
        xAxis.setValueFormatter(xAxisFormatter);

        IAxisValueFormatter custom = new YAxisValueFormatter();

        YAxis leftAxis = barChart.getAxisLeft();
        leftAxis.setTypeface(mTfLight);
        leftAxis.setLabelCount(5, false);
//        leftAxis.setValueFormatter(custom);
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        barChart.getAxisRight().setEnabled(false);


        Legend l = barChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setForm(Legend.LegendForm.SQUARE);
        l.setFormSize(9f);
        l.setTextSize(11f);
        l.setXEntrySpace(4f);

        setbarChartData(fgsnudlviews);

        barChart.setNoDataText("您需要提供的数据图表");
        NDDLMarkerView mv = new NDDLMarkerView(getActivity(), fgsnudlviews, 1);
        mv.setChartView(barChart); // For bounds control
        barChart.setMarker(mv); // Set the marker to the chart


    }


    /**
     * 设置分公司年度上网电量
     **/
    private void setbarChartData(ArrayList<Fgsnudlview> fgsnudlviews) {


        /**设置Y轴数据**/
        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();

        for (int i = 0; i < fgsnudlviews.size(); i++) {
            float value = fgsnudlviews.get(i).XDL;
            yVals1.add(new BarEntry(i, value));
        }


        BarDataSet set1;

        if (barChart.getData() != null &&
                barChart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) barChart.getData().getDataSetByIndex(0);
            set1.setValues(yVals1);
            barChart.getData().notifyDataChanged();
            barChart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(yVals1, "分公司");
            set1.setColors(ColorTemplate.MATERIAL_COLORS);

            ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
            dataSets.add(set1);

            BarData data = new BarData(dataSets);
            data.setValueTextSize(10f);
            data.setValueTypeface(mTfLight);
            data.setBarWidth(0.9f);

            barChart.setData(data);

        }

    }


    /**
     * 分公司月度上网电量
     **/
    private void getFGSYUDLVIEW() {
        HttpManager.getData(getActivity(), HttpManager.Fgsyudlview(), new HttpRequestHandler<String>() {

            @Override
            public void onSuccess(String data) {
                if (!data.equals("") || data != null) {
                    ArrayList<Fgsyudlview> item = null;
                    try {
                        item = Ig_Json_Model.parsingFgsyudlview(data);
                        if (item == null || item.isEmpty()) {
                            MessageUtils.showMiddleToast(getActivity(), "无法获取月度上网电量数据");
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
     * 月度上网电量
     **/
    private void setlineChart(ArrayList<Fgsyudlview> fgsyudlviews) {

        //Y轴左边
        YAxis leftAxis = lineChart.getAxisLeft();
        leftAxis.setTypeface(mTfLight);
        leftAxis.setLabelCount(5, false);
        leftAxis.setAxisMinimum(0f);


        lineChart.getDescription().setEnabled(false);

        //X轴下边

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTypeface(mTfLight);
        xAxis.setDrawGridLines(false);  //是否显示X坐标轴上的刻度竖线，默认是true
        xAxis.setDrawAxisLine(true);
        xAxis.setLabelCount(13, false);//第一个参数是X轴坐标的个数，第二个参数是 是否不均匀分布，true是不均匀分布
        xAxis.setGranularity(1f); //

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
        //lineChart设置
        lineChart.getAxisRight().setEnabled(false);  //右边默认不显示
        lineChart.setData(generateDataLine(fgsyudlviews)); //设置数据
        lineChart.setNoDataText("没有可用的图表数据");

        lineChart.animateX(750); //设置动画


        lineChart.setScaleEnabled(true);  // 是否可以缩放 x和y轴, 默认是true
        lineChart.setPinchZoom(false);// //设置x轴和y轴能否同时缩放。默认是否

        //设置XYMarkerView弹出框
        LineXAxisValueFormatter lineXAxisValueFormatter = new LineXAxisValueFormatter(fgsyudlviews);
        XYMarkerView mv = new XYMarkerView(getActivity(), lineXAxisValueFormatter);
        mv.setChartView(lineChart); // For bounds control
        lineChart.setMarker(mv); // Set the marker to the chart


    }


    /**
     * 设置月度上网电量
     **/
    private LineData generateDataLine(ArrayList<Fgsyudlview> fgsyudlviews) {
        ArrayList<ILineDataSet> sets = new ArrayList<ILineDataSet>();
        for (int i = 0; i < fgsnudlviews.size(); i++) {

            // y轴的数据
            ArrayList<Entry> yValues = new ArrayList<Entry>();
            ArrayList<Fgsyudlview> item = getFgsyudlview(fgsnudlviews.get(i).BRANCH, fgsyudlviews);
            for (int j = 0; j < item.size(); j++) {
                float value = (float) item.get(j).XDL;

                yValues.add(new Entry(j, value));
            }

            LineDataSet d = new LineDataSet(yValues, item.get(0).FGSDES);
            d.setLineWidth(3.0f);
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
     * 获取每个分公司的1-12月份的上网电量
     **/

    private ArrayList<Fgsyudlview> getFgsyudlview(String branch, ArrayList<Fgsyudlview> fgsyudlviews) {

        ArrayList<Fgsyudlview> fgsyudlviews1 = new ArrayList<Fgsyudlview>();
        for (Fgsyudlview fgsyudlview : fgsyudlviews) {
            if (branch.equals(fgsyudlview.BRANCH)) {
                fgsyudlviews1.add(fgsyudlview);
            }
        }


        return fgsyudlviews1;
    }


    /**
     * 分公司当月单日电量
     **/
    private void getFGSRUDLVIEW() {
        HttpManager.getData(getActivity(), HttpManager.Fgsrudlview(), new HttpRequestHandler<String>() {

            @Override
            public void onSuccess(String data) {
                if (!data.equals("") || data != null) {
                    ArrayList<Fgsrudlview> item = null;
                    try {
                        item = Ig_Json_Model.parsingFgsrudlview(data);
                        if (item == null || item.isEmpty()) {
                            MessageUtils.showMiddleToast(getActivity(), "无法获取当月单日电量");
                        } else {
                            setdayLineChart(item);

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
     * 当月单日上网电量
     **/
    private void setdayLineChart(ArrayList<Fgsrudlview> fgsrudlviews) {

        dayLineChart.getDescription().setEnabled(false);
        dayLineChart.setTouchEnabled(true); // 设置是否可以触摸
//        dayLineChart.setDragEnabled(true);// 是否可以拖拽
        dayLineChart.setPinchZoom(false);//
        dayLineChart.setScaleEnabled(true);// 是否可以缩放
//        dayLineChart.setScaleX(1.0f);


        /**设置X轴坐标**/
        DayAxisValueFormatter dayAxisValueFormatter = new DayAxisValueFormatter(dayLineChart);
        XAxis xAxis = dayLineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTypeface(mTfLight);
        xAxis.setLabelCount(DateUtils.getDays() + 1, false);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(true);
        xAxis.setGranularity(1f); // only intervals of 1 day
        xAxis.setValueFormatter(dayAxisValueFormatter);
        xAxis.setLabelRotationAngle(1.0f);


        /**设置Y轴坐标**/
        YAxis leftAxis = dayLineChart.getAxisLeft();
        leftAxis.setTypeface(mTfLight);
        leftAxis.setLabelCount(5, false);
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)


        YAxis rightAxis = dayLineChart.getAxisRight();
        rightAxis.setEnabled(false);
        // set data
        dayLineChart.setData(generateDataLine1(fgsrudlviews));

        // do not forget to refresh the chart
        // holder.chart.invalidate();
        dayLineChart.animateX(750);
        dayLineChart.setVisibleXRangeMaximum(8);

        DYMRDLMarkerView mv = new DYMRDLMarkerView(getActivity(), fgsrudlviews);
        mv.setChartView(dayLineChart); // For bounds control
        dayLineChart.setMarker(mv); // Set the marker to the chart

    }


    /**
     * 设置当月累计上网电量
     **/
    private LineData generateDataLine1(ArrayList<Fgsrudlview> fgsrudlviews) {
        ArrayList<ILineDataSet> sets = new ArrayList<ILineDataSet>();
        for (int i = 0; i < fgsnudlviews.size(); i++) {
            // y轴的数据
            ArrayList<Entry> yValues = new ArrayList<Entry>();
            ArrayList<Fgsrudlview> item = getFgsrudlview(fgsnudlviews.get(i).BRANCH, fgsrudlviews);
            for (int j = 0; j < item.size(); j++) {
                float value = (float) item.get(j).XDL;

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
     * 获取每个分公司当月单日上网电量
     **/

    private ArrayList<Fgsrudlview> getFgsrudlview(String branch, ArrayList<Fgsrudlview> fgsrudlviews) {

        ArrayList<Fgsrudlview> fgsrudlviews1 = new ArrayList<Fgsrudlview>();
        for (Fgsrudlview fgsrudlview : fgsrudlviews) {
            if (branch.equals(fgsrudlview.BRANCH)) {
                fgsrudlviews1.add(fgsrudlview);
            }
        }


        return fgsrudlviews1;
    }


}
