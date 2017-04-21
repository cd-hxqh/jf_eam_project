package com.jf_eam_project.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.jf_eam_project.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 巡检管理界面
 */

public class XunJan_Activity extends BaseActivity {

    @Bind(R.id.title_name)
    TextView titleText; //标题


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_xunjan);
        ButterKnife.bind(this);
        findViewById();

        initView();

    }

    @Override
    protected void findViewById() {

    }

    @Override
    protected void initView() {
        titleText.setText(getString(R.string.xjgl_text));
    }

    @OnClick(R.id.title_back_id)
    void setBackImageViewOnClickListener() {
        finish();
    }

    @OnClick(R.id.udinspo_dqdjd_id)
    void setDqdjd_layoutOnClickListener() {
        Intent intent = new Intent(XunJan_Activity.this, Udinspo_Activity.class);
        intent.putExtra("title", XunJan_Activity.this.getString(R.string.dqdjd_text));
        intent.putExtra("assettype", "电气");
        intent.putExtra("checktype", "定检");
        intent.putExtra("inspotype", "05");
        startActivityForResult(intent, 0);
    }

    @OnClick(R.id.udinspo_fjdjd_id)
    void setFjdjd_layoutOnClickListener() {
        Intent intent1 = new Intent(XunJan_Activity.this, Udinspo_Activity.class);
        intent1.putExtra("title", XunJan_Activity.this.getString(R.string.fjdjd_text));
        intent1.putExtra("assettype", "风机");
        intent1.putExtra("checktype", "定检");
        intent1.putExtra("inspotype", "05");
        startActivityForResult(intent1, 0);
    }

    @OnClick(R.id.udinspo_fjxjd_id)
    void setFjxjd_layoutOnClickListener() {
        Intent intent2 = new Intent(XunJan_Activity.this, Udinspo_Activity.class);
        intent2.putExtra("inspotype", "05");
        intent2.putExtra("assettype", "风机");
        intent2.putExtra("checktype", "巡检");
        intent2.putExtra("title", XunJan_Activity.this.getString(R.string.fjxjd_text));
        startActivityForResult(intent2, 0);
    }

    @OnClick(R.id.udinspo_jdxl_id)
    void setJdxl_layoutOnClickListener() {
        Intent intent3 = new Intent(XunJan_Activity.this, Udinspo_Activity.class);
        intent3.putExtra("title", XunJan_Activity.this.getString(R.string.jdxl_text));
        intent3.putExtra("inspotype", "02");
        startActivityForResult(intent3, 0);
    }

    @OnClick(R.id.udinspo_xtb_id)
    void setXtb_layoutOnClickListener() {
        Intent intent4 = new Intent(XunJan_Activity.this, Udinspo_Activity.class);
        intent4.putExtra("title", XunJan_Activity.this.getString(R.string.jdxl_text));
        intent4.putExtra("inspotype", "01");
        startActivityForResult(intent4, 0);
    }

    @OnClick(R.id.udinspo_qtsb_id)
    void setQtsb_layoutOnClickListener() {
        Intent intent5 = new Intent(XunJan_Activity.this, Udinspo_Activity.class);
        intent5.putExtra("title", XunJan_Activity.this.getString(R.string.qtsb_text));
        intent5.putExtra("inspotype", "03");
        startActivityForResult(intent5, 0);
    }

    @OnClick(R.id.udinspo_rcxj_id)
    void setRcxj_layoutOnClickListener() {
        Intent intent6 = new Intent(XunJan_Activity.this, Udinspo_Activity.class);
        intent6.putExtra("title", XunJan_Activity.this.getString(R.string.rcxj_text));
        intent6.putExtra("inspotype", "04");
        startActivityForResult(intent6, 0);
    }

    @OnClick(R.id.udinspo_jkzx_id)
    void setJkzx_layoutOnClickListener() {
        Intent intent7 = new Intent(XunJan_Activity.this, Udinspo_Activity.class);
        intent7.putExtra("title", XunJan_Activity.this.getString(R.string.jkzx_text));
        intent7.putExtra("inspotype", "06");
        startActivityForResult(intent7, 0);
    }


}
