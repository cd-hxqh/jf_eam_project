package com.jf_eam_project.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
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

    private TextView itemnum;//项目
    private TextView itemqty;//数量
    private TextView location;//库房
    private TextView storelocsite;//库房地点
    private TextView unitcost;//单位成本
    private TextView restype;//预留类型
    private TextView requiredate;//要求日期

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

        itemnum = (TextView) findViewById(R.id.wpmaterial_itemnum);
        itemqty = (TextView) findViewById(R.id.wpmaterial_itemqty);
        location = (TextView) findViewById(R.id.wpmaterial_location);
        storelocsite = (TextView) findViewById(R.id.wpmaterial_storelocsite);
        unitcost = (TextView) findViewById(R.id.wpmaterial_unitcost);
        restype = (TextView) findViewById(R.id.wpmaterial_restype);
        requiredate = (TextView) findViewById(R.id.wpmaterial_requiredate);
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
        unitcost.setText(wpmaterial.unitcost);
        restype.setText(wpmaterial.restype);
        requiredate.setText(wpmaterial.requiredate);
    }
}
