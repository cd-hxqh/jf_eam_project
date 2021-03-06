package com.jf_eam_project.ui.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

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
    private int position;

    /**
     * 标题*
     */
    private TextView titlename;
    /**
     * 编辑*
     */
    private ImageView editImageView;
    /**
     * 返回*
     */
    private ImageView backImageView;

    private TextView taskid;//任务
    private EditText description;//摘要
    private TextView targstartdate;//目标开始时间
    private TextView targcompdate;//目标结束时间
    private TextView actstart;//实际开始时间
    private TextView actfinish;//实际结束时间
    private EditText estdur;//估计持续时间
    private Button ok;//确定
    private Button delete;//删除

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
        position = getIntent().getIntExtra("position",0);
    }
    @Override
    protected void findViewById() {
        titlename = (TextView) findViewById(R.id.title_name);
        editImageView = (ImageView) findViewById(R.id.title_add);
        backImageView = (ImageView) findViewById(R.id.title_back_id);

        taskid = (TextView) findViewById(R.id.woactivity_taskid);
        description = (EditText) findViewById(R.id.woactivity_description);
        targstartdate = (TextView) findViewById(R.id.woactivity_targstartdate);
        targcompdate = (TextView) findViewById(R.id.woactivity_targcompdate);
        actstart = (TextView) findViewById(R.id.woactivity_actstart);
        actfinish = (TextView) findViewById(R.id.woactivity_actfinish);
        estdur = (EditText) findViewById(R.id.woactivity_estdur);

        ok = (Button) findViewById(R.id.woactivity_ok);
        delete = (Button) findViewById(R.id.woactivity_delete);
    }

    @Override
    protected void initView() {
        titlename.setText(R.string.title_activity_woactivity_details);
        editImageView.setVisibility(View.VISIBLE);
        editImageView.setImageResource(R.drawable.edit_query);
        editImageView.setOnClickListener(editImageViewOnClickListener);
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

        description.setFocusable(false);
        description.setFocusableInTouchMode(false);
        estdur.setFocusable(false);
        estdur.setFocusableInTouchMode(false);
        targstartdate.setEnabled(false);
        targcompdate.setEnabled(false);
        actstart.setEnabled(false);
        actfinish.setEnabled(false);

        setDataListener();
        targstartdate.setOnClickListener(new MydateListener());
        targcompdate.setOnClickListener(new MydateListener());
        actstart.setOnClickListener(new MydateListener());
        actfinish.setOnClickListener(new MydateListener());

        ok.setOnClickListener(okOnClickListener);
        ok.setVisibility(View.GONE);
        delete.setOnClickListener(deleteOnClickListener);
    }

    private View.OnClickListener okOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = getIntent();
            if(woactivity.taskid.equals(taskid.getText().toString())
                    &&woactivity.description.equals(description.getText().toString())
                    &&woactivity.targstartdate.equals(targstartdate.getText().toString())
                    &&woactivity.targcompdate.equals(targcompdate.getText().toString())
                    &&woactivity.actstart.equals(actstart.getText().toString())
                    &&woactivity.actfinish.equals(actfinish.getText().toString())
                    &&woactivity.estdur.equals(estdur.getText().toString())) {//如果内容没有修改
                intent.putExtra("woactivity",woactivity);
            }else {
                woactivity.setTaskid(taskid.getText().toString());
                woactivity.setDescription(description.getText().toString());
                woactivity.setTargstartdate(targstartdate.getText().toString());
                woactivity.setTargcompdate(targcompdate.getText().toString());
                woactivity.setActstart(actstart.getText().toString());
                woactivity.setActfinish(actfinish.getText().toString());
                woactivity.estdur = estdur.getText().toString();
                if(woactivity.type==null||!woactivity.type.equals("add")) {
                    woactivity.type = "update";
                }
                intent.putExtra("woactivity", woactivity);
                Toast.makeText(WoactivityDetailsActivity.this, "任务本地修改成功", Toast.LENGTH_SHORT).show();
            }
            intent.putExtra("position", position);
            WoactivityDetailsActivity.this.setResult(4, intent);
            finish();
        }
    };

    private View.OnClickListener editImageViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ok.setVisibility(View.VISIBLE);
            delete.setVisibility(View.VISIBLE);
            statusEdit();
        }
    };

    /**
     * 设置状态编辑*
     */
    private void statusEdit() {
        description.setFocusable(true);
        description.setFocusableInTouchMode(true);
        estdur.setFocusable(true);
        estdur.setFocusableInTouchMode(true);
        targstartdate.setEnabled(true);
        targcompdate.setEnabled(true);
        actstart.setEnabled(true);
        actfinish.setEnabled(true);
    }

    private View.OnClickListener deleteOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = getIntent();
            if(woactivity.type!=null&&woactivity.type.equals("add")){//本地新增任务
                intent.putExtra("position",position);
                WoactivityDetailsActivity.this.setResult(7, intent);
                Toast.makeText(WoactivityDetailsActivity.this, "任务删除成功", Toast.LENGTH_SHORT).show();
                finish();
            }else if (woactivity.type==null){//服务器接收的任务
                woactivity.type = "delete";
                intent.putExtra("woactivity", woactivity);
                intent.putExtra("position",position);
                WoactivityDetailsActivity.this.setResult(8, intent);
                Toast.makeText(WoactivityDetailsActivity.this, "任务本地删除成功", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    };

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
                sb.append(year + "-" + monthOfYear + "-" + "0" + dayOfMonth);
            } else {
                sb.append(year + "-" + monthOfYear + "-" + dayOfMonth);
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
