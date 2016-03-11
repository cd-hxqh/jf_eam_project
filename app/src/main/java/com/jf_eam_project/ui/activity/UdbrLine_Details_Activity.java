package com.jf_eam_project.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.jf_eam_project.R;
import com.jf_eam_project.model.Udbr;
import com.jf_eam_project.model.Udbrline;

/**
 * 申请行表详情
 */
public class UdbrLine_Details_Activity extends BaseActivity {

    private static final String TAG = "UdbrLine_Details_Activity";

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
    private TextView udbrlinenumText; //行号
    private TextView fromstorelocText; //借出库房
    private TextView itemnumText; //物资编码
    private TextView descriptionText; //描述
    private TextView orderunitText; //单位
    private TextView borrowqtyText; //借用数量
    private TextView returnqtyText; //归还数量


    private Udbrline udbrline; //物资借用归还行表




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_udbrline_details);
        initData();
        findViewById();
        initView();
    }

    /**
     * 获取初始话数据*
     */
    private void initData() {
        udbrline = (Udbrline) getIntent().getSerializableExtra("udbrline");
    }

    @Override
    protected void findViewById() {
        titleView = (TextView) findViewById(R.id.title_name);
        backImageView = (ImageView) findViewById(R.id.title_back_id);

        udbrlinenumText = (TextView) findViewById(R.id.udbrlinenum_text_id);
        fromstorelocText = (TextView) findViewById(R.id.fromstoreloc_text_id);
        itemnumText = (TextView) findViewById(R.id.itemnum_text_id);
        descriptionText = (TextView) findViewById(R.id.description_text_id);
        orderunitText = (TextView) findViewById(R.id.orderunit_text_id);
        borrowqtyText = (TextView) findViewById(R.id.borrowqty_text_id);
        returnqtyText = (TextView) findViewById(R.id.returnqty_text_id);

    }

    @Override
    protected void initView() {
        titleView.setText(getString(R.string.udbrline_text));
        backImageView.setOnClickListener(backImageViewOnClickListenrer);



        if (udbrline != null) {
            udbrlinenumText.setText(udbrline.udbrlinenum == null ? "" : udbrline.udbrlinenum);
            fromstorelocText.setText(udbrline.fromstoreloc == null ? "" : udbrline.fromstoreloc);
            itemnumText.setText(udbrline.itemnum == null ? "" : udbrline.itemnum);
            descriptionText.setText(udbrline.item_description == null ? "" : udbrline.item_description);
            orderunitText.setText(udbrline.item_orderunit== null ? "" : udbrline.item_orderunit);
            borrowqtyText.setText(udbrline.borrowqty == null ? "" : udbrline.borrowqty);
            returnqtyText.setText(udbrline.returnqty == null ? "" : udbrline.returnqty);
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
