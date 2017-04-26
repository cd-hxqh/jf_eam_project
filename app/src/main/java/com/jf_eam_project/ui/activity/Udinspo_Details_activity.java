package com.jf_eam_project.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.jf_eam_project.Dao.UdinspoDao;
import com.jf_eam_project.R;
import com.jf_eam_project.model.Udinspo;
import com.jf_eam_project.utils.GetNowTime;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 巡检单详情
 */
public class Udinspo_Details_activity extends BaseActivity {

    private static final String TAG = "Udinspo_Details_activity";

    @Bind(R.id.title_name)
    TextView titleView;//标题
    @Bind(R.id.title_back_id)
    ImageView backImageView;//返回按钮

    @Bind(R.id.title_add)
    ImageView menuImageView;//菜单

    @Bind(R.id.udinspoasset_btn_id)
    Button udinspoassetBtn;//巡检备件_btn


    /**
     * 界面信息显示*
     */
    @Bind(R.id.udinspo_insponum_text)
    TextView insponumText; //巡检单
    @Bind(R.id.udinspo_description_text)
    TextView descriptionText; //描述
    @Bind(R.id.udinspo_inspplannum_text)
    TextView inspplannumText; //计划编号
    @Bind(R.id.inspschemenum_text_id)
    TextView inspschemenumText; //检修方案编号
    @Bind(R.id.udinspo_inspoby_text)
    TextView inspobyText; //检修人
    @Bind(R.id.udinspo_inspodate_text)
    TextView inspodateText; //检修日期
    @Bind(R.id.startdate_text_id)
    TextView startdateText; //开始时间
    @Bind(R.id.enddate_text_id)
    TextView enddateText; //结束时间
    @Bind(R.id.statu_text_id)
    TextView statusText; //状态
    @Bind(R.id.description_text_id)
    TextView branchText; //分公司

    @Bind(R.id.uddept_description_text_id)
    TextView farmText; //风电场
    @Bind(R.id.weather_text_id)
    TextView weatherText; //天气
    @Bind(R.id.temperature_text_id)
    TextView temperatureText; //温度


    private TextView udinspoasset; //设备部件

    private Udinspo udinspo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_udinspo_details);
        ButterKnife.bind(this);
        initData();
        findViewById();
        initView();

    }

    /**
     * 获取初始话数据*
     */
    private void initData() {
        udinspo = (Udinspo) getIntent().getSerializableExtra("udinspo");
    }

    @Override
    protected void findViewById() {

    }

    @Override
    protected void initView() {
        titleView.setText(getString(R.string.details_text));

        descriptionText.setFocusable(false);
        descriptionText.setFocusableInTouchMode(false);
        if (udinspo != null) {
            insponumText.setText(udinspo.getInsponum() == null ? "" : udinspo.getInsponum());
            descriptionText.setText(udinspo.getDescription() == null ? "" : udinspo.getDescription());
            inspplannumText.setText(udinspo.udinspmainplandesc == null ? "" : udinspo.udinspmainplandesc);
            inspschemenumText.setText(udinspo.inspschemenumdesc == null ? "" : udinspo.inspschemenumdesc);
            inspobyText.setText(udinspo.inspobydisplayname == null ? "" : udinspo.inspobydisplayname);
            inspodateText.setText(udinspo.getInspodate() == null ? "" : udinspo.getInspodate());
            startdateText.setText(udinspo.getStartdate() == null ? "" : udinspo.getStartdate());
            enddateText.setText(udinspo.getEnddate() == null ? "" : udinspo.getEnddate());


            statusText.setText(udinspo.statusdesc == null ? "" : udinspo.statusdesc);


            String branch = udinspo.getBranch();
            if (branch.equals("01001")) {
                branchText.setText("华北分公司");
            } else {
                branchText.setText(udinspo.getBranch() == null ? "" : udinspo.getBranch());
            }
            farmText.setText(udinspo.getUdbelongdesc() == null ? "" : udinspo.getUdbelongdesc());
            weatherText.setText(udinspo.getWeather() == null ? "" : udinspo.getWeather());
            temperatureText.setText(udinspo.getTemperature() == null ? "" : udinspo.getTemperature());
        }


    }


    //返回按钮
    @OnClick(R.id.title_back_id)
    void setBackImageViewOnClickListenrer() {
        finish();
    }

    /**
     * 执行*
     */
    @OnClick(R.id.udinspoasset_btn_id)
    void setUdinspoassetBtnOnClickListener() {
        //更新状态
        updateUdinspo();

        Intent intent = new Intent(Udinspo_Details_activity.this, Udinspoassetnew_Activity.class);
        intent.putExtra("insponum", udinspo.insponum);
        intent.putExtra("branch", udinspo.branch);
        intent.putExtra("udbelong", udinspo.udbelong);
        intent.putExtra("assettype", udinspo.assettype);
        startActivityForResult(intent, 0);
    }


    /**
     * 更新操作状态*
     */
    private void updateUdinspo() {

        if (udinspo.getStartdate() == null || udinspo.getStartdate().equals("")) {
            udinspo.setStartdate(GetNowTime.getTime());
        }
        if (udinspo.getOperation() == null) {
            udinspo.setOperation("执行中");
        }
        new UdinspoDao(Udinspo_Details_activity.this).update(udinspo);
    }


}
