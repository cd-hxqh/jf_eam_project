package com.jf_eam_project.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jf_eam_project.R;
import com.jf_eam_project.config.Constants;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 工单管理界面
 */

public class WorkOrderActivity extends BaseActivity {

    private static final String TAG = "WorkOrderActivity";
    @Bind(R.id.title_name)
    TextView titleText;//标题

    @Bind(R.id.title_back_id)
    ImageView backImageView;//返回按钮


    @Bind(R.id.work_linear_plan_id)
    LinearLayout plan_layout;//计划工单

    @Bind(R.id.work_linear_not_plan_id)
    LinearLayout not_plan_layout;//非计划工单


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_work);
        ButterKnife.bind(this);
        findViewById();

        initView();

    }

    @Override
    protected void findViewById() {

    }

    @Override
    protected void initView() {
        titleText.setText(getString(R.string.title_activity_work_list));
    }

    //返回
    @OnClick(R.id.title_back_id)void setBackImageViewOnClickListener(){
        finish();
    }


    //计划工单
    @OnClick(R.id.work_linear_plan_id) void setPlan_layoutOnClickListener(){
        Intent intent = new Intent(WorkOrderActivity.this, Work_ListActivity.class);
        intent.putExtra("worktype", Constants.PLAN);
        startActivityForResult(intent, 0);
    }
    //非计划工单
    @OnClick(R.id.work_linear_not_plan_id) void setNot_plan_layoutOnClickListener(){
        Intent intent = new Intent(WorkOrderActivity.this, Work_ListActivity.class);
        intent.putExtra("worktype", Constants.UNPLAN);
        startActivityForResult(intent, 0);
    }




}
