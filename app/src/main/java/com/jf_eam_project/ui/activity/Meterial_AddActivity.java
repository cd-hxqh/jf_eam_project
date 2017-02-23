package com.jf_eam_project.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jf_eam_project.R;
import com.jf_eam_project.utils.AccountUtils;
import com.jf_eam_project.utils.GetNowTime;

/**
 * Created by think on 2015/11/30.
 * 新增领料单界面
 */
public class Meterial_AddActivity extends BaseActivity {

    private static final String TAG = "Meterial_AddActivity";
    private TextView titlename; //标题
    private ImageView menuImageView; //菜单按钮
    private ImageView backlayout; //返回按钮

    private EditText description;//描述

    private TextView udwonumText; //关联工单号

    private TextView displaynameText; //申请人

    private TextView createdateText; //创建时间

    private TextView uddeptdesText; //分公司

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_add);

        findViewById();
        initView();
    }


    @Override
    protected void findViewById() {
        titlename = (TextView) findViewById(R.id.title_name);
        backlayout = (ImageView) findViewById(R.id.title_back_id);
        menuImageView = (ImageView) findViewById(R.id.title_add);

        description = (EditText) findViewById(R.id.description_text);
        udwonumText = (TextView) findViewById(R.id.udwonum_text_id);
        displaynameText = (TextView) findViewById(R.id.displayname_text_id);
        createdateText = (TextView) findViewById(R.id.createdate_text_id);
        uddeptdesText = (TextView) findViewById(R.id.uddeptdes_text_id);
    }

    @Override
    protected void initView() {
        titlename.setText(R.string.meterial_title);
//        menuImageView.setImageResource(R.drawable.ic_drawer);
//        menuImageView.setVisibility(View.VISIBLE);
//        menuImageView.setOnClickListener(menuImageViewOnClickListener);
        backlayout.setOnClickListener(backlayoutOnClickListener);
        displaynameText.setText(AccountUtils.getDisplayName(Meterial_AddActivity.this));
        createdateText.setText(GetNowTime.getTime());
        uddeptdesText.setText(AccountUtils.getDepartmentms(Meterial_AddActivity.this));

    }


    private View.OnClickListener backlayoutOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            finish();
        }
    };
}
