package com.jf_eam_project.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.jf_eam_project.R;
import com.jf_eam_project.api.HttpManager;
import com.jf_eam_project.api.HttpRequestHandler;
import com.jf_eam_project.api.ig.json.Ig_Json_Model;
import com.jf_eam_project.bean.Results;
import com.jf_eam_project.model.Udreport;
import com.jf_eam_project.model.WorkOrder;
import com.jf_eam_project.ui.adapter.UdreportListAdapter;

import java.io.IOException;
import java.util.ArrayList;

/**
 * 提报单详情
 */
public class Udreport_Details_Activity extends BaseActivity {

    private static final String TAG = "Udreport_Details_Activity";

    /**
     * 标题*
     */
    private TextView titleView;
    /**
     * 返回按钮*
     */
    private ImageView backImageView;
    /**
     * 菜单*
     */
    private ImageView menuImageView;


    /**
     * 故障界面信息显示*
     */
    private TextView reportnumText; //编号
    private TextView descriptionText; //描述
    private TextView udworktypeText; //工单类型
    private TextView assettypeText; //设备专业
    private TextView culevelText; //故障等级
    private TextView branch_descriptionText; //分公司
    private TextView udbelong_descriptionText; //运行单位
    private TextView statustypeText; //故障状态
    private TextView createby_displaynameText; //提报人
    private TextView createdateText; //提报日期
    private TextView xcdateText; //消除时间
    private TextView assetnum_descriptionText; //设备
    private TextView location_descriptionText; //位置
    private TextView cudescribeText; //故障、隐患描述


    /**
     * 缺陷界面信息显示*
     */
    private TextView qxtypeText; //缺陷类型
    private TextView qxsjlyText; //缺陷数据来源

    private Udreport udreport; //提报单

    private int mark; //标识

    /**
     * 提报单编号*
     */
    private String reportnum;

    private ProgressDialog mProgressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData();
        if (udreport.apptype.equals("FAULT")) {
            setContentView(R.layout.activity_gztbd_details);
        } else if (udreport.apptype.equals("HIDDEN")) {
            setContentView(R.layout.activity_qxtbd_details);
        } else {
            setContentView(R.layout.activity_gztbd_details);
        }

        findViewById();
        initView();
    }

    /**
     * 获取初始话数据*
     */
    private void initData() {

        mark = getIntent().getIntExtra("mark", 0);


        udreport = (Udreport) getIntent().getSerializableExtra("udreport");
    }

    @Override
    protected void findViewById() {
        titleView = (TextView) findViewById(R.id.title_name);
        backImageView = (ImageView) findViewById(R.id.title_back_id);
        if (mark==1||udreport.apptype.equals("FAULT")) {
            reportnumText = (TextView) findViewById(R.id.reportnum_text_id);
            descriptionText = (TextView) findViewById(R.id.description_text_id);
            udworktypeText = (TextView) findViewById(R.id.udworktype_text_id);
            assettypeText = (TextView) findViewById(R.id.assettype_text_id);
            culevelText = (TextView) findViewById(R.id.culevel_text_id);
            branch_descriptionText = (TextView) findViewById(R.id.branch_description_text_id);
            udbelong_descriptionText = (TextView) findViewById(R.id.udbelong_description_text);
            statustypeText = (TextView) findViewById(R.id.statustype_text_id);
            createby_displaynameText = (TextView) findViewById(R.id.createby_displayname_text_id);
            createdateText = (TextView) findViewById(R.id.createdate_text_id);
            xcdateText = (TextView) findViewById(R.id.xcdate_text_id);
            assetnum_descriptionText = (TextView) findViewById(R.id.assetnum_description_text_id);
            location_descriptionText = (TextView) findViewById(R.id.location_description_text_id);
            cudescribeText = (TextView) findViewById(R.id.cudescribe_text_id);
        } else {
            reportnumText = (TextView) findViewById(R.id.reportnum_text_id);
            descriptionText = (TextView) findViewById(R.id.description_text_id);

            qxtypeText = (TextView) findViewById(R.id.qxtype_text_id);
            qxsjlyText = (TextView) findViewById(R.id.qxsjly_text_id);

            udworktypeText = (TextView) findViewById(R.id.udworktype_text_id);
            assettypeText = (TextView) findViewById(R.id.assettype_text_id);
            culevelText = (TextView) findViewById(R.id.culevel_text_id);
            branch_descriptionText = (TextView) findViewById(R.id.branch_description_text_id);
            udbelong_descriptionText = (TextView) findViewById(R.id.udbelong_description_text);
            statustypeText = (TextView) findViewById(R.id.status_text_id);
            createby_displaynameText = (TextView) findViewById(R.id.createby_displayname_text_id);
            createdateText = (TextView) findViewById(R.id.createdate_text_id);
            xcdateText = (TextView) findViewById(R.id.xcdate_text_id);
            assetnum_descriptionText = (TextView) findViewById(R.id.assetnum_description_text_id);
            location_descriptionText = (TextView) findViewById(R.id.location_description_text_id);
            cudescribeText = (TextView) findViewById(R.id.cudescribe_text_id);
        }

    }

    @Override
    protected void initView() {
        if (udreport.apptype.equals("FAULT")) {
            titleView.setText(getString(R.string.gztbd_title));
        } else if( udreport.apptype.equals("HIDDEN")){
            titleView.setText(getString(R.string.qxtbd_title));
        }else{
            titleView.setText("提报单详情");
        }
        backImageView.setOnClickListener(backImageViewOnClickListenrer);


        if (udreport != null) {
            reportnumText.setText(udreport.reportnum == null ? "" : udreport.reportnum);
            descriptionText.setText(udreport.description == null ? "" : udreport.description);
            if (udreport.apptype.equals("HIDDEN")) {
                qxtypeText.setText(udreport.qxtype == null ? "" : udreport.qxtype);
                qxsjlyText.setText(udreport.qxsjly == null ? "" : udreport.qxsjly);
            }

            udworktypeText.setText(udreport.udworktype == null ? "" : udreport.udworktype);
            assettypeText.setText(udreport.assettype == null ? "" : udreport.assettype);
            culevelText.setText(udreport.culevel == null ? "" : udreport.culevel);
            branch_descriptionText.setText(udreport.branch_description == null ? "" : udreport.branch_description);
            udbelong_descriptionText.setText(udreport.udbelong_description == null ? "" : udreport.udbelong_description);
            statustypeText.setText(udreport.statustype == null ? "" : udreport.statustype);
            createby_displaynameText.setText(udreport.createby_displayname == null ? "" : udreport.createby_displayname);
            createdateText.setText(udreport.createdate == null ? "" : udreport.createdate);
            xcdateText.setText(udreport.xcdate == null ? "" : udreport.xcdate);
            assetnum_descriptionText.setText(udreport.assetnum_description == null ? "" : udreport.assetnum_description);
            location_descriptionText.setText(udreport.location_description == null ? "" : udreport.location_description);
            cudescribeText.setText(udreport.cudescribe == null ? "" : udreport.cudescribe);
        }


    }

    /**
     * 返回按钮*
     */
    private View.OnClickListener backImageViewOnClickListenrer = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };





}
