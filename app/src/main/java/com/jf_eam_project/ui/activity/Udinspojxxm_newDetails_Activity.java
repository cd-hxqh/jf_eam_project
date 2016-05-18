package com.jf_eam_project.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.jf_eam_project.R;
import com.jf_eam_project.model.Udinspojxxm;

import java.util.ArrayList;
import java.util.List;

/**
 * 检修项目标准
 */
public class Udinspojxxm_newDetails_Activity extends BaseActivity {

    private static final String TAG = "Udinspojxxm_newDetails_Activity";

    /**
     * 标题*
     */
    private TextView titleView;
    /**
     * 返回按钮*
     */
    private ImageView backImageView;

    private List<Udinspojxxm> list=new ArrayList<Udinspojxxm>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_newudinspojxxm);
        initData();
        findViewById();
        initView();
    }

    /**
     * 获取初始话数据*
     */
    private void initData() {

    }

    @Override
    protected void findViewById() {
        titleView = (TextView) findViewById(R.id.title_name);
        backImageView = (ImageView) findViewById(R.id.title_back_id);


    }


    @Override
    protected void initView() {
        titleView.setText(getString(R.string.udinspojxxm_detail_title));
        backImageView.setOnClickListener(backImageViewOnClickListenrer);
//

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


}
