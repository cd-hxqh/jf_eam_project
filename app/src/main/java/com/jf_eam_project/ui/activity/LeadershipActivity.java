package com.jf_eam_project.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jf_eam_project.R;

/**
 * 领导界面
 */

public class LeadershipActivity extends BaseActivity {

    private static final String TAG = "LeadershipActivity";
    /**
     * 标题*
     */
    private TextView titleText;
    /**
     * 返回按钮
     **/
    private ImageView backImageView;

    /**
     * 电量统计
     **/
    private TextView electricityTextView;
    /**
     * 故障统计
     **/
    private TextView gzTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leadership);

        findViewById();

        initView();

    }

    @Override
    protected void findViewById() {
        titleText = (TextView) findViewById(R.id.title_name);
        backImageView = (ImageView) findViewById(R.id.title_back_id);

        electricityTextView = (TextView) findViewById(R.id.electricity_text_id);
        gzTextView = (TextView) findViewById(R.id.gz_text_id);
    }

    @Override
    protected void initView() {
        titleText.setText(getString(R.string.kpi_text));
        backImageView.setOnClickListener(backImageViewOnClickListener);
        electricityTextView.setOnClickListener(electricityTextViewOnClickListener);
        gzTextView.setOnClickListener(gzTextViewOnClickListener);
    }


    private View.OnClickListener backImageViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            finish();
        }
    };


    //电量统计
    private View.OnClickListener electricityTextViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent();
            intent.setClass(LeadershipActivity.this, ElectricityActivity.class);
            startActivityForResult(intent, 0);
        }
    };
    //故障统计
    private View.OnClickListener gzTextViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent();
            intent.setClass(LeadershipActivity.this, GzStatisticalActivity.class);
            startActivityForResult(intent, 0);

        }
    };


}
