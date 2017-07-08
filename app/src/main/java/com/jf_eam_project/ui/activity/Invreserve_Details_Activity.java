package com.jf_eam_project.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.jf_eam_project.R;
import com.jf_eam_project.model.Locations;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 物资发放
 */
public class Invreserve_Details_Activity extends BaseActivity {

    private static final String TAG = "Invreserve_Details_Activity";

    @Bind(R.id.title_name)
    TextView titleView;//标题
    @Bind(R.id.title_back_id)
    ImageView backImageView;//返回按钮
    @Bind(R.id.title_add)
    ImageView submitBtn; //提交按钮


    /**
     * 界面信息显示*
     */
    @Bind(R.id.location_text_id)
    TextView locationText; //库房
    @Bind(R.id.location_name_text_id)
    TextView descriptionText; //库房名称
    @Bind(R.id.description_text_id)
    TextView branchText; //分公司
    @Bind(R.id.uddeptdes_text_id)
    TextView uddeptdesText; //运行单位
    @Bind(R.id.siteid_text_id)
    TextView siteidText; //地点

    @Bind(R.id.choose_btn_id)
    Button chooseBtn;//选择预留项目


    private Locations locations; //物资发放


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invreserve_details);
        ButterKnife.bind(this);
        initData();
        findViewById();
        initView();
    }

    /**
     * 获取初始话数据*
     */
    private void initData() {
        locations = (Locations) getIntent().getSerializableExtra("locations");
    }

    @Override
    protected void findViewById() {

    }

    @Override
    protected void initView() {
        titleView.setText(R.string.ffxq_text);
        submitBtn.setVisibility(View.VISIBLE);
        submitBtn.setImageResource(R.drawable.ic_sbmit);

        if (locations != null) {
            locationText.setText(locations.location == null ? "" : locations.location);
            descriptionText.setText(locations.description == null ? "" : locations.description);
            branchText.setText(locations.branch == null ? "" : locations.branch);
            uddeptdesText.setText(locations.udbelong == null ? "" : locations.udbelong);
            siteidText.setText(locations.siteid == null ? "" : locations.siteid);
        }


    }

    //返回按钮
    @OnClick(R.id.title_back_id)
    void setBackImageViewOnClickListenrer() {
        finish();
    }

    //选择预留项目
    @OnClick(R.id.choose_btn_id)
    void setChooseBtnOnClickListener() {
        Intent intent = new Intent(Invreserve_Details_Activity.this, Invreserve_Choose_Activity.class);
        intent.putExtra("location", locations.location);
        startActivityForResult(intent, 0);
    }
}
