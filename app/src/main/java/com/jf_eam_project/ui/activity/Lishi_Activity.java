package com.jf_eam_project.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jf_eam_project.R;

/**
 * 本地历史
 */

public class Lishi_Activity extends BaseActivity {

    private static final String TAG = "Lishi_Activity";
    /**
     * 标题*
     */
    private TextView titleText;
    /**
     * 返回按钮
     **/
    private ImageView backImageView;

    /**
     * 工单*
     */
    private LinearLayout workorder_layout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_lishi);

        findViewById();

        initView();

    }

    @Override
    protected void findViewById() {
        titleText = (TextView) findViewById(R.id.title_name);
        backImageView = (ImageView) findViewById(R.id.title_back_id);

        workorder_layout = (LinearLayout) findViewById(R.id.work_order_linearlayout_id);
    }

    @Override
    protected void initView() {
//        titleText.setText(getString(R.string.bdls_text));
        backImageView.setOnClickListener(backImageViewOnClickListener);
        workorder_layout.setOnClickListener(workorderOnClickListener);
    }


    private View.OnClickListener workorderOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Lishi_Activity.this, Work_History_ListActivity.class);
            startActivityForResult(intent, 1);
        }
    };


    private View.OnClickListener backImageViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            finish();
        }
    };

}
