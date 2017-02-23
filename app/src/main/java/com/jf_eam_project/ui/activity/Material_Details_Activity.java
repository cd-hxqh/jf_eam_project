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
import com.jf_eam_project.model.WorkOrder;

/**
 * 领料单详情
 */
public class Material_Details_Activity extends BaseActivity {

    private static final String TAG = "Material_Details_Activity";

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
    private TextView wonumText; //领料单号
    private TextView descriptionText; //描述
    private TextView udwonumText; //关联工单号
    private TextView displaynameText; //申请人
    private TextView createdateText; //创建时间
    private TextView udescriptionText; //分公司
    private TextView udescription1Text; //风电场
    private TextView statusText; //状态


    private WorkOrder workOrder; //领料单

    private PopupWindow popupWindow;

    /**
     * 物料*
     */
    private TextView wpmaterialText;
    /**
     * 工具*
     */
    private TextView wptooltext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_details);
        initData();
        findViewById();
        initView();
    }

    /**
     * 获取初始话数据*
     */
    private void initData() {
        workOrder = (WorkOrder) getIntent().getSerializableExtra("workOrder");
    }

    @Override
    protected void findViewById() {
        titleView = (TextView) findViewById(R.id.title_name);
        backImageView = (ImageView) findViewById(R.id.title_back_id);
        menuImageView = (ImageView) findViewById(R.id.title_add);

        wonumText = (TextView) findViewById(R.id.material_title_text_id);
        descriptionText = (TextView) findViewById(R.id.inventory_description_text);
        udwonumText = (TextView) findViewById(R.id.udwonum_text_id);
        displaynameText = (TextView) findViewById(R.id.material_displayname_text_id);
        createdateText = (TextView) findViewById(R.id.createdate_text_id);
        udescriptionText = (TextView) findViewById(R.id.description_text_id);
        udescription1Text = (TextView) findViewById(R.id.uddept_description_text_id);
        statusText = (TextView) findViewById(R.id.state_text_id);

    }

    @Override
    protected void initView() {
        titleView.setText(getString(R.string.material_detail_text));
        backImageView.setOnClickListener(backImageViewOnClickListenrer);

        menuImageView.setImageResource(R.drawable.ic_drawer);
        menuImageView.setVisibility(View.VISIBLE);
        menuImageView.setOnClickListener(menuImageViewOnClickListener);


        if (workOrder != null) {
            wonumText.setText(workOrder.getWonum() == null ? "" : workOrder.getWonum());
            descriptionText.setText(workOrder.getDescription() == null ? "" : workOrder.getDescription());
            udwonumText.setText(workOrder.getUdwonum() == null ? "" : workOrder.getUdwonum());
            displaynameText.setText(workOrder.getReportedby() == null ? "" : workOrder.getReportedby());
            createdateText.setText(workOrder.getCreatedate() == null ? "" : workOrder.getCreatedate());
            udescriptionText.setText(workOrder.getUddeptdescription() == null ? "" : workOrder.getUddeptdescription());
            udescription1Text.setText(workOrder.getUdbelong() == null ? "" : workOrder.getUdbelong());
            statusText.setText(workOrder.getStatus() == null ? "" : workOrder.getStatus());
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
    private void showPopupWindow(View view) {

        View contentView = LayoutInflater.from(Material_Details_Activity.this).inflate(
                R.layout.material_popup_window, null);


        popupWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setTouchable(true);
        popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);

        popupWindow.setBackgroundDrawable(getResources().getDrawable(
                R.drawable.abc_popup_background_mtrl_mult));
        popupWindow.showAsDropDown(view, 0, 20);

        wpmaterialText = (TextView) contentView.findViewById(R.id.wpmaterial_popup_text_id);
        wpmaterialText.setOnClickListener(wpmaterialTextOnClickListener);

    }

    private View.OnClickListener wpmaterialTextOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Material_Details_Activity.this, Wpmaterial_ListActivity.class);
            intent.putExtra("wonum", workOrder.getWonum());
            startActivityForResult(intent, 0);
            popupWindow.dismiss();
        }
    };

}
