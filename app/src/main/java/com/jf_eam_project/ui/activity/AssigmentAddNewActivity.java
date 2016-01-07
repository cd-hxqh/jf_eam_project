package com.jf_eam_project.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.flyco.animation.BaseAnimatorSet;
import com.flyco.animation.BounceEnter.BounceTopEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.flyco.dialog.entity.DialogMenuItem;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.NormalListDialog;
import com.jf_eam_project.R;
import com.jf_eam_project.config.Constants;
import com.jf_eam_project.model.Assignment;
import com.jf_eam_project.model.Option;
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
    private EditText laborhrs;//时数
    private Button ok;//确定

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
        laborhrs = (EditText) findViewById(R.id.assignment_laborhrs);
        ok = (Button) findViewById(R.id.assignment_ok);
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
        taskid.setOnClickListener(taskidlayoutOnClickListener);
        laborcode.setOnClickListener(new LayoutOnClickListener(Constants.LABORCRAFTRATE));
        craft.setOnClickListener(new LayoutOnClickListener(Constants.CRAFTRATE));
        ok.setOnClickListener(okOnClickListener);
    }

    private View.OnClickListener okOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = getIntent();
            assignment = new Assignment();
            assignment.taskid = taskid.getText().toString();
            assignment.laborcode = laborcode.getText().toString();
            assignment.craft = craft.getText().toString();
            assignment.laborhrs = laborhrs.getText().toString();
            intent.putExtra("assignment",assignment);
            AssigmentAddNewActivity.this.setResult(1,intent);
            finish();
        }
    };

    private View.OnClickListener taskidlayoutOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            NormalListDialog();
        }
    };

    private void NormalListDialog() {
        final NormalListDialog dialog = new NormalListDialog(AssigmentAddNewActivity.this, mMenuItems);
        dialog.title("请选择")//
                .showAnim(mBasIn)//
                .dismissAnim(mBasOut)//
                .show();
        dialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {

                taskid.setText(mMenuItems.get(position).mOperName);
                dialog.dismiss();
            }
        });
    }

    private class LayoutOnClickListener implements View.OnClickListener {
        int requestCode;

        private LayoutOnClickListener(int requestCode) {
            this.requestCode = requestCode;
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(AssigmentAddNewActivity.this, OptionActivity.class);
            intent.putExtra("requestCode", requestCode);
            if(requestCode==Constants.LABORCRAFTRATE){
                intent.putExtra("craft", craft.getText().toString());
            }
            startActivityForResult(intent, requestCode);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Option option;
        switch (resultCode) {
            case Constants.LABORCRAFTRATE:
                option = (Option) data.getSerializableExtra("option");
                laborcode.setText(option.getName());
                craft.setText(option.getDescription());
                break;
            case Constants.CRAFTRATE:
                option = (Option) data.getSerializableExtra("option");
                craft.setText(option.getName());
        }
    }

    /**
     * 添加任务数据*
     */
    private void addTaskData() {
        for (int i = 0; i < woactivityList.size(); i++)
            mMenuItems.add(new DialogMenuItem(woactivityList.get(i).getTaskid(), 0));
    }
}
