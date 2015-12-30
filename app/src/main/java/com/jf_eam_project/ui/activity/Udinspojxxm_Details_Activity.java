package com.jf_eam_project.ui.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.jf_eam_project.R;
import com.jf_eam_project.model.Udinspoasset;
import com.jf_eam_project.model.Udinspojxxm;

/**
 * 检修项目标准
 */
public class Udinspojxxm_Details_Activity extends BaseActivity {

    private static final String TAG = "Udinspojxxm_Details_Activity";

    /**
     * 标题*
     */
    private TextView titleView;
    /**
     * 返回按钮*
     */
    private ImageView backImageView;





    /**
     * 界面信息显示*
     */
    private TextView udinspoassetlinenumText; //序号
    private TextView descriptionText; //巡检项目标准
    private TextView udinspojxxm2Text; //数值A
    private TextView udinspojxxm3Text; //数值B
    private TextView udinspojxxm4Text; //数值C
    private TextView fillmethodText; //计量单位
    private TextView executionText; //巡检情况描述
    private TextView udinspojxxm5Text; //巡检人员


    private Udinspojxxm udinspojxxm; //设备备件



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_udinspojxxm_details);
        initData();
        findViewById();
        initView();
    }

    /**
     * 获取初始话数据*
     */
    private void initData() {
        udinspojxxm = (Udinspojxxm) getIntent().getSerializableExtra("Udinspojxxm");
    }

    @Override
    protected void findViewById() {
        titleView = (TextView) findViewById(R.id.title_name);
        backImageView = (ImageView) findViewById(R.id.title_back_id);

        udinspoassetlinenumText = (TextView) findViewById(R.id.udinspoasset_udinspoassetlinenum_text);
        descriptionText = (TextView) findViewById(R.id.udinspojxxm_description_text);
        udinspojxxm2Text = (TextView) findViewById(R.id.udinspojxxm_udinspojxxm2_text);
        udinspojxxm3Text = (TextView) findViewById(R.id.udinspojxxm_udinspojxxm3_text);
        udinspojxxm4Text = (TextView) findViewById(R.id.udinspojxxm_udinspojxxm4_text);
        fillmethodText = (TextView) findViewById(R.id.udinspojxxm_fillmethod_text);
        executionText = (TextView) findViewById(R.id.udinspojxxm_execution_text);
        udinspojxxm5Text = (TextView) findViewById(R.id.ud_udinspojxxm4_text);

    }


    @Override
    protected void initView() {
        titleView.setText(getString(R.string.udinspojxxm_detail_title));
        backImageView.setOnClickListener(backImageViewOnClickListenrer);



        if (udinspojxxm != null) {
            udinspoassetlinenumText.setText(udinspojxxm.getUdinspojxxmlinenum() == null ? "" : udinspojxxm.getUdinspojxxmlinenum());
            descriptionText.setText(udinspojxxm.getDescription() == null ? "" : udinspojxxm.getDescription());
            udinspojxxm2Text.setText(udinspojxxm.getUdinspojxxm2() == null ? "" : udinspojxxm.getUdinspojxxm2());
            udinspojxxm3Text.setText(udinspojxxm.getUdinspojxxm3() == null ? "" : udinspojxxm.getUdinspojxxm3());
            udinspojxxm4Text.setText(udinspojxxm.getUdinspojxxm4() == null ? "" : udinspojxxm.getUdinspojxxm4());
            fillmethodText.setText(udinspojxxm.getFillmethod() == null ? "" : udinspojxxm.getFillmethod());
            executionText.setText(udinspojxxm.getExecution()== null ? "" : udinspojxxm.getExecution());
            udinspojxxm5Text.setText(udinspojxxm.getUdinspojxxm4()== null ? "" : udinspojxxm.getUdinspojxxm4());
        }


    }

    /**
     * 返回按钮*
     */
    private View.OnClickListener backImageViewOnClickListenrer = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };



}
