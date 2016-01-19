package com.jf_eam_project.ui.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.flyco.animation.BaseAnimatorSet;
import com.flyco.animation.BounceEnter.BounceTopEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.flyco.dialog.entity.DialogMenuItem;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.NormalListDialog;
import com.jf_eam_project.R;
import com.jf_eam_project.config.Constants;
import com.jf_eam_project.model.Labtrans;
import com.jf_eam_project.model.Option;
import com.jf_eam_project.model.Woactivity;
import com.jf_eam_project.model.Wplabor;
import com.jf_eam_project.ui.widget.CumTimePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by think on 2015/12/22.
 * 实际员工详情页面
 */
public class LabtransDetailsActivity extends BaseActivity{
    private Labtrans labtrans;
    private int position;

    /**
     * 标题*
     */
    private TextView titlename;
    /**
     * 返回*
     */
    private ImageView backImageView;
    //员工，开始日期，常规时数，工种，费率，类型
//    private TextView taskid;//任务
    private TextView laborcode;//员工
    private TextView startdate;//开始日期
    private TextView regularhrs;//常规时数
    private TextView craft;//工种
//    private TextView payrate;//费率
    private Button ok;//确定

//    private ArrayList<Woactivity> woactivityList = new ArrayList<>();
//    private ArrayList<DialogMenuItem> mMenuItems = new ArrayList<>();
//    private BaseAnimatorSet mBasIn;
//    private BaseAnimatorSet mBasOut;

    private DatePickerDialog datePickerDialog;
    StringBuffer sb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_labtrans_details);

        geiIntentData();
        findViewById();
        initView();
    }

    /**
     * 获取数据*
     */
    private void geiIntentData() {
//        woactivityList = (ArrayList<Woactivity>) getIntent().getSerializableExtra("woactivityList");
        labtrans = (Labtrans) getIntent().getSerializableExtra("labtrans");
        position = getIntent().getIntExtra("position", 0);
    }
    @Override
    protected void findViewById() {
        titlename = (TextView) findViewById(R.id.title_name);
        backImageView = (ImageView) findViewById(R.id.title_back_id);

//        taskid = (TextView) findViewById(R.id.labtrans_taskid);
        laborcode = (TextView) findViewById(R.id.labtrans_laborcode);
        craft = (TextView) findViewById(R.id.labtrans_craft);
//        payrate = (TextView) findViewById(R.id.labtrans_payrate);
        startdate = (TextView) findViewById(R.id.labtrans_startdate);
        regularhrs = (EditText) findViewById(R.id.labtrans_regularhrs);
        ok = (Button) findViewById(R.id.labtrans_ok);
    }

    @Override
    protected void initView() {
        titlename.setText(R.string.title_activity_wplabor_details);
        backImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        laborcode.setText(labtrans.laborcode);
        craft.setText(labtrans.craft);
        startdate.setText(labtrans.startdate);
        regularhrs.setText(labtrans.regularhrs);

        laborcode.setEnabled(false);
        craft.setEnabled(false);
        startdate.setEnabled(false);
        regularhrs.setEnabled(false);

        setDataListener();
        laborcode.setOnClickListener(new LayoutOnClickListener(Constants.LABORCRAFTRATE));
        craft.setOnClickListener(new LayoutOnClickListener(Constants.CRAFTRATE));
        startdate.setOnClickListener(new MydateListener());
        ok.setOnClickListener(okOnClickListener);

        if(labtrans.labtransid!=null&&!labtrans.labtransid.equals("")){
            ok.setVisibility(View.GONE);
        }
    }

    private View.OnClickListener okOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            finish();
        }
    };

//    private View.OnClickListener taskidlayoutOnClickListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//            NormalListDialog();
//        }
//    };
//
//    private void NormalListDialog() {
//        final NormalListDialog dialog = new NormalListDialog(LabtransDetailsActivity.this, mMenuItems);
//        dialog.title("请选择")//
//                .showAnim(mBasIn)//
//                .dismissAnim(mBasOut)//
//                .show();
//        dialog.setOnOperItemClickL(new OnOperItemClickL() {
//            @Override
//            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                taskid.setText(mMenuItems.get(position).mOperName);
//                dialog.dismiss();
//            }
//        });
//    }

    /**
     * 设置时间选择器*
     */
    private void setDataListener() {

        final Calendar objTime = Calendar.getInstance();
        int iYear = objTime.get(Calendar.YEAR);
        int iMonth = objTime.get(Calendar.MONTH);
        int iDay = objTime.get(Calendar.DAY_OF_MONTH);


        datePickerDialog = new DatePickerDialog(this, new datelistener(), iYear, iMonth, iDay);
    }

    private class MydateListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            sb = new StringBuffer();
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
            startdate.setText(sb);
        }
    }


    private class LayoutOnClickListener implements View.OnClickListener {
        int requestCode;

        private LayoutOnClickListener(int requestCode) {
            this.requestCode = requestCode;
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(LabtransDetailsActivity.this, OptionActivity.class);
            intent.putExtra("requestCode", requestCode);
            if (requestCode == Constants.LABORCRAFTRATE) {
                intent.putExtra("craft", craft.getText().toString());
            }
            startActivityForResult(intent, requestCode);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Option option;
        switch (resultCode) {
            case Constants.LABORCRAFTRATE:
                option = (Option) data.getSerializableExtra("option");
                laborcode.setText(option.getName());
                craft.setText(option.getDescription());
                break;
            case Constants.CRAFTRATE:
                option = (Option) data.getSerializableExtra("option");
                craft.setText(option.getName());
        }
    }

//    /**
//     * 添加任务数据*
//     */
//    private void addTaskData() {
//        if (woactivityList != null && woactivityList.size() != 0) {
//            for (int i = 0; i < woactivityList.size(); i++) {
//                mMenuItems.add(new DialogMenuItem(woactivityList.get(i).getTaskid(), 0));
//            }
//        }
//    }
}
