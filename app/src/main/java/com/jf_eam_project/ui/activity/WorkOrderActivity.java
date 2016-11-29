package com.jf_eam_project.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jf_eam_project.R;
import com.jf_eam_project.config.Constants;

/**
 * 工单管理界面
 */

public class WorkOrderActivity extends BaseActivity {

    private static final String TAG = "WorkOrderActivity";
    /**
     * 标题*
     */
    private TextView titleText;
    /**
     * 返回按钮
     **/
    private ImageView backImageView;

    /**
     * 计划工单
     **/
    private LinearLayout plan_layout;
    /**
     * 非计划工单
     **/
    private LinearLayout not_plan_layout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_work);

        findViewById();

        initView();

    }

    @Override
    protected void findViewById() {
        titleText = (TextView) findViewById(R.id.title_name);
        backImageView = (ImageView) findViewById(R.id.title_back_id);

        plan_layout = (LinearLayout) findViewById(R.id.work_linear_plan_id);
        not_plan_layout = (LinearLayout) findViewById(R.id.work_linear_not_plan_id);
    }

    @Override
    protected void initView() {
        titleText.setText(getString(R.string.title_activity_work_list));
        backImageView.setOnClickListener(backImageViewOnClickListener);
        plan_layout.setOnClickListener(plan_layoutOnClickListener);
        not_plan_layout.setOnClickListener(not_plan_layoutOnClickListener);
    }


    private View.OnClickListener backImageViewOnClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            finish();
        }
    };







    //计划工单
    private View.OnClickListener plan_layoutOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(WorkOrderActivity.this, Work_ListActivity.class);
            intent.putExtra("worktype", Constants.PLAN);
            startActivityForResult(intent,0);
        }
    };
    //非计划工单
    private View.OnClickListener not_plan_layoutOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(WorkOrderActivity.this, Work_ListActivity.class);
            intent.putExtra("worktype", Constants.UNPLAN);
            startActivityForResult(intent,0);
        }
    };


}
