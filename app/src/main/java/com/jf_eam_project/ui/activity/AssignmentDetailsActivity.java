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

    private TextView taskid;//任务
    private TextView laborcode;//员工
    private TextView craft;//工种
    private TextView amcrewtype;//班组类型
    private TextView amcrew;//班组
    private TextView skilllevel;//技能级别
    private TextView vendor;//供应商
    private TextView contractnum;//合同
    private TextView crewworkgroup;//班组工作组
    private TextView scheduledate;//调度开始时间
    private TextView laborhrs;//时数
    private TextView status;//状态

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
        amcrewtype = (TextView) findViewById(R.id.assignment_amcrewtype);
        amcrew = (TextView) findViewById(R.id.assignment_amcrew);
        skilllevel = (TextView) findViewById(R.id.assignment_skilllevel);
        vendor = (TextView) findViewById(R.id.assignment_vendor);
        contractnum = (TextView) findViewById(R.id.assignment_contractnum);
        crewworkgroup = (TextView) findViewById(R.id.assignment_crewworkgroup);
        scheduledate = (TextView) findViewById(R.id.assignment_scheduledate);
        laborhrs = (TextView) findViewById(R.id.assignment_laborhrs);
        status = (TextView) findViewById(R.id.assignment_status);
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
        amcrewtype.setText(assignment.amcrewtype);
        amcrew.setText(assignment.amcrew);
        skilllevel.setText(assignment.skilllevel);
        vendor.setText(assignment.vendor);
        contractnum.setText(assignment.contractnum);
        crewworkgroup.setText(assignment.crewworkgroup);
        scheduledate.setText(assignment.scheduledate);
        laborhrs.setText(assignment.laborhrs);
        status.setText(assignment.status);
    }
}
