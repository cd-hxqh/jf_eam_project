package com.jf_eam_project.ui.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.jf_eam_project.R;
import com.jf_eam_project.model.Woactivity;
import com.jf_eam_project.ui.widget.CumTimePickerDialog;

import java.util.Calendar;

/**
 * Created by think on 2015/12/7.
 * 计划任务详情页面
 */
public class WoactivityDetailsActivity extends BaseActivity{
    private Woactivity woactivity;

    /**
     * 标题*
     */
    private TextView titlename;
    /**
     * 返回*
     */
    private ImageView backImageView;

    private TextView taskid;//任务
    private TextView description;//摘要
    private TextView targstartdate;
    private TextView targcompdate;
    private TextView actstart;
    private TextView actfinish;
    private TextView estdur;//估计持续时间
    private Button ok;//确定

    private DatePickerDialog datePickerDialog;
    private CumTimePickerDialog timePickerDialog;
    StringBuffer sb;
    private int layoutnum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_woactivity_details);

        geiIntentData();
        findViewById();
        initView();
    }

    /**
     * 获取数据*
     */
    private void geiIntentData() {
        woactivity = (Woactivity) getIntent().getSerializableExtra("woactivity");
    }
    @Override
    protected void findViewById() {
        titlename = (TextView) findViewById(R.id.title_name);
        backImageView = (ImageView) findViewById(R.id.title_back_id);

        taskid = (TextView) findViewById(R.id.woactivity_taskid);
        description = (TextView) findViewById(R.id.woactivity_description);
        targstartdate = (TextView) findViewById(R.id.woactivity_targstartdate);
        targcompdate = (TextView) findViewById(R.id.woactivity_targcompdate);
        actstart = (TextView) findViewById(R.id.woactivity_actstart);
        actfinish = (TextView) findViewById(R.id.woactivity_actfinish);
        estdur = (TextView) findViewById(R.id.woactivity_estdur);

        ok = (Button) findViewById(R.id.woactivity_ok);
    }

    @Override
    protected void initView() {
        titlename.setText(R.string.title_activity_woactivity_details);
        backImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        taskid.setText(woactivity.taskid);
        description.setText(woactivity.description);
        targstartdate.setText(woactivity.targstartdate);
        targcompdate.setText(woactivity.targcompdate);
        actstart.setText(woactivity.actstart);
        actfinish.setText(woactivity.actfinish);
        estdur.setText(woactivity.estdur);

        setDataListener();
        targstartdate.setOnClickListener(new MydateListener());
        targcompdate.setOnClickListener(new MydateListener());
        actstart.setOnClickListener(new MydateListener());
        actfinish.setOnClickListener(new MydateListener());
    }

    /**
     * 设置时间选择器*
     */
    private void setDataListener() {

        final Calendar objTime = Calendar.getInstance();
        int iYear = objTime.get(Calendar.YEAR);
        int iMonth = objTime.get(Calendar.MONTH);
        int iDay = objTime.get(Calendar.DAY_OF_MONTH);
        int hour = objTime.get(Calendar.HOUR_OF_DAY);

        int minute = objTime.get(Calendar.MINUTE);


        datePickerDialog = new DatePickerDialog(this, new datelistener(), iYear, iMonth, iDay);
        timePickerDialog = new CumTimePickerDialog(this, new timelistener(), hour, minute, true);
    }

    private class MydateListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            layoutnum = 0;
            sb = new StringBuffer();
            layoutnum = view.getId();
            datePickerDialog.show();
        }
    }

    private class datelistener implements DatePickerDialog.OnDateSetListener {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            sb = new StringBuffer();
            monthOfYear = monthOfYear + 1;
            if (dayOfMonth < 10) {
                sb.append(year%100 + "-" + monthOfYear + "-" + "0" + dayOfMonth);
            } else {
                sb.append(year%100 + "-" + monthOfYear + "-" + dayOfMonth);
            }
            timePickerDialog.show();
        }
    }

    private class timelistener implements TimePickerDialog.OnTimeSetListener {
        @Override
        public void onTimeSet(TimePicker timePicker, int i, int i1) {
            sb.append(" ");
            if (i1 < 10) {
                sb.append(i + ":" + "0" + i1 + ":00");
            } else {
                sb.append(i + ":" + i1 + ":00");
            }
            if (layoutnum == targstartdate.getId()) {
                targstartdate.setText(sb);
            } else if (layoutnum == targcompdate.getId()) {
                targcompdate.setText(sb);
            } else if (layoutnum == actstart.getId()) {
                actstart.setText(sb);
            } else if(layoutnum == actfinish.getId()){
                actfinish.setText(sb);
            }
        }
    }
}
