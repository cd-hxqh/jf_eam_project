package com.jf_eam_project.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jf_eam_project.R;
import com.jf_eam_project.utils.AccountUtils;
import com.jf_eam_project.utils.GetNowTime;

/**
 * Created by think on 2015/11/30.
 * 新增采购计划
 */
public class Pr_AddActivity extends BaseActivity {

    private static final String TAG = "Pr_AddActivity";
    private TextView titlename; //标题
    private ImageView menuImageView; //菜单按钮
    private ImageView backlayout; //返回按钮

    private EditText description;//描述

    private TextView sqrText; //申请人

    private TextView uddeptdesText; //运行单位

    private TextView udwztypeText; //物资类别

    private TextView udprojectnumText; //项目编号

    private TextView udprtypeText; //采购类型

    private TextView issuedateText; //申请日期

    private TextView orderdateText; //创建日期

    private TextView requireddateText; //要求日期

    private TextView currencycodeText; //货币

    private TextView pretaxtotalText; //税前总计

    private TextView totaltax1Text; //税款总计

    private TextView totalcostText; //成本总计


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pr_add);

        findViewById();
        initView();
    }


    @Override
    protected void findViewById() {
        titlename = (TextView) findViewById(R.id.title_name);
        backlayout = (ImageView) findViewById(R.id.title_back_id);
        menuImageView = (ImageView) findViewById(R.id.title_add);

        description = (EditText) findViewById(R.id.description_text);
        sqrText = (TextView) findViewById(R.id.displayname_text_id);
        uddeptdesText = (TextView) findViewById(R.id.uddeptdes_text_id);
        udwztypeText = (TextView) findViewById(R.id.udwztype_text_id);
        udprojectnumText = (TextView) findViewById(R.id.udprojectnum_text_id);
        udprtypeText = (TextView) findViewById(R.id.udprtype_text_id);
        issuedateText = (TextView) findViewById(R.id.pr_issuedate_text_id);
        orderdateText = (TextView) findViewById(R.id.orderdate_text_id);
        requireddateText = (TextView) findViewById(R.id.requireddate_text_id);
        currencycodeText = (TextView) findViewById(R.id.po_currencycode_text_id);
        pretaxtotalText = (TextView) findViewById(R.id.po_pretaxtotal_text_id);
        totaltax1Text = (TextView) findViewById(R.id.po_totaltax1_text_id);
        totalcostText = (TextView) findViewById(R.id.po_totalcost_text_id);
    }

    @Override
    protected void initView() {
        titlename.setText(R.string.pr_add_text);
//        menuImageView.setImageResource(R.drawable.ic_drawer);
//        menuImageView.setVisibility(View.VISIBLE);
//        menuImageView.setOnClickListener(menuImageViewOnClickListener);
        backlayout.setOnClickListener(backlayoutOnClickListener);
        sqrText.setText(AccountUtils.getDisplayName(Pr_AddActivity.this));
        issuedateText.setText(GetNowTime.getTime());


    }


    private View.OnClickListener backlayoutOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            finish();
        }
    };
}
