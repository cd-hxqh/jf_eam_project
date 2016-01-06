package com.jf_eam_project.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jf_eam_project.R;
import com.jf_eam_project.model.Assignment;

/**
 * Created by think on 2015/12/11.
 * 任务分配详情页面
 */
public class AssignmentDetailsActivity extends BaseActivity{
    private Assignment assignment;

    /**
     * 标题*
     */
    private TextView titlename;
    /**
     * 返回*
     */
    private ImageView backImageView;
    //员工、工种、任务时数
    private TextView taskid;//任务
    private TextView laborcode;//员工
    private TextView craft;//工种
    private TextView laborhrs;//时数

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment_details);

        geiIntentData();
        findViewById();
        initView();
    }

    /**
     * 获取数据*
     */
    private void geiIntentData() {
        assignment = (Assignment) getIntent().getSerializableExtra("assignment");
    }
    @Override
    protected void findViewById() {
        titlename = (TextView) findViewById(R.id.title_name);
        backImageView = (ImageView) findViewById(R.id.title_back_id);

        taskid = (TextView) findViewById(R.id.assignment_taskid);
        laborcode = (TextView) findViewById(R.id.assignment_laborcode);
        craft = (TextView) findViewById(R.id.assignment_craft);
        laborhrs = (TextView) findViewById(R.id.assignment_laborhrs);
    }

    @Override
    protected void initView() {
        titlename.setText(R.string.title_activity_work_assignment_details);
        backImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        taskid.setText(assignment.taskid);
        laborcode.setText(assignment.laborcode);
        craft.setText(assignment.craft);
        laborhrs.setText(assignment.laborhrs);
    }
}
