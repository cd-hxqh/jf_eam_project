package com.jf_eam_project.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.jf_eam_project.R;
import com.jf_eam_project.model.PR;

/**
 * 采购计划详情
 */
public class PR_Details_Activity extends BaseActivity {

    private static final String TAG = "PR_Details_Activity";

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
     * 界面信息显示*
     */
    private TextView prnumText; //pr编号
    private TextView descriptionText; //描述
    private TextView siteidText; //地点
    private TextView statusText; //状态
    private TextView udprtypeText; //采购类型
    private TextView requestedbyText; //申请人
    private TextView issuedateText; //申请日期
    private TextView statusdateText; //状态日期
    private TextView requireddateText; //要求的日期
    private TextView pretaxtotalText; //税前总计
    private TextView totaltax1Text; //税款总计
    private TextView totalcostText; //成本总计
    private TextView currencycodeText; //货币


    private PR pr; //采购计划

    private PopupWindow popupWindow;

    private LinearLayout polineLinearLayout; //采购单行
    private TextView popText; //文本

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pr_details);
        initData();
        findViewById();
        initView();
    }

    /**
     * 获取初始话数据*
     */
    private void initData() {
        pr = (PR) getIntent().getSerializableExtra("pr");
    }

    @Override
    protected void findViewById() {
        titleView = (TextView) findViewById(R.id.title_name);
        backImageView = (ImageView) findViewById(R.id.title_back_id);
        menuImageView = (ImageView) findViewById(R.id.title_add);

        prnumText = (TextView) findViewById(R.id.pr_prnum_text);
        descriptionText = (TextView) findViewById(R.id.pr_description_text);
        siteidText = (TextView) findViewById(R.id.pr_siteid_text);
        statusText = (TextView) findViewById(R.id.pr_status_text);
        udprtypeText = (TextView) findViewById(R.id.pr_udprtype_text);
        requestedbyText = (TextView) findViewById(R.id.pr_requestedby_text);
        issuedateText = (TextView) findViewById(R.id.pr_issuedate_text);
        statusdateText = (TextView) findViewById(R.id.pr_statusdate_text);
        requireddateText = (TextView) findViewById(R.id.pr_requireddate_text);
        pretaxtotalText = (TextView) findViewById(R.id.pr_pretaxtotal_text);
        totaltax1Text = (TextView) findViewById(R.id.pr_totaltax1_text);
        totalcostText = (TextView) findViewById(R.id.pr_totalcost_text);
        currencycodeText = (TextView) findViewById(R.id.pr_currencycode_text);

    }


    @Override
    protected void initView() {
        titleView.setText(getResources().getString(R.string.pr_detail_title));
        backImageView.setOnClickListener(backImageViewOnClickListenrer);

        menuImageView.setImageResource(R.drawable.ic_drawer);
        menuImageView.setVisibility(View.VISIBLE);
        menuImageView.setOnClickListener(menuImageViewOnClickListener);


        if (pr != null) {
            prnumText.setText(pr.getPrnum() == null ? "" : pr.getPrnum());
            descriptionText.setText(pr.getDescription() == null ? "" : pr.getDescription());
            siteidText.setText(pr.getSiteid() == null ? "" : pr.getSiteid());
            statusText.setText(pr.getStatus() == null ? "" : pr.getStatus());
            udprtypeText.setText(pr.getUdprtype() == null ? "" : pr.getUdprtype());
            requestedbyText.setText(pr.getRequestedby()== null ? "" : pr.getRequestedby());
            issuedateText.setText(pr.getIssuedate() == null ? "" : pr.getIssuedate());
            statusdateText.setText(pr.getStatusdate() == null ? "" : pr.getStatusdate());
            requireddateText.setText(pr.getRequireddate() == null ? "" : pr.getRequireddate());
            pretaxtotalText.setText(pr.getPretaxtotal() == null ? "" : pr.getPretaxtotal());
            totaltax1Text.setText(pr.getTotaltax1() == null ? "" : pr.getTotaltax1());
            totalcostText.setText(pr.getTotalcost() == null ? "" : pr.getTotalcost());
            currencycodeText.setText(pr.getCurrencycode() == null ? "" : pr.getCurrencycode());
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

    /**
     * 菜单按钮*
     */
    private View.OnClickListener menuImageViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            showPopupWindow(menuImageView);
        }
    };

    /**
     * 显示PopupWindow*
     */
    private void showPopupWindow(View view) {

        View contentView = LayoutInflater.from(PR_Details_Activity.this).inflate(
                R.layout.popup_window, null);


        popupWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setTouchable(true);
        popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);

        popupWindow.setBackgroundDrawable(getResources().getDrawable(
                R.drawable.popup_background_mtrl_mult));
        popupWindow.showAsDropDown(view, 0, -40);

        polineLinearLayout = (LinearLayout) contentView.findViewById(R.id.poline_linearlayout_id);
        polineLinearLayout.setOnClickListener(polineOnClickListener);
        popText=(TextView)contentView.findViewById(R.id.popup_text_id);
        popText.setText(getResources().getString(R.string.prline_title));

    }


    private View.OnClickListener polineOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(PR_Details_Activity.this, Prline_Activity.class);
            intent.putExtra("prnum", pr.prnum);
            startActivityForResult(intent, 0);
            popupWindow.dismiss();
        }
    };

}
