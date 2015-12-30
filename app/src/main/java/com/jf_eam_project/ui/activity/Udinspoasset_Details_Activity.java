package com.jf_eam_project.ui.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
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
import com.jf_eam_project.model.Udinspoasset;

/**
 * 设备备件详情
 */
public class Udinspoasset_Details_Activity extends BaseActivity {

    private static final String TAG = "Udinspoasset_Details_Activity";

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
    private TextView udinspoassetlinenumText; //序号
    private TextView udinspoassetnumText; //设备编号
    private TextView locationText; //位置
    private TextView locationDescText; //位置描述
    private TextView assetnumText; //设备
    private TextView assetnumDescText; //设备描述
    private TextView childassetnumText; //设备部件


    private Udinspoasset udinspoasset; //设备备件


    private PopupWindow popupWindow;

    private TextView udinspojxxm; //检修项目标准

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_udinspoasset_details);
        initData();
        findViewById();
        initView();
    }

    /**
     * 获取初始话数据*
     */
    private void initData() {
        udinspoasset = (Udinspoasset) getIntent().getSerializableExtra("Udinspoasset");
    }

    @Override
    protected void findViewById() {
        titleView = (TextView) findViewById(R.id.title_name);
        backImageView = (ImageView) findViewById(R.id.title_back_id);
        menuImageView = (ImageView) findViewById(R.id.title_add);

        udinspoassetlinenumText = (TextView) findViewById(R.id.udinspoasset_udinspoassetlinenum_text);
        udinspoassetnumText = (TextView) findViewById(R.id.ud_udinspoassetnum_text);
        locationText = (TextView) findViewById(R.id.udinspoasset_location_text);
        locationDescText = (TextView) findViewById(R.id.udinspoasset_location_description_text);
        assetnumText = (TextView) findViewById(R.id.udinspoasset_assetnum_text);
        assetnumDescText = (TextView) findViewById(R.id.udinspoasset_assetnum_desc_text);
        childassetnumText = (TextView) findViewById(R.id.udinspoasset_childassetnum_text);

    }


    @Override
    protected void initView() {
        titleView.setText(getResources().getString(R.string.udinspoasset_detail_title));
        backImageView.setOnClickListener(backImageViewOnClickListenrer);

        menuImageView.setImageResource(R.drawable.ic_drawer);
        menuImageView.setVisibility(View.VISIBLE);
        menuImageView.setOnClickListener(menuImageViewOnClickListener);


        if (udinspoasset != null) {
            udinspoassetlinenumText.setText(udinspoasset.getUdinspoassetlinenum() == null ? "" : udinspoasset.getUdinspoassetlinenum());
            udinspoassetnumText.setText(udinspoasset.getUdinspoassetnum() == null ? "" : udinspoasset.getUdinspoassetnum());
            locationText.setText(udinspoasset.getLocation() == null ? "" : udinspoasset.getLocation());
            locationDescText.setText(udinspoasset.getLocationsdesc() == null ? "" : udinspoasset.getLocationsdesc());
            assetnumText.setText(udinspoasset.getAssetnum() == null ? "" : udinspoasset.getAssetnum());
            assetnumDescText.setText(udinspoasset.getAssetdesc() == null ? "" : udinspoasset.getAssetdesc());
            childassetnumText.setText(udinspoasset.getChildassetnum()== null ? "" : udinspoasset.getChildassetnum());
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
    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void showPopupWindow(View view) {

        View contentView = LayoutInflater.from(Udinspoasset_Details_Activity.this).inflate(
                R.layout.udinspo_popup_window, null);


        popupWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setTouchable(true);
        popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);

        popupWindow.setBackgroundDrawable(getResources().getDrawable(
                R.drawable.abc_popup_background_mtrl_mult));
        popupWindow.showAsDropDown(view, 0, 20);

        udinspojxxm = (TextView) contentView.findViewById(R.id.udinspoasset_text_id);
        udinspojxxm.setText(getString(R.string.udinspojxxm_title));
        udinspojxxm.setOnClickListener(udinspoassetOnClickListener);

    }


    private View.OnClickListener udinspoassetOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Udinspoasset_Details_Activity.this, Udinspojxxm_Activity.class);
            intent.putExtra("udinspoassetnum", udinspoasset.udinspoassetnum);
            startActivityForResult(intent, 0);
            popupWindow.dismiss();
        }
    };

}
