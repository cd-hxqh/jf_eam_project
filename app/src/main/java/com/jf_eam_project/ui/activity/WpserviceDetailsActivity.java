package com.jf_eam_project.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jf_eam_project.R;
import com.jf_eam_project.model.Wpservice;

/**
 * Created by think on 2015/12/7.
 * 计划服务详情页面
 */
public class WpserviceDetailsActivity extends BaseActivity{
    private Wpservice wpservice;

    /**
     * 标题*
     */
    private TextView titlename;
    /**
     * 返回*
     */
    private ImageView backImageView;

    private TextView taskid;//任务
    private TextView linetype;//行类型
    private TextView requiredate;//要求日期
    private TextView requestby;//请求者

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wpservice_details);

        geiIntentData();
        findViewById();
        initView();
    }

    /**
     * 获取数据*
     */
    private void geiIntentData() {
        wpservice = (Wpservice) getIntent().getSerializableExtra("wpservice");
    }
    @Override
    protected void findViewById() {
        titlename = (TextView) findViewById(R.id.title_name);
        backImageView = (ImageView) findViewById(R.id.title_back_id);

        taskid = (TextView) findViewById(R.id.wpservice_taskid);
        linetype = (TextView) findViewById(R.id.wpservice_linetype);
        requiredate = (TextView) findViewById(R.id.wpservice_requiredate);
        requestby = (TextView) findViewById(R.id.wpservice_requestby);
    }

    @Override
    protected void initView() {
        titlename.setText(R.string.title_activity_wpservice_details);
        backImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        taskid.setText(wpservice.taskid);
        linetype.setText(wpservice.linetype);
        requiredate.setText(wpservice.requiredate);
        requestby.setText(wpservice.requestby);
    }
}
