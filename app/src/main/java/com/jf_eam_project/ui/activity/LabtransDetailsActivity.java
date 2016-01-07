package com.jf_eam_project.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jf_eam_project.R;
import com.jf_eam_project.model.Labtrans;
import com.jf_eam_project.model.Wplabor;

/**
 * Created by think on 2015/12/22.
 * 实际员工详情页面
 */
public class LabtransDetailsActivity extends BaseActivity{
    private Labtrans labtrans;

    /**
     * 标题*
     */
    private TextView titlename;
    /**
     * 返回*
     */
    private ImageView backImageView;
    //员工，开始日期，常规时数，工种，费率，类型
    private TextView taskid;//任务
    private TextView laborcode;//员工
    private TextView startdate;//开始日期
    private TextView regularhrs;//常规时数
    private TextView craft;//工种
    private TextView payrate;//费率
    private TextView transtype;//类型

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
        labtrans = (Labtrans) getIntent().getSerializableExtra("labtrans");
    }
    @Override
    protected void findViewById() {
        titlename = (TextView) findViewById(R.id.title_name);
        backImageView = (ImageView) findViewById(R.id.title_back_id);

        taskid = (TextView) findViewById(R.id.labtrans_laborcode);
        craft = (TextView) findViewById(R.id.labtrans_craft);
        payrate = (TextView) findViewById(R.id.labtrans_payrate);
        transtype = (TextView) findViewById(R.id.labtrans_transtype);
        startdate = (TextView) findViewById(R.id.labtrans_startdate);
        regularhrs = (TextView) findViewById(R.id.labtrans_regularhrs);
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

        taskid.setText(labtrans.taskid);
        craft.setText(labtrans.craft);
        payrate.setText(labtrans.payrate);
        transtype.setText(labtrans.transtype);
        startdate.setText(labtrans.startdate);
        regularhrs.setText(labtrans.regularhrs);
    }
}
