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
import com.jf_eam_project.model.Po;
import com.jf_eam_project.model.Wfassignment;

/**
 * 流程审批详情
 */
public class Wfm_Details_Activity extends BaseActivity {

    private static final String TAG = "Wfm_Details_Activity";

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
    private TextView ownertableText; //应用程序名
    private TextView descriptionText; //描述
    private TextView assigncodeText; //任务分配人
    private TextView duedateText; //到期日期
    private TextView processnameText; //过程名称
    private TextView roleidText; //任务角色
    private TextView startdateText; //当前日期


    private Wfassignment wfm; //流程审批


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wfm_details);
        initData();
        findViewById();
        initView();
    }

    /**
     * 获取初始话数据*
     */
    private void initData() {
        wfm = (Wfassignment) getIntent().getSerializableExtra("wfassignment");
    }

    @Override
    protected void findViewById() {
        titleView = (TextView) findViewById(R.id.title_name);
        backImageView = (ImageView) findViewById(R.id.title_back_id);

        ownertableText = (TextView) findViewById(R.id.wfm_ownertable_text);
        descriptionText = (TextView) findViewById(R.id.wfm_description_text);
        assigncodeText = (TextView) findViewById(R.id.wfm_assigncode_text);
        duedateText = (TextView) findViewById(R.id.wfm_duedate_text);
        processnameText = (TextView) findViewById(R.id.wfm_processname_text);
        roleidText = (TextView) findViewById(R.id.wfm_roleid_text);
        startdateText = (TextView) findViewById(R.id.wfm_startdate_text);

    }

    @Override
    protected void initView() {
        titleView.setText(getString(R.string.wfm_detail_title));
        backImageView.setOnClickListener(backImageViewOnClickListenrer);



        if (wfm != null) {
            ownertableText.setText(wfm.getOwnertable() == null ? "" : wfm.getOwnertable());
            descriptionText.setText(wfm.getDescription() == null ? "" : wfm.getDescription());
            assigncodeText.setText(wfm.getAssigncode() == null ? "" : wfm.getAssigncode());
            duedateText.setText(wfm.getDuedate() == null ? "" : wfm.getDuedate());
            processnameText.setText(wfm.getProcessname() == null ? "" : wfm.getProcessname());
            roleidText.setText(wfm.getRoleid() == null ? "" : wfm.getRoleid());
            startdateText.setText(wfm.getStartdate() == null ? "" : wfm.getStartdate());
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
