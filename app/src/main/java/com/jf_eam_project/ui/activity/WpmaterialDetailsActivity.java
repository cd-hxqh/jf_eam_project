package com.jf_eam_project.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jf_eam_project.R;
import com.jf_eam_project.model.Wpmaterial;

/**
 * Created by think on 2015/12/7.
 * 计划物料详情页面
 */
public class WpmaterialDetailsActivity extends BaseActivity{
    private Wpmaterial wpmaterial;

    /**
     * 标题*
     */
    private TextView titlename;
    /**
     * 返回*
     */
    private ImageView backImageView;

    private TextView taskid;//任务
    private RelativeLayout taskidlayout;
    private TextView itemnum;//项目  项目/物料
    private RelativeLayout itemnumlayout;
    private EditText itemqty;//数量
    private TextView location;//库房
    private RelativeLayout locationlayout;
    private TextView storelocsite;//库房地点
    private TextView requestby;//请求者
    private TextView requiredate;//要求日期
    private RelativeLayout requiredatelayout;
    private Button ok;//确认

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wpmaterial_details);

        geiIntentData();
        findViewById();
        initView();
    }

    /**
     * 获取数据*
     */
    private void geiIntentData() {
        wpmaterial = (Wpmaterial) getIntent().getSerializableExtra("wpmaterial");
    }
    @Override
    protected void findViewById() {
        titlename = (TextView) findViewById(R.id.title_name);
        backImageView = (ImageView) findViewById(R.id.title_back_id);

        taskid = (TextView) findViewById(R.id.wpmaterial_taskid);
        taskidlayout = (RelativeLayout) findViewById(R.id.wpmaterial_taskid_layout);
        itemnum = (TextView) findViewById(R.id.wpmaterial_itemnum);
        itemnumlayout = (RelativeLayout) findViewById(R.id.wpmaterial_itemnum_layout);
        itemqty = (EditText) findViewById(R.id.wpmaterial_itemqty);
        location = (TextView) findViewById(R.id.wpmaterial_location);
        locationlayout = (RelativeLayout) findViewById(R.id.wpmaterial_location_layout);
        storelocsite = (TextView) findViewById(R.id.wpmaterial_storelocsite);
        requiredate = (TextView) findViewById(R.id.wpmaterial_requiredate);
        requiredatelayout = (RelativeLayout) findViewById(R.id.wpmaterial_requiredate_layout);
    }

    @Override
    protected void initView() {
        titlename.setText(R.string.title_activity_wpmaterial_details);
        backImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        itemnum.setText(wpmaterial.itemnum);
        itemqty.setText(wpmaterial.itemqty);
        location.setText(wpmaterial.location);
        storelocsite.setText(wpmaterial.storelocsite);
        requiredate.setText(wpmaterial.requiredate);
    }
}
