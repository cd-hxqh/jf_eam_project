package com.jf_eam_project.ui.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.jf_eam_project.R;
import com.jf_eam_project.model.Udinspo;

/**
 * 巡检单详情
 */
public class Udinspo_Details_activity extends BaseActivity {

    private static final String TAG = "Udinspo_Details_activity";

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
    private TextView insponumText; //巡检单
    private TextView descriptionText; //描述
    private TextView inspplannumText; //计划编号
    private TextView inspotypeText; //巡检单类型
    private TextView createbyText; //创建人
    private TextView createdateText; //创建时间
    private TextView inspobyText; //巡检人
    private TextView inspodateText; //巡检日期
    private TextView lastrundateText; //本次生成日期
    private TextView nextrundateText; //下次运行时间


    private Udinspo udinspo;


    private PopupWindow popupWindow;


    private TextView udinspoasset; //设备部件



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_udinspo_details);
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
        titleView = (TextView) findViewById(R.id.title_name);
        backImageView = (ImageView) findViewById(R.id.title_back_id);
        menuImageView = (ImageView) findViewById(R.id.title_add);

        insponumText = (TextView) findViewById(R.id.udinspo_insponum_text);
        descriptionText = (TextView) findViewById(R.id.udinspo_description_text);
        inspplannumText = (TextView) findViewById(R.id.udinspo_inspplannum_text);
        inspotypeText = (TextView) findViewById(R.id.udinspo_inspotype_text);
        createbyText = (TextView) findViewById(R.id.udinspo_createby_text);
        createdateText = (TextView) findViewById(R.id.udinspo_createdate_text);
        inspobyText = (TextView) findViewById(R.id.udinspo_inspoby_text);
        inspodateText = (TextView) findViewById(R.id.udinspo_inspodate_text);
        lastrundateText = (TextView) findViewById(R.id.udinspo_lastrundate_text);
        nextrundateText = (TextView) findViewById(R.id.udinspo_nextrundate_text);

    }

    @Override
    protected void initView() {
        titleView.setText(getString(R.string.udinspo_detail_title));
        backImageView.setOnClickListener(backImageViewOnClickListenrer);

        menuImageView.setImageResource(R.drawable.ic_drawer);
        menuImageView.setVisibility(View.VISIBLE);
        menuImageView.setOnClickListener(menuImageViewOnClickListener);


        if (udinspo != null) {
            insponumText.setText(udinspo.getInsponum() == null ? "" : udinspo.getInsponum());
            descriptionText.setText(udinspo.getDescription() == null ? "" : udinspo.getDescription());
            inspplannumText.setText(udinspo.getInspplannum() == null ? "" : udinspo.getInspplannum());
            inspotypeText.setText(udinspo.getInspotype() == null ? "" : udinspo.getInspotype());
            createbyText.setText(udinspo.getCreateby() == null ? "" : udinspo.getCreateby());
            createdateText.setText(udinspo.getCreatedate() == null ? "" : udinspo.getCreatedate());
            inspobyText.setText(udinspo.getInspoby() == null ? "" : udinspo.getInspoby());
            inspodateText.setText(udinspo.getInspodate() == null ? "" : udinspo.getInspodate());
            lastrundateText.setText(udinspo.getLastrundate() == null ? "" : udinspo.getLastrundate());
            nextrundateText.setText(udinspo.getNextrundate() == null ? "" : udinspo.getNextrundate());
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

        View contentView = LayoutInflater.from(Udinspo_Details_activity.this).inflate(
                R.layout.udinspo_popup_window, null);


        popupWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setTouchable(true);
        popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);

        popupWindow.setBackgroundDrawable(getResources().getDrawable(
                R.drawable.abc_popup_background_mtrl_mult));
        popupWindow.showAsDropDown(view, 0, 20);

        udinspoasset = (TextView) contentView.findViewById(R.id.udinspoasset_text_id);
        udinspoasset.setOnClickListener(udinspoassetOnClickListener);

    }


    private View.OnClickListener udinspoassetOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Udinspo_Details_activity.this, Udinspoasset_Activity.class);
            intent.putExtra("insponum", udinspo.insponum);
            startActivityForResult(intent, 0);
            popupWindow.dismiss();
        }
    };

}
