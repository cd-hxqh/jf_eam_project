package com.jf_eam_project.ui.activity;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.TimePicker;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flyco.animation.BaseAnimatorSet;
import com.flyco.animation.BounceEnter.BounceTopEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.flyco.dialog.entity.DialogMenuItem;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.NormalListDialog;
import com.jf_eam_project.Dao.UdinspoDao;
import com.jf_eam_project.Dao.UdinspojxxmDao;
import com.jf_eam_project.R;
import com.jf_eam_project.config.Constants;
import com.jf_eam_project.model.Assets;
import com.jf_eam_project.model.Option;
import com.jf_eam_project.model.Udinspo;
import com.jf_eam_project.model.Udinspoasset;
import com.jf_eam_project.model.Udinspojxxm;
import com.jf_eam_project.model.WorkOrder;
import com.jf_eam_project.ui.widget.CumTimePickerDialog;
import com.jf_eam_project.utils.AccountUtils;
import com.jf_eam_project.utils.GetNowTime;
import com.jf_eam_project.utils.MessageUtils;
import com.jf_eam_project.utils.NetWorkHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * 资产详情
 */
public class AssetsDetailActivity extends BaseActivity {

    private static final String TAG = "AssetsDetailActivity";

    /**
     * 标题*
     */
    private TextView titleView;
    /**
     * 返回按钮*
     */
    private ImageView backImageView;

    /**界面说明**/

    /**
     * 资产编号*
     */
    private TextView assetnumText;
    /**
     * 资产描述*
     */
    private TextView descriptionText;
    /**
     * 地点*
     */
    private TextView siteidText;
    /**
     * 位置*
     */
    private TextView locationText;



    private ProgressDialog mProgressDialog;

    /**Asset**/
    private Assets assets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assets_detail);
        initData();
        findViewById();
        initView();
//        showProgressDialog("正在加载...");

    }

    private void initData() {
        assets = (Assets) getIntent().getSerializableExtra("assets");
    }


    @Override
    protected void findViewById() {
        titleView = (TextView) findViewById(R.id.title_name);
        backImageView = (ImageView) findViewById(R.id.title_back_id);


        assetnumText = (TextView) findViewById(R.id.asset_num_text_id);
        descriptionText = (TextView) findViewById(R.id.asset_description_text_id);
        siteidText = (TextView) findViewById(R.id.asset_sited_text_id);
        locationText = (TextView) findViewById(R.id.asset_loction_text_id);

    }

    @Override
    protected void initView() {
        titleView.setText(getString(R.string.assets_title_text));
        backImageView.setOnClickListener(backImageViewOnClickListenrer);


        if(assets!=null){
            assetnumText.setText(assets.getAssetnum() == null ? "" : assets.getAssetnum());
            descriptionText.setText(assets.getDescription() == null ? "" : assets.getDescription());
            siteidText.setText(assets.getSiteid() == null ? "" : assets.getSiteid());
            locationText.setText(assets.getLocation() == null ? "" : assets.getLocation());
        }


    }

    private View.OnClickListener backImageViewOnClickListenrer = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            finish();
        }
    };

}
