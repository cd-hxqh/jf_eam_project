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

/**
 * 物资申请编码详情
 */
public class Uditemreq_Details_Activity extends BaseActivity {

    private static final String TAG = "Uditemreq_Details_Activity";

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
    private TextView uditemreqnumText; //申请单号
    private TextView descriptionText; //描述
    private TextView reasonText; //申请原因
    private TextView adescriptionText; //状态
    private TextView branchText; //分公司
    private TextView udbelongText; //风电场
    private TextView person_displaynameText; //创建人
    private TextView createdateText; //创建日期


    private Uditemreq uditemreq; //物资编码申请

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
        setContentView(R.layout.activity_uditemreq_details);
        initData();
        findViewById();
        initView();
    }

    /**
     * 获取初始话数据*
     */
    private void initData() {
        uditemreq = (Uditemreq) getIntent().getSerializableExtra("uditemreq");
    }

    @Override
    protected void findViewById() {
        titleView = (TextView) findViewById(R.id.title_name);
        backImageView = (ImageView) findViewById(R.id.title_back_id);
        menuImageView = (ImageView) findViewById(R.id.title_add);

        uditemreqnumText = (TextView) findViewById(R.id.uditemreqnum_text_id);
        descriptionText = (TextView) findViewById(R.id.desc_text_id);
        reasonText = (TextView) findViewById(R.id.reason_text_id);
        adescriptionText = (TextView) findViewById(R.id.alndomain_description_text_id);
        branchText = (TextView) findViewById(R.id.description_text_id);
        udbelongText = (TextView) findViewById(R.id.uddept_description_text_id);
        person_displaynameText = (TextView) findViewById(R.id.person_displayname_text_id);
        createdateText = (TextView) findViewById(R.id.createdate_text_id);

    }

    @Override
    protected void initView() {
        titleView.setText(getString(R.string.uditemreq_detail_text));
        backImageView.setOnClickListener(backImageViewOnClickListenrer);

        menuImageView.setImageResource(R.drawable.ic_drawer);
        menuImageView.setVisibility(View.VISIBLE);
        menuImageView.setOnClickListener(menuImageViewOnClickListener);


        if (uditemreq != null) {
            uditemreqnumText.setText(uditemreq.uditemreqnum == null ? "" : uditemreq.uditemreqnum);
            descriptionText.setText(uditemreq.description == null ? "" : uditemreq.description);
            reasonText.setText(uditemreq.reason == null ? "" : uditemreq.reason);
            adescriptionText.setText(uditemreq.alndomain_description == null ? "" : uditemreq.alndomain_description);
            branchText.setText(uditemreq.branch == null ? "" : uditemreq.branch);
            udbelongText.setText(uditemreq == null ? "" : uditemreq.udbelong);
            person_displaynameText.setText(uditemreq.person_displayname == null ? "" : uditemreq.person_displayname);
            createdateText.setText(uditemreq.createdate == null ? "" : uditemreq.createdate);
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

        View contentView = LayoutInflater.from(Uditemreq_Details_Activity.this).inflate(
                R.layout.material_popup_window, null);


        popupWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setTouchable(true);
        popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);

        popupWindow.setBackgroundDrawable(getResources().getDrawable(
                R.drawable.abc_popup_background_mtrl_mult));
        popupWindow.showAsDropDown(view, 0, 20);

        wpmaterialText = (TextView) contentView.findViewById(R.id.wpmaterial_popup_text_id);
//        wpmaterialText.setOnClickListener(wpmaterialTextOnClickListener);

    }

//    private View.OnClickListener wpmaterialTextOnClickListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//            Intent intent = new Intent(Uditemreq_Details_Activity.this, Wpmaterial_ListActivity.class);
//            intent.putExtra("wonum", workOrder.getWonum());
//            startActivityForResult(intent, 0);
//            popupWindow.dismiss();
//        }
//    };

}
