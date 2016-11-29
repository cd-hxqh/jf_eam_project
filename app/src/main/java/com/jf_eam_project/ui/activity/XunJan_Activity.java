package com.jf_eam_project.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jf_eam_project.R;

/**
 * 巡检管理界面
 */

public class XunJan_Activity extends BaseActivity {

    private static final String TAG = "XunJanActivity";
    /**
     * 标题*
     */
    private TextView titleText;
    /**
     * 返回按钮
     **/
    private ImageView backImageView;

    /**
     * 电气定检单
     */
    private LinearLayout dqdjd_layout;


    /**
     * 风机定检单
     */
    private LinearLayout fjdjd_layout;


    /**
     * 风机巡检单
     */
    private LinearLayout fjxjd_layout;

    /**
     * 集电线路
     */
    private LinearLayout jdxl_layout;
    /**
     * 箱台变
     */
    private LinearLayout xtb_layout;
    /**
     * 其他设备
     */
    private LinearLayout qtsb_layout;
    /**
     * 日常巡检
     */
    private LinearLayout rcxj_layout;
    /**
     * 集控中心*
     */
    private LinearLayout jkzx_layout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_xunjan);

        findViewById();

        initView();

    }

    @Override
    protected void findViewById() {
        titleText = (TextView) findViewById(R.id.title_name);
        backImageView = (ImageView) findViewById(R.id.title_back_id);

        dqdjd_layout = (LinearLayout) findViewById(R.id.udinspo_dqdjd_id);
        fjdjd_layout = (LinearLayout)findViewById(R.id.udinspo_fjdjd_id);
        fjxjd_layout = (LinearLayout)findViewById(R.id.udinspo_fjxjd_id);
        jdxl_layout = (LinearLayout) findViewById(R.id.udinspo_jdxl_id);
        xtb_layout = (LinearLayout)findViewById(R.id.udinspo_xtb_id);
        qtsb_layout = (LinearLayout) findViewById(R.id.udinspo_qtsb_id);
        rcxj_layout = (LinearLayout)findViewById(R.id.udinspo_rcxj_id);
        jkzx_layout = (LinearLayout)findViewById(R.id.udinspo_jkzx_id);
    }

    @Override
    protected void initView() {
        titleText.setText(getString(R.string.xjgl_text));
        backImageView.setOnClickListener(backImageViewOnClickListener);
        dqdjd_layout.setOnClickListener(onClickListener);
        fjdjd_layout.setOnClickListener(onClickListener);
        fjxjd_layout.setOnClickListener(onClickListener);
        jdxl_layout.setOnClickListener(onClickListener);
        xtb_layout.setOnClickListener(onClickListener);
        qtsb_layout.setOnClickListener(onClickListener);
        rcxj_layout.setOnClickListener(onClickListener);
        jkzx_layout.setOnClickListener(onClickListener);
    }















    private View.OnClickListener backImageViewOnClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            finish();
        }
    };







    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {


            switch (v.getId()) {
                case R.id.udinspo_dqdjd_id: //电气定检单

                    Intent intent = new Intent(XunJan_Activity.this, Udinspo_Activity.class);
                    intent.putExtra("title", XunJan_Activity.this.getString(R.string.dqdjd_text));
                    intent.putExtra("assettype", "电气");
                    intent.putExtra("checktype", "定检");
                    intent.putExtra("inspotype", "05");
                    startActivityForResult(intent, 0);

                    break;
                case R.id.udinspo_fjdjd_id: //风机定检单

                    Intent intent1 = new Intent(XunJan_Activity.this, Udinspo_Activity.class);
                    intent1.putExtra("title", XunJan_Activity.this.getString(R.string.fjdjd_text));
                    intent1.putExtra("assettype", "风机");
                    intent1.putExtra("checktype", "定检");
                    intent1.putExtra("inspotype", "05");
                    startActivityForResult(intent1, 0);

                    break;

                case R.id.udinspo_fjxjd_id: //风机巡检单
                    Intent intent2 = new Intent(XunJan_Activity.this, Udinspo_Activity.class);
                    intent2.putExtra("inspotype", "05");
                    intent2.putExtra("assettype", "风机");
                    intent2.putExtra("checktype", "巡检");
                    intent2.putExtra("title", XunJan_Activity.this.getString(R.string.fjxjd_text));
                    startActivityForResult(intent2, 0);
                    break;
                case R.id.udinspo_jdxl_id: //集电线路
                    Intent intent3 = new Intent(XunJan_Activity.this, Udinspo_Activity.class);
                    intent3.putExtra("title", XunJan_Activity.this.getString(R.string.jdxl_text));
                    intent3.putExtra("inspotype", "02");
                    startActivityForResult(intent3, 0);
                    break;
                case R.id.udinspo_xtb_id: //箱台变
                    Intent intent4 = new Intent(XunJan_Activity.this, Udinspo_Activity.class);
                    intent4.putExtra("title", XunJan_Activity.this.getString(R.string.jdxl_text));
                    intent4.putExtra("inspotype", "01");
                    startActivityForResult(intent4, 0);
                    break;
                case R.id.udinspo_qtsb_id: //其他设备
                    Intent intent5 = new Intent(XunJan_Activity.this, Udinspo_Activity.class);
                    intent5.putExtra("title", XunJan_Activity.this.getString(R.string.qtsb_text));
                    intent5.putExtra("inspotype", "03");
                    startActivityForResult(intent5, 0);
                    break;
                case R.id.udinspo_rcxj_id: //日常巡检
                    Intent intent6 = new Intent(XunJan_Activity.this, Udinspo_Activity.class);
                    intent6.putExtra("title", XunJan_Activity.this.getString(R.string.rcxj_text));
                    intent6.putExtra("inspotype", "04");
                    startActivityForResult(intent6, 0);
                    break;
                case R.id.udinspo_jkzx_id: //集控中心
                    Intent intent7 = new Intent(XunJan_Activity.this, Udinspo_Activity.class);
                    intent7.putExtra("title", XunJan_Activity.this.getString(R.string.jkzx_text));
                    intent7.putExtra("inspotype", "06");
                    startActivityForResult(intent7, 0);
                    break;

            }
        }
    };


}
