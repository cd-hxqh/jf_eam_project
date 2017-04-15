package com.jf_eam_project.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.jf_eam_project.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 故障缺陷界面
 */

public class GuZhang_Activity extends BaseActivity {

    private static final String TAG = "GuZhang_Activity";
    @Bind(R.id.title_name)
    TextView titleText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_guzhang);
        ButterKnife.bind(this);
        findViewById();


        initView();

    }

    @Override
    protected void findViewById() {
    }

    @Override
    protected void initView() {
        titleText.setText(getString(R.string.gzqx_text));
    }

    //返回事件
    @OnClick(R.id.title_back_id)
    void setBackImageViewOnClickListener() {
        finish();
    }

    @OnClick(R.id.udinspo_dqdjd_id) //故障提报单
    void setGztbd_layoutOnClickListener() {
        Intent intent = new Intent(GuZhang_Activity.this, Udreport_Activity.class);
        intent.putExtra("title", GuZhang_Activity.this.getString(R.string.gztbd_text));
        intent.putExtra("apptype", "FAULT");
        startActivityForResult(intent, 0);
    }
    @OnClick(R.id.udinspo_fjdjd_id) //缺陷提报单
    void setQxtbd_layoutOnClickListener() {
        Intent intent = new Intent(GuZhang_Activity.this, Udreport_Activity.class);
        intent.putExtra("title", GuZhang_Activity.this.getString(R.string.qxtbd_text));
        intent.putExtra("apptype", "HIDDEN");
        startActivityForResult(intent, 0);
    }



}
