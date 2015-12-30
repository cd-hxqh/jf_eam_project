package com.jf_eam_project.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jf_eam_project.R;

/**
 * 巡检单新增
 */
public class AddUdinspoActivity extends BaseActivity {


    /**
     * 标题*
     */
    private TextView titleView;
    /**
     * 返回按钮*
     */
    private ImageView backImageView;

    /**
     * 新增界面说明*
     */

    private EditText descEditText; //描述
    private TextView inspotypeText; //巡检单类型
    private TextView createbyText; //创建人
    private TextView createdateText; //创建时间
    private TextView inspobyText; //巡检人
    private TextView inspodateText; //巡检日期


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_udinspo);
        findViewById();
        initView();
    }

    @Override
    protected void findViewById() {
        titleView = (TextView) findViewById(R.id.title_name);
        backImageView = (ImageView) findViewById(R.id.title_back_id);

    }

    @Override
    protected void initView() {
        titleView.setText(getString(R.string.udinspo_detail_title));
        backImageView.setOnClickListener(backImageViewOnClickListenrer);
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
