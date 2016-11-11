package com.jf_eam_project.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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
import com.jf_eam_project.custom.DayAxisValueFormatter;
import com.jf_eam_project.custom.MonthAxisValueFormatter;
import com.jf_eam_project.custom.XAxisValueFormatter;
import com.jf_eam_project.custom.XYMarkerView;
import com.jf_eam_project.custom.YAxisValueFormatter;
import com.jf_eam_project.model.Fgsnudlview;
import com.jf_eam_project.model.Fgsyudlview;
import com.jf_eam_project.utils.DateUtils;
import com.jf_eam_project.utils.MessageUtils;

import java.io.IOException;
import java.util.ArrayList;

public class Statistical_Activity extends BaseActivity {

    private static final String TAG = "Statistical_Activity";

    /**
     * 标题*
     */
    private TextView titleView;
    /**
     * 返回按钮*
     */
    private ImageView backImageView;

    /**
     * 主菜单
     **/
    private Button mainBtn;

    // 分公司年度上网电量 柱状图
    protected BarChart barChart;
    //分公司月度上网电量 线形图
    protected LineChart lineChart;
    //本年线行图
    protected LineChart mChart3;

    protected Typeface mTfRegular;
    protected Typeface mTfLight;


    /**月份**/
    private String[] months=null;

    protected String[] mMonths = new String[] {
            "1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_statistical);

        mTfRegular = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");
        mTfLight = Typeface.createFromAsset(getAssets(), "OpenSans-Light.ttf");


        findViewById();
        initView();

        getFgsnudlview();  //年度
        getFGSYUDLVIEW();  //月度
//        setlineChart();
//        setmChart3();


    }

    @Override
    protected void findViewById() {
        titleView = (TextView) findViewById(R.id.title_name);
        backImageView = (ImageView) findViewById(R.id.title_back_id);
        mainBtn = (Button) findViewById(R.id.main_btn_id);

        barChart = (BarChart) findViewById(R.id.chart1);
        lineChart = (LineChart) findViewById(R.id.chart2);
        mChart3 = (LineChart) findViewById(R.id.chart3);
    }

    @Override
    protected void initView() {
        backImageView.setVisibility(View.GONE);
        titleView.setText(R.string.dltj_text);
        mainBtn.setVisibility(View.VISIBLE);
        mainBtn.setOnClickListener(mainBtnOnClickListener);
    }


    private View.OnClickListener mainBtnOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent inetnt = new Intent();
            inetnt.setClass(Statistical_Activity.this, MainActivity.class);
            startActivity(inetnt);
        }
    };

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

        barChart.setBorderWidth(20);


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
        XYMarkerView mv = new XYMarkerView(this, xAxisFormatter);
        mv.setChartView(barChart); // For bounds control
        barChart.setMarker(mv); // Set the marker to the chart


    }

    /**
     * 月度上网电量
     **/
    private void setlineChart(ArrayList<Fgsyudlview> fgsyudlviews) {

        //Y轴左边
        YAxis leftAxis = lineChart.getAxisLeft();
        leftAxis.setTypeface(mTfLight);
        leftAxis.setLabelCount(5, false);
        leftAxis.setGranularity(1f);



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
        lineChart.setData(generateDataLine(fgsyudlviews));

        lineChart.animateX(750);


        lineChart.setScaleEnabled(false);
        lineChart.setPinchZoom(false);//
        lineChart.moveViewToX(2);



    }


    /**
     * 当月累计上网电量
     **/
    private void setmChart3() {

        mChart3.getDescription().setEnabled(false);
        mChart3.setTouchEnabled(true); // 设置是否可以触摸
//        mChart3.setDragEnabled(true);// 是否可以拖拽
        mChart3.setPinchZoom(false);//
        mChart3.setScaleEnabled(false);// 是否可以缩放
//        mChart3.setScaleX(1.0f);


        /**设置X轴坐标**/
        DayAxisValueFormatter dayAxisValueFormatter = new DayAxisValueFormatter(mChart3);
        XAxis xAxis = mChart3.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTypeface(mTfLight);
        xAxis.setLabelCount(DateUtils.getDays() + 1, false);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(true);
        xAxis.setGranularity(1f); // only intervals of 1 day
        xAxis.setValueFormatter(dayAxisValueFormatter);
        xAxis.setLabelRotationAngle(1.0f);


        /**设置Y轴坐标**/
        YAxis leftAxis = mChart3.getAxisLeft();
        leftAxis.setTypeface(mTfLight);
        leftAxis.setLabelCount(5, false);
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)


        YAxis rightAxis = mChart3.getAxisRight();
        rightAxis.setEnabled(false);
        // set data
        mChart3.setData(generateDataLine1());

        // do not forget to refresh the chart
        // holder.chart.invalidate();
        mChart3.animateX(750);
        mChart3.setVisibleXRangeMaximum(8);

    }

    /**
     * 设置月度上网电量
     **/
    private LineData generateDataLine(ArrayList<Fgsyudlview> fgsyudlviews) {


        // 华北分公司
        ArrayList<Entry> yValues = new ArrayList<Entry>();
        ArrayList<Fgsyudlview> item1 = getFgsyudlview("01001", fgsyudlviews);
        for (int i = 0; i < item1.size(); i++) {
            float value = (float) item1.get(i).SWDL;
            yValues.add(new Entry(i, value));
        }


        LineDataSet d1 = new LineDataSet(yValues, item1.get(0).FGSDES);
        d1.setLineWidth(2.5f);
        d1.setCircleRadius(4.5f);
        d1.setHighLightColor(R.color.holo_blue_bright);
        d1.setDrawValues(false);


        // 西北风公司
        ArrayList<Entry> yValues1 = new ArrayList<Entry>();
        ArrayList<Fgsyudlview> item2 = getFgsyudlview("01002", fgsyudlviews);
        for (int i = 0; i < item1.size(); i++) {
            float value = (float) item2.get(i).SWDL;
            yValues1.add(new Entry(i, value));
        }


        LineDataSet d2 = new LineDataSet(yValues1, item2.get(0).FGSDES);
        d2.setLineWidth(2.5f);
        d2.setCircleRadius(4.5f);
        d2.setHighLightColor(R.color.holo_green_light);
        d2.setColor(R.color.holo_green_light);
        d2.setCircleColor(R.color.holo_green_light);
        d2.setDrawValues(false);


        ArrayList<ILineDataSet> sets = new ArrayList<ILineDataSet>();
        sets.add(d1);
        sets.add(d2);

        LineData cd = new LineData(sets);
        return cd;
    }


    /**
     * 设置当月累计上网电量
     **/
    private LineData generateDataLine1() {


        // y轴的数据
        ArrayList<Entry> yValues = new ArrayList<Entry>();
        for (int i = 0; i < DateUtils.getDays() + 1; i++) {
            float value = (float) (Math.random() * 65) + 3;
            yValues.add(new Entry(i, value));
        }


        LineDataSet d1 = new LineDataSet(yValues, "上网电量");
        d1.setLineWidth(2.5f);
        d1.setCircleRadius(4.5f);
        d1.setHighLightColor(Color.rgb(244, 117, 117));
        d1.setDrawValues(false);


        ArrayList<ILineDataSet> sets = new ArrayList<ILineDataSet>();
        sets.add(d1);

        LineData cd = new LineData(sets);
        return cd;
    }

    /**
     * 设置分公司年度上网电量
     **/
    private void setbarChartData(ArrayList<Fgsnudlview> fgsnudlviews) {

        /**设置Y轴数据**/
        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();

        for (int i = 0; i < fgsnudlviews.size(); i++) {
            float value = fgsnudlviews.get(i).SWDL;

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


            set1 = new BarDataSet(yVals1, "电量/万kwh");
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
     * 获取上网电量的数据
     **/
    private void getFgsnudlview() {
        HttpManager.getData(this, HttpManager.Fgsnudlview(), new HttpRequestHandler<String>() {

            @Override
            public void onSuccess(String data) {

                ArrayList<Fgsnudlview> item = null;
                try {
                    item = Ig_Json_Model.parsingFgsnudlview(data);
                    if (item == null || item.isEmpty()) {
                        MessageUtils.showMiddleToast(Statistical_Activity.this, "无法获取上网电量数据");
                    } else {
                        setbarChart(item);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
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
    private void getFGSYUDLVIEW() {
        HttpManager.getData(this, HttpManager.Fgsyudlview(), new HttpRequestHandler<String>() {

            @Override
            public void onSuccess(String data) {
                Log.i(TAG, "111111111" + data);

                ArrayList<Fgsyudlview> item = null;
                try {
                    item = Ig_Json_Model.parsingFgsyudlview(data);
                    if (item == null || item.isEmpty()) {
                        MessageUtils.showMiddleToast(Statistical_Activity.this, "无法获取月度上网电量数据");
                    } else {
                        setlineChart(item);

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onSuccess(String data, int totalPages, int currentPage) {
                Log.i(TAG, "2222222222" + data);
            }

            @Override
            public void onFailure(String error) {
                Log.i(TAG, "3333333333");

            }
        });

    }


    /**
     * 获取每个分公司的1-12月份的上网电量
     **/

    private ArrayList<Fgsyudlview> getFgsyudlview(String branch, ArrayList<Fgsyudlview> fgsyudlviews) {

        ArrayList<Fgsyudlview> fgsyudlviews1 = new ArrayList<Fgsyudlview>();
//        Fgsyudlview f=new Fgsyudlview();
//        f.setBRANCH(branch);
//        f.setSWDL(0);
//        fgsyudlviews1.add(f);
        for (Fgsyudlview fgsyudlview : fgsyudlviews) {
            if (branch.equals(fgsyudlview.BRANCH)) {
                fgsyudlviews1.add(fgsyudlview);
            }
        }


        return fgsyudlviews1;
    }


}
