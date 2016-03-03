package com.jf_eam_project.ui.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.jf_eam_project.R;
import com.jf_eam_project.config.Constants;
import com.jf_eam_project.model.Option;
import com.jf_eam_project.model.Wpmaterial;
import com.jf_eam_project.ui.widget.CumTimePickerDialog;

import java.util.Calendar;

/**
 * Created by think on 2015/12/7.
 * 物料详情页面
 */
public class Wpmaterial_DetailsActivity extends BaseActivity {
    private Wpmaterial wpmaterial;

    /**
     * 标题*
     */
    private TextView titlename;

    /**
     * 返回*
     */
    private ImageView backImageView;

    /**
     * 界面说明*
     */
    private TextView itemnumText; //项目
    private TextView descriptionText; //描述
    private TextView linetypeText; //行类型
    private TextView itemqtyText; //数量
    private TextView orderunitText; //订购单位
    private TextView unitcostText; //单位成本
    private TextView linecostText; //行成本
    private TextView locationText; //库房
    private TextView restypeText; //预留类型
    private TextView requestbyText; //请求者
    private TextView requiredateText; //要求的日期

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wpmaterial1_details);

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

        itemnumText = (TextView) findViewById(R.id.wpmaterial_itemnum);
        descriptionText = (TextView) findViewById(R.id.wpmaterial_description_text);
        linetypeText = (TextView) findViewById(R.id.wpmaterial_linetype_text);
        itemqtyText = (TextView) findViewById(R.id.wpmaterial_itemqty_id);
        orderunitText = (TextView) findViewById(R.id.wpmaterial_orderunit_id);
        unitcostText = (TextView) findViewById(R.id.wpmaterial_unitcost_id);
        linecostText = (TextView) findViewById(R.id.wpmaterial_linecost_id);
        locationText = (TextView) findViewById(R.id.wpmaterial_location_id);
        restypeText = (TextView) findViewById(R.id.wpmaterial_restype_id);
        requestbyText = (TextView) findViewById(R.id.wpmaterial_requestby_id);
        requiredateText = (TextView) findViewById(R.id.wpmaterial_requiredate_id);

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
        if (wpmaterial != null) {
            itemnumText.setText(wpmaterial.itemnum == null ? "" : wpmaterial.itemnum);
            descriptionText.setText(wpmaterial.description == null ? "" : wpmaterial.description);
            linetypeText.setText(wpmaterial.linetype == null ? "" : wpmaterial.linetype);
            itemqtyText.setText(wpmaterial.itemqty == null ? "" : wpmaterial.itemqty);
            orderunitText.setText(wpmaterial.orderunit == null ? "" : wpmaterial.orderunit);
            unitcostText.setText(wpmaterial.unitcost == null ? "" : wpmaterial.unitcost);
            linecostText.setText(wpmaterial.linecost == null ? "" : wpmaterial.linecost);
            locationText.setText(wpmaterial.location == null ? "" : wpmaterial.location);
            restypeText.setText(wpmaterial.restype == null ? "" : wpmaterial.restype);
            requestbyText.setText(wpmaterial.requestby == null ? "" : wpmaterial.requestby);
            requiredateText.setText(wpmaterial.requiredate == null ? "" : wpmaterial.requiredate);


        }
    }


}
