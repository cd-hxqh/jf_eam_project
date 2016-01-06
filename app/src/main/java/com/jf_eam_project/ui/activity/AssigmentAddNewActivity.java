package com.jf_eam_project.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.flyco.animation.BaseAnimatorSet;
import com.flyco.animation.BounceEnter.BounceTopEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.flyco.dialog.entity.DialogMenuItem;
import com.jf_eam_project.R;
import com.jf_eam_project.model.Assignment;
import com.jf_eam_project.model.Woactivity;

import java.util.ArrayList;

/**
 * Created by think on 2016/1/6.
 * 新增任务分配页面
 */
public class AssigmentAddNewActivity extends BaseActivity{
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

    private ArrayList<Woactivity> woactivityList = new ArrayList<>();
    private ArrayList<DialogMenuItem> mMenuItems = new ArrayList<>();
    private BaseAnimatorSet mBasIn;
    private BaseAnimatorSet mBasOut;
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
        woactivityList = (ArrayList<Woactivity>) getIntent().getSerializableExtra("woactivityList");
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
        titlename.setText(R.string.title_activity_work_assignment_addnew);
        backImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mBasIn = new BounceTopEnter();
        mBasOut = new SlideBottomExit();
        addTaskData();
    }

    /**
     * 添加任务数据*
     */
    private void addTaskData() {
        for (int i = 0; i < woactivityList.size(); i++)
            mMenuItems.add(new DialogMenuItem(woactivityList.get(i).getTaskid(), 0));
    }
}
