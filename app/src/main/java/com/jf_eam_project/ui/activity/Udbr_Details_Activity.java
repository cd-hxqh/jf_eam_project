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
import com.jf_eam_project.model.Udbr;
import com.jf_eam_project.model.Uditemreq;

/**
 * 物资借用归还详情
 */
public class Udbr_Details_Activity extends BaseActivity {

    private static final String TAG = "Udbr_Details_Activity";

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
    private TextView udbrnumText; //编号
    private TextView descriptionText; //描述
    private TextView branchText; //分公司
    private TextView udbelongText; //风电场
    private TextView displaynameText; //借用人
    private TextView createdateText; //借用日期
    private TextView frombranchText; //借出分公司
    private TextView frombelongText; // 借出风电场
    private TextView statusText; // 状态


    private Udbr udbr; //物资借用归还

    private PopupWindow popupWindow;

    /**
     * 申请行表
     */
    private TextView udbrlineText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_udbr_details);
        initData();
        findViewById();
        initView();
    }

    /**
     * 获取初始话数据*
     */
    private void initData() {
        udbr = (Udbr) getIntent().getSerializableExtra("udbr");
    }

    @Override
    protected void findViewById() {
        titleView = (TextView) findViewById(R.id.title_name);
        backImageView = (ImageView) findViewById(R.id.title_back_id);
        menuImageView = (ImageView) findViewById(R.id.title_add);

        udbrnumText = (TextView) findViewById(R.id.udbrnum_text_id);
        descriptionText = (TextView) findViewById(R.id.description_text_id);
        branchText = (TextView) findViewById(R.id.branch_text_id);
        udbelongText = (TextView) findViewById(R.id.udbelong_text_id);
        displaynameText = (TextView) findViewById(R.id.displayname_text_id);
        createdateText = (TextView) findViewById(R.id.createdate_text_id);
        frombranchText = (TextView) findViewById(R.id.frombranch_text_id);
        frombelongText = (TextView) findViewById(R.id.frombelong_text_id);
        statusText = (TextView) findViewById(R.id.status_text_id);

    }

    @Override
    protected void initView() {
        titleView.setText(getString(R.string.udbr_details_text));
        backImageView.setOnClickListener(backImageViewOnClickListenrer);

        menuImageView.setImageResource(R.drawable.ic_drawer);
        menuImageView.setVisibility(View.VISIBLE);
        menuImageView.setOnClickListener(menuImageViewOnClickListener);


        if (udbr != null) {
            udbrnumText.setText(udbr.udbrnum == null ? "" : udbr.udbrnum);
            descriptionText.setText(udbr.description == null ? "" : udbr.description);
            branchText.setText(udbr.branch == null ? "" : udbr.branch);
            udbelongText.setText(udbr.udbelong == null ? "" : udbr.udbelong);
            displaynameText.setText(udbr.person_displayname == null ? "" : udbr.person_displayname);
            createdateText.setText(udbr.createdate == null ? "" : udbr.createdate);
            frombranchText.setText(udbr.frombranch == null ? "" : udbr.frombranch);
            frombelongText.setText(udbr.frombelong == null ? "" : udbr.frombelong);
            statusText.setText(udbr.status == null ? "" : udbr.frombelong);
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

        View contentView = LayoutInflater.from(Udbr_Details_Activity.this).inflate(
                R.layout.material_popup_window, null);


        popupWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setTouchable(true);
        popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);

        popupWindow.setBackgroundDrawable(getResources().getDrawable(
                R.drawable.abc_popup_background_mtrl_mult));
        popupWindow.showAsDropDown(view, 0, 20);

        udbrlineText = (TextView) contentView.findViewById(R.id.wpmaterial_popup_text_id);
        udbrlineText.setText(getString(R.string.udbrline_details_text));
        udbrlineText.setOnClickListener(uditemreqlineTextOnClickListener);

    }

    private View.OnClickListener uditemreqlineTextOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Udbr_Details_Activity.this, UdbrLine_ListActivity.class);
            intent.putExtra("udbrnum", udbr.udbrnum);
            startActivityForResult(intent, 0);
            popupWindow.dismiss();
        }
    };

}
