package com.jf_eam_project.ui.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.jf_eam_project.Dao.LocationDao;
import com.jf_eam_project.Dao.UdinspoAssetDao;
import com.jf_eam_project.R;
import com.jf_eam_project.config.Constants;
import com.jf_eam_project.model.Option;
import com.jf_eam_project.model.PR;
import com.jf_eam_project.model.Udinspoasset;
import com.jf_eam_project.model.Woactivity;
import com.jf_eam_project.model.Wplabor;
import com.jf_eam_project.model.Wpmaterial;

import java.util.ArrayList;
import java.util.Random;

/**
 * 设备备件新增
 */
public class AddUdinspoAssetActivity extends BaseActivity {

    private static final String TAG = "AddUdinspoAssetActivity";

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
    private TextView udinspoassetnumText; //设备编号
    private TextView locationText; //位置
    private TextView locationDescText; //位置描述
    private TextView assetnumText; //设备
    private TextView assetnumDescText; //设备描述
    private EditText childassetnumText; //设备部件


    private Udinspoasset udinspoasset = null; //设备备件


    private PopupWindow popupWindow;

    private TextView udinspojxxm; //检修项目标准


    /**
     * 保存按钮*
     */
    private Button submitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_udinspoasset_add);
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

        udinspoassetnumText = (TextView) findViewById(R.id.ud_udinspoassetnum_text);
        locationText = (TextView) findViewById(R.id.udinspoasset_location_text);
        locationDescText = (TextView) findViewById(R.id.udinspoasset_location_description_text);
        assetnumText = (TextView) findViewById(R.id.udinspoasset_assetnum_text);
        assetnumDescText = (TextView) findViewById(R.id.udinspoasset_assetnum_desc_text);
        childassetnumText = (EditText) findViewById(R.id.udinspoasset_childassetnum_text);

        submitBtn = (Button) findViewById(R.id.submit_btn_id);

    }


    @Override
    protected void initView() {
        titleView.setText(getResources().getString(R.string.add_udinspoasset_title));
        backImageView.setOnClickListener(backImageViewOnClickListenrer);

        menuImageView.setImageResource(R.drawable.ic_drawer);
        menuImageView.setVisibility(View.VISIBLE);
        menuImageView.setOnClickListener(menuImageViewOnClickListener);

        udinspoassetnumText.setText("SC" + getRandomNumber(4));

        locationText.setOnClickListener(locationOnClickListener);
        assetnumText.setOnClickListener(assetnumOnClickListener);


        submitBtn.setOnClickListener(submitBtnOnClickListener);

    }

    /**
     * 位置选择*
     */
    private View.OnClickListener locationOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(AddUdinspoAssetActivity.this, OptionActivity.class);
            intent.putExtra("requestCode", Constants.LOCATIONCODE);
            startActivityForResult(intent, Constants.LOCATIONCODE);
        }
    };

    /**
     * 资产选择*
     */
    private View.OnClickListener assetnumOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(AddUdinspoAssetActivity.this, OptionActivity.class);
            intent.putExtra("requestCode", Constants.ASSETCODE);
            startActivityForResult(intent, Constants.ASSETCODE);
        }
    };


    public static int getRandomNumber(int n) {
        int temp = 0;
        int min = (int) Math.pow(10, n - 1);
        int max = (int) Math.pow(10, n);
        Random rand = new Random();

        while (true) {
            temp = rand.nextInt(max);
            if (temp >= min)
                break;
        }

        return temp;
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

        View contentView = LayoutInflater.from(AddUdinspoAssetActivity.this).inflate(
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
            Intent intent = new Intent(AddUdinspoAssetActivity.this, Udinspojxxm_Activity.class);
            intent.putExtra("udinspoassetnum", udinspoasset.udinspoassetnum);
            startActivityForResult(intent, 0);
            popupWindow.dismiss();
        }
    };


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Option option;
        switch (resultCode) {
            case Constants.ASSETCODE:
                option = (Option) data.getSerializableExtra("option");
                assetnumText.setText(option.getName());
                assetnumDescText.setText(option.getDescription());
                break;
            case Constants.LOCATIONCODE:
                option = (Option) data.getSerializableExtra("option");
                locationText.setText(option.getName());
                locationDescText.setText(option.getDescription());
                break;

            default:
                break;
        }
    }

    /**
     * 保存方法*
     */
    private View.OnClickListener submitBtnOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Udinspoasset udinspoasset = addUdinspoInfo();

            Intent intent = getIntent();
            intent.putExtra("udinspoasset", udinspoasset);
            setResult(0, intent);
            finish();
        }
    };


    /**
     * 将数据保存至本地数据库*
     */

    private Udinspoasset addUdinspoInfo() {
        Udinspoasset udinspoasset = new Udinspoasset();

        udinspoasset.setUdinspoassetnum(udinspoassetnumText.getText().toString());
        udinspoasset.setLocation(locationText.getText().toString());
        udinspoasset.setLocationsdesc(locationDescText.getText().toString());
        udinspoasset.setAssetnum(assetnumText.getText().toString());
        udinspoasset.setAssetdesc(assetnumDescText.getText().toString());
        udinspoasset.setChildassetnum(childassetnumText.getText().toString());


        new UdinspoAssetDao(AddUdinspoAssetActivity.this).insert(udinspoasset);

        return udinspoasset;
    }


}
