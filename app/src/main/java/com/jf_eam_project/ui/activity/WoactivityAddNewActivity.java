package com.jf_eam_project.ui.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
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
public class WoactivityAddNewActivity extends BaseActivity{
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
    private RelativeLayout targstartdatelayout;
    private TextView targcompdate;
    private RelativeLayout targcompdatelayout;
    private TextView actstart;
    private RelativeLayout actstartlayout;
    private TextView actfinish;
    private RelativeLayout actfinishlayout;
    private TextView estdur;//估计持续时间
    private RelativeLayout estdurlayout;
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
        ;
    }
    @Override
    protected void findViewById() {
        titlename = (TextView) findViewById(R.id.title_name);
        backImageView = (ImageView) findViewById(R.id.title_back_id);

        taskid = (TextView) findViewById(R.id.woactivity_taskid);
        description = (TextView) findViewById(R.id.woactivity_description);
        targstartdate = (TextView) findViewById(R.id.woactivity_targstartdate);
        targstartdatelayout = (RelativeLayout) findViewById(R.id.woactivity_targstartdate_layout);
        targcompdate = (TextView) findViewById(R.id.woactivity_targcompdate);
        targcompdatelayout = (RelativeLayout) findViewById(R.id.woactivity_targcompdate_layout);
        actstart = (TextView) findViewById(R.id.woactivity_actstart);
        actstartlayout = (RelativeLayout) findViewById(R.id.woactivity_actstart_layout);
        actfinish = (TextView) findViewById(R.id.woactivity_actfinish);
        actfinishlayout = (RelativeLayout) findViewById(R.id.woactivity_actfinish_layout);
        estdur = (TextView) findViewById(R.id.woactivity_estdur);
        estdurlayout = (RelativeLayout) findViewById(R.id.woactivity_estdur_layout);
        ok = (Button) findViewById(R.id.woactivity_ok);

    }

    @Override
    protected void initView() {
        titlename.setText(R.string.title_activity_woactivity_addnew);
        backImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        taskid.setText(getIntent().getIntExtra("taskid", 0) + "");

        setDataListener();
        targstartdatelayout.setOnClickListener(new MydateListener());
        targcompdatelayout.setOnClickListener(new MydateListener());
        actstartlayout.setOnClickListener(new MydateListener());
        actfinishlayout.setOnClickListener(new MydateListener());

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                woactivity.taskid = "10";
                woactivity.description = description.getText().toString();
                woactivity.targstartdate = targstartdate.getText().toString();
                woactivity.targcompdate = targcompdate.getText().toString();
                woactivity.actstart = actstart.getText().toString();
                woactivity.actfinish = actfinish.getText().toString();
//                woactivity.estdur = estdur.getText().toString();
                intent.putExtra("woactivity",woactivity);
                WoactivityAddNewActivity.this.setResult(0,intent);
                finish();
            }
        });
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
            if (layoutnum == targstartdatelayout.getId()) {
                targstartdate.setText(sb);
            } else if (layoutnum == targcompdatelayout.getId()) {
                targcompdate.setText(sb);
            } else if (layoutnum == actstartlayout.getId()) {
                actstart.setText(sb);
            } else if(layoutnum == actfinishlayout.getId()){
                actfinish.setText(sb);
            }
        }
    }
}
