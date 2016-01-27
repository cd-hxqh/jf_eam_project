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
 * Created by think on 2015/12/11.
 * 任务分配详情页面
 */
public class AssignmentDetailsActivity extends BaseActivity {
    private Assignment assignment;
    private int position;

    /**
     * 标题*
     */
    private TextView titlename;
    /**
     * 编辑*
     */
    private ImageView editImageView;
    /**
     * 返回*
     */
    private ImageView backImageView;
    //员工、工种、任务时数
//    private TextView taskid;//任务
    private TextView laborcode;//员工
    private TextView craft;//工种
    private EditText laborhrs;//时数
    private Button ok;//确定
    private Button delete;//删除

//    private ArrayList<Woactivity> woactivityList = new ArrayList<>();
//    private ArrayList<DialogMenuItem> mMenuItems = new ArrayList<>();
//    private BaseAnimatorSet mBasIn;
//    private BaseAnimatorSet mBasOut;

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
//        woactivityList = (ArrayList<Woactivity>) getIntent().getSerializableExtra("woactivityList");
        assignment = (Assignment) getIntent().getSerializableExtra("assignment");
        position = getIntent().getIntExtra("position", 0);
    }

    @Override
    protected void findViewById() {
        titlename = (TextView) findViewById(R.id.title_name);
        editImageView = (ImageView) findViewById(R.id.title_add);
        backImageView = (ImageView) findViewById(R.id.title_back_id);

//        taskid = (TextView) findViewById(R.id.assignment_taskid);
        laborcode = (TextView) findViewById(R.id.assignment_laborcode);
        craft = (TextView) findViewById(R.id.assignment_craft);
        laborhrs = (EditText) findViewById(R.id.assignment_laborhrs);
        ok = (Button) findViewById(R.id.assignment_ok);
        delete = (Button) findViewById(R.id.assignment_delete);
    }

    @Override
    protected void initView() {
        titlename.setText(R.string.title_activity_work_assignment_details);
        editImageView.setVisibility(View.VISIBLE);
        editImageView.setImageResource(R.drawable.edit_query);
        editImageView.setOnClickListener(editImageViewOnClickListener);
        backImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

//        taskid.setText(assignment.taskid);
        laborcode.setText(assignment.laborcode);
        craft.setText(assignment.craft);
        laborhrs.setText(assignment.laborhrs);

        laborcode.setEnabled(false);
        laborhrs.setFocusable(false);
        laborhrs.setFocusableInTouchMode(false);
        laborhrs.setEnabled(false);

//        mBasIn = new BounceTopEnter();
//        mBasOut = new SlideBottomExit();
//        addTaskData();
//        taskid.setOnClickListener(taskidlayoutOnClickListener);
        laborcode.setOnClickListener(new LayoutOnClickListener(Constants.LABORCRAFTRATE));
        craft.setOnClickListener(new LayoutOnClickListener(Constants.CRAFTRATE));
        ok.setOnClickListener(okOnClickListener);
        ok.setVisibility(View.GONE);
        delete.setOnClickListener(deleteOnClickListener);
    }

    private View.OnClickListener editImageViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ok.setVisibility(View.VISIBLE);
            delete.setVisibility(View.VISIBLE);
            statusEdit();
        }
    };

    /**
     * 设置状态编辑*
     */
    private void statusEdit() {
        laborcode.setEnabled(true);
        laborhrs.setFocusable(true);
        laborhrs.setFocusableInTouchMode(true);
        laborhrs.setEnabled(true);
    }

    private View.OnClickListener okOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = getIntent();
            if (!assignment.laborhrs.equals(laborhrs.getText().toString())
                    || !assignment.laborcode.equals(laborcode.getText().toString())
                    || !assignment.craft.equals(craft.getText().toString())) {
//                assignment.taskid = taskid.getText().toString();
                assignment.laborcode = laborcode.getText().toString();
                assignment.craft = craft.getText().toString();
                assignment.laborhrs = laborhrs.getText().toString();
                if (assignment.type == null || !assignment.type.equals("add")) {
                    assignment.type = "update";
                }
            }
            intent.putExtra("assignment", assignment);
            intent.putExtra("position", position);
            AssignmentDetailsActivity.this.setResult(-1, intent);
            finish();
        }
    };

    private View.OnClickListener deleteOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = getIntent();
            if(assignment.type!=null&&assignment.type.equals("add")){//本地新增任务
                intent.putExtra("position",position);
                AssignmentDetailsActivity.this.setResult(2, intent);
                finish();
            }else if (assignment.type==null){//服务器接收的任务
                assignment.type = "delete";
                intent.putExtra("assignment", assignment);
                intent.putExtra("position",position);
                AssignmentDetailsActivity.this.setResult(3,intent);
                finish();
            }
        }
    };

//    private View.OnClickListener taskidlayoutOnClickListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//            NormalListDialog();
//        }
//    };
//
//    private void NormalListDialog() {
//        final NormalListDialog dialog = new NormalListDialog(AssignmentDetailsActivity.this, mMenuItems);
//        dialog.title("请选择")//
//                .showAnim(mBasIn)//
//                .dismissAnim(mBasOut)//
//                .show();
//        dialog.setOnOperItemClickL(new OnOperItemClickL() {
//            @Override
//            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                taskid.setText(mMenuItems.get(position).mOperName);
//                dialog.dismiss();
//            }
//        });
//    }

    private class LayoutOnClickListener implements View.OnClickListener {
        int requestCode;

        private LayoutOnClickListener(int requestCode) {
            this.requestCode = requestCode;
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(AssignmentDetailsActivity.this, OptionActivity.class);
            intent.putExtra("requestCode", requestCode);
            if (requestCode == Constants.LABORCRAFTRATE) {
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

//    /**
//     * 添加任务数据*
//     */
//    private void addTaskData() {
//        if (woactivityList != null && woactivityList.size() != 0) {
//            for (int i = 0; i < woactivityList.size(); i++) {
//                mMenuItems.add(new DialogMenuItem(woactivityList.get(i).getTaskid(), 0));
//            }
//        }
//    }
}
