package com.jf_eam_project.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jf_eam_project.R;
import com.jf_eam_project.model.Woactivity;

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

    private TextView wosequence;//序号
    private TextView wonum;//记录
    private TextView woclass;//记录类
    private TextView assetnum;//资产
    private TextView assetdesc;//资产描述
    private TextView location;//位置
    private TextView locationdesc;//位置描述

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

        wosequence = (TextView) findViewById(R.id.woactivity_wosequence);
        wonum = (TextView) findViewById(R.id.woactivity_wonum);
        woclass = (TextView) findViewById(R.id.woactivity_woclass);
        assetnum = (TextView) findViewById(R.id.woactivity_assetnum);
        assetdesc = (TextView) findViewById(R.id.woactivity_assetdesc);
        location = (TextView) findViewById(R.id.woactivity_location);
        locationdesc = (TextView) findViewById(R.id.woactivity_locationdesc);
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

        wosequence.setText(woactivity.wosequence);
        wonum.setText(woactivity.wonum);
        woclass.setText(woactivity.woclass);
        assetnum.setText(woactivity.assetnum);
        assetdesc.setText(woactivity.assetdesc);
        location.setText(woactivity.location);
        locationdesc.setText(woactivity.locationdesc);
    }
}
