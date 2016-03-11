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
import com.jf_eam_project.model.Uditemreq;
import com.jf_eam_project.model.Uditemreqline;

/**
 * 物资申请编码行表详情
 */
public class UditemreqLine_Detail_activity extends BaseActivity {

    private static final String TAG = "UditemreqLine_Detail_activity";

    /**
     * 标题*
     */
    private TextView titleView;
    /**
     * 返回按钮*
     */
    private ImageView backImageView;



    /**
     * 界面信息显示*
     */
    private TextView udlinenumText; //序号
    private TextView nameText; //物资名称
    private TextView udunitText; //单位
    private TextView uditemreqline1Text; //生产厂家
    private TextView specifyText; //物资型号
    private TextView udunitcostText; //预估单价
    private TextView classstructureidText; //物资分类
    private TextView itemnumText; //物资编码
    private TextView memoText; //备注


    private Uditemreqline uditemreqLine; //物资编码申请行表


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uditemreqline_details);
        initData();
        findViewById();
        initView();
    }

    /**
     * 获取初始话数据*
     */
    private void initData() {
        uditemreqLine = (Uditemreqline) getIntent().getSerializableExtra("uditemreqLine");
    }

    @Override
    protected void findViewById() {
        titleView = (TextView) findViewById(R.id.title_name);
        backImageView = (ImageView) findViewById(R.id.title_back_id);

        udlinenumText = (TextView) findViewById(R.id.udlinenum_text_id);
        nameText = (TextView) findViewById(R.id.name_text_id);
        udunitText = (TextView) findViewById(R.id.udunit_text_id);
        uditemreqline1Text = (TextView) findViewById(R.id.uditemreqline1_text_id);
        specifyText = (TextView) findViewById(R.id.specify_text_id);
        udunitcostText = (TextView) findViewById(R.id.udunitcost_text_id);
        classstructureidText = (TextView) findViewById(R.id.classstructureid_text_id);
        itemnumText = (TextView) findViewById(R.id.itemnum_text_id);
        memoText = (TextView) findViewById(R.id.memo_text_id);

    }

    @Override
    protected void initView() {
        titleView.setText(getString(R.string.uditemreqline_details_title));
        backImageView.setOnClickListener(backImageViewOnClickListenrer);



        if (uditemreqLine != null) {
            udlinenumText.setText(uditemreqLine.udlinenum == null ? "" : uditemreqLine.udlinenum);
            nameText.setText(uditemreqLine.name == null ? "" : uditemreqLine.name);
            udunitText.setText(uditemreqLine.udunit == null ? "" : uditemreqLine.udunit);
            uditemreqline1Text.setText(uditemreqLine.uditemreqline1 == null ? "" : uditemreqLine.uditemreqline1);
            specifyText.setText(uditemreqLine.specify == null ? "" : uditemreqLine.specify);
            udunitcostText.setText(uditemreqLine.udunitcost == null ? "" : uditemreqLine.udunitcost);
            classstructureidText.setText(uditemreqLine.classstructureid == null ? "" : uditemreqLine.classstructureid);
            itemnumText.setText(uditemreqLine.itemnum == null ? "" : uditemreqLine.itemnum);
            memoText.setText(uditemreqLine.memo == null ? "" : uditemreqLine.memo);
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
