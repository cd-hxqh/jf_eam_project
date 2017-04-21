package com.jf_eam_project.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.jf_eam_project.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 领导界面
 */

public class LeadershipActivity extends BaseActivity {

    private static final String TAG = "LeadershipActivity";

    @Bind(R.id.title_name)
    TextView titleText; //标题


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leadership);
        ButterKnife.bind(this);
        initView();

    }

    @Override
    protected void findViewById() {
    }

    @Override
    protected void initView() {
        titleText.setText(getString(R.string.kpi_text));
    }

    @OnClick(R.id.title_back_id)
    void setBackImageViewOnClickListener() {
        finish();
    }


    //电量统计
    @OnClick(R.id.electricity_text_id)
    void setelectricityTextViewOnClickListener() {
        Intent intent = new Intent();
        intent.setClass(LeadershipActivity.this, ElectricityActivity.class);
        startActivityForResult(intent, 0);
    }

    //电量统计
    @OnClick(R.id.gz_text_id)
    void setgzTextViewOnClickListener() {
        Intent intent = new Intent();
        intent.setClass(LeadershipActivity.this, GzStatisticalActivity.class);
        startActivityForResult(intent, 0);
    }


}
