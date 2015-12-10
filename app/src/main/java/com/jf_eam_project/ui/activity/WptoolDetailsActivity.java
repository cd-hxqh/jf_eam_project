package com.jf_eam_project.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jf_eam_project.R;
import com.jf_eam_project.model.Wptool;

/**
 * Created by think on 2015/12/7.
 * 计划工具详情页面
 */
public class WptoolDetailsActivity extends BaseActivity{
    private Wptool wptool;

    /**
     * 标题*
     */
    private TextView titlename;
    /**
     * 返回*
     */
    private ImageView backImageView;

    private TextView taskid;//任务
    private TextView itemnum;//工具
    private TextView itemqty;//数量
    private TextView rate;//费率

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wptool_details);

        geiIntentData();
        findViewById();
        initView();
    }

    /**
     * 获取数据*
     */
    private void geiIntentData() {
        wptool = (Wptool) getIntent().getSerializableExtra("wptool");
    }
    @Override
    protected void findViewById() {
        titlename = (TextView) findViewById(R.id.title_name);
        backImageView = (ImageView) findViewById(R.id.title_back_id);

        taskid = (TextView) findViewById(R.id.wptool_taskid);
        itemnum = (TextView) findViewById(R.id.wptool_itemnum);
        itemqty = (TextView) findViewById(R.id.wptool_itemqty);
        rate = (TextView) findViewById(R.id.wptool_rate);
    }

    @Override
    protected void initView() {
        titlename.setText(R.string.title_activity_wptool_details);
        backImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        taskid.setText(wptool.taskid);
        itemnum.setText(wptool.itemnum);
        itemqty.setText(wptool.itemqty);
        rate.setText(wptool.rate);
    }
}
