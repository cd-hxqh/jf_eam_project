package com.jf_eam_project.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jf_eam_project.R;
import com.jf_eam_project.model.Wplabor;

/**
 * Created by think on 2015/12/7.
 * 计划员工详情页面
 */
public class WplaborDetailsActivity extends BaseActivity{
    private Wplabor wplabor;

    /**
     * 标题*
     */
    private TextView titlename;
    /**
     * 返回*
     */
    private ImageView backImageView;

    private TextView taskid;//任务
    private TextView craft;//工种
    private TextView quantity;//数量
    private TextView laborhrs;//常规时数

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wplabor_details);

        geiIntentData();
        findViewById();
        initView();
    }

    /**
     * 获取数据*
     */
    private void geiIntentData() {
        wplabor = (Wplabor) getIntent().getSerializableExtra("wplabor");
    }
    @Override
    protected void findViewById() {
        titlename = (TextView) findViewById(R.id.title_name);
        backImageView = (ImageView) findViewById(R.id.title_back_id);

        taskid = (TextView) findViewById(R.id.wplabor_taskid);
        craft = (TextView) findViewById(R.id.wplabor_craft);
        quantity = (TextView) findViewById(R.id.wplabor_quantity);
        laborhrs = (TextView) findViewById(R.id.wplabor_laborhrs);
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

        taskid.setText(wplabor.taskid);
        craft.setText(wplabor.craft);
        quantity.setText(wplabor.quantity);
        laborhrs.setText(wplabor.laborhrs);
    }
}
