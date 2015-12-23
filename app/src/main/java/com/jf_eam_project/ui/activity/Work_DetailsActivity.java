package com.jf_eam_project.ui.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jf_eam_project.R;
import com.jf_eam_project.config.Constants;
import com.jf_eam_project.model.WorkOrder;
import com.jf_eam_project.model.Wplabor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by think on 2015/11/27.
 * 工单详情页面
 */
public class Work_DetailsActivity extends BaseActivity {
    private WorkOrder workOrder;

    /**
     * 标题*
     */
    private TextView titlename;
    /**
     * 菜单按钮*
     */
    private ImageView menuImageView;
    /**
     * 返回*
     */
    private ImageView backImageView;
    private PopupWindow popupWindow;
    /**
     * 计划员工*
     */
    private LinearLayout planLinearlayout;
    private ArrayList<Wplabor> wplaborList = new ArrayList<>();
    /**
     * 任务分配*
     */
    private LinearLayout taskLinearLayout;
    /**
     * 实际情况
     */
    private LinearLayout realinfoLinearLayout;

    private TextView wonum;//工单号
    private TextView description;//描述
    private TextView parent;//父工单
    private TextView udwotype; //工单类型
    private TextView udprojectnum; //项目编号
    private TextView uudprojectnumdesc; //项目编号描述
    private TextView assetnum;//资产编号
    private TextView assetdesc;//资产描述
    private TextView location; //位置
    private TextView locationdesc;//位置描述
    private TextView status; //状态
    private TextView statusdate; //状态日期
    private TextView lctype; //风机/电气
    private TextView woclass; //类
    private TextView failurecode; //故障类
    private TextView problemcode; //问题代码
    private TextView displayname; //创建人
    private TextView createdate; //创建时间

    private TextView jpnum; //作业计划
    private TextView targstartdate;//计划开始时间
    private TextView targcompdate;//计划完成时间
    private TextView actstart;//实际开始时间
    private TextView actfinish;//实际完成时间
    private TextView reportedby; //报告人
    private TextView reportdate; //汇报日期


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workdetails);

        geiIntentData();
        findViewById();
        initView();
    }

    /**
     * 获取数据*
     */
    private void geiIntentData() {
        workOrder = (WorkOrder) getIntent().getSerializableExtra("workOrder");
    }

    @Override
    protected void findViewById() {
        titlename = (TextView) findViewById(R.id.title_name);
        menuImageView = (ImageView) findViewById(R.id.title_add);
        backImageView = (ImageView) findViewById(R.id.title_back_id);

        wonum = (TextView) findViewById(R.id.work_wonum);
        description = (TextView) findViewById(R.id.work_desc);
        parent = (TextView) findViewById(R.id.work_parent);
        udwotype = (TextView) findViewById(R.id.work_udwotype);
        udprojectnum = (TextView) findViewById(R.id.work_udprojectnum);
        uudprojectnumdesc = (TextView) findViewById(R.id.work_uudprojectnumdesc);
        assetnum = (TextView) findViewById(R.id.work_assetnum);
        assetdesc = (TextView) findViewById(R.id.work_assetdesc);
        location = (TextView) findViewById(R.id.work_location);
        locationdesc = (TextView) findViewById(R.id.work_locationdesc);
        status = (TextView) findViewById(R.id.work_status);
        statusdate = (TextView) findViewById(R.id.work_statusdate);
        lctype = (TextView) findViewById(R.id.work_lctype);
        woclass = (TextView) findViewById(R.id.work_woclass);
        failurecode = (TextView) findViewById(R.id.work_failurecode);
        problemcode = (TextView) findViewById(R.id.work_problemcode);
        displayname = (TextView) findViewById(R.id.work_displayname);
        createdate = (TextView) findViewById(R.id.work_createdate);
        jpnum = (TextView) findViewById(R.id.work_jpnum);
        targstartdate = (TextView) findViewById(R.id.work_targstartdate);
        targcompdate = (TextView) findViewById(R.id.work_targcompdate);
        actstart = (TextView) findViewById(R.id.work_acstart);
        actfinish = (TextView) findViewById(R.id.work_actfinish);
        reportedby = (TextView) findViewById(R.id.work_reportedby);
        reportdate = (TextView) findViewById(R.id.work_reportdate);


    }

    @Override
    protected void initView() {
        titlename.setText(R.string.title_activity_work_details);
        menuImageView.setImageResource(R.drawable.ic_drawer);
        menuImageView.setVisibility(View.VISIBLE);
        menuImageView.setOnClickListener(menuImageViewOnClickListener);

        backImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        wonum.setText(workOrder.wonum);
        description.setText(workOrder.description);
        parent.setText(workOrder.parent);
        udwotype.setText(workOrder.udwotype);
        udprojectnum.setText(workOrder.udprojectnum);
        uudprojectnumdesc.setText(workOrder.udprojectnumdesc);
        assetnum.setText(workOrder.assetnum);
        assetdesc.setText(workOrder.assetdesc);
        location.setText(workOrder.location);
        locationdesc.setText(workOrder.locationdesc);
        status.setText(workOrder.status);
        statusdate.setText(workOrder.statusdate);
        lctype.setText(workOrder.lctype);
        woclass.setText(workOrder.woclass);
        failurecode.setText(workOrder.failurecode);
        problemcode.setText(workOrder.problemcode);
        displayname.setText(workOrder.displayname);
        createdate.setText(workOrder.createdate);
        jpnum.setText(workOrder.jpnum);
        targstartdate.setText(workOrder.targstartdate);
        targcompdate.setText(workOrder.targcompdate);
        actstart.setText(workOrder.actstart);
        actfinish.setText(workOrder.actfinish);
        reportedby.setText(workOrder.reportedby);
        reportdate.setText(workOrder.reportdate);


    }

    private View.OnClickListener menuImageViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            showPopupWindow(menuImageView);
        }
    };
    /**
     * 初始化showPopupWindow*
     */
    private void showPopupWindow(View view) {

        // 一个自定义的布局，作为显示的内容
        View contentView = LayoutInflater.from(Work_DetailsActivity.this).inflate(
                R.layout.work_popup_window, null);


        popupWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setTouchable(true);
        popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setTouchInterceptor(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {


                return false;
                // 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
            }
        });

        // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
        // 我觉得这里是API的一个bug
        popupWindow.setBackgroundDrawable(getResources().getDrawable(
                R.drawable.popup_background_mtrl_mult));

        // 设置好参数之后再show
        popupWindow.showAsDropDown(view);

        planLinearlayout = (LinearLayout) contentView.findViewById(R.id.work_wplabor_id);
        taskLinearLayout = (LinearLayout) contentView.findViewById(R.id.work_task_id);
        realinfoLinearLayout = (LinearLayout) contentView.findViewById(R.id.work_labtrans_id);
        planLinearlayout.setOnClickListener(planOnClickListener);
        taskLinearLayout.setOnClickListener(taskOnClickListener);
        realinfoLinearLayout.setOnClickListener(realinfoOnClickListener);

        if(workOrder.status.equals(Constants.WAIT_APPROVAL)){
            realinfoLinearLayout.setVisibility(View.GONE);
        }else if(workOrder.status.equals(Constants.APPROVALED)){
            planLinearlayout.setVisibility(View.GONE);
        }
    }

    private View.OnClickListener planOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Work_DetailsActivity.this,Work_WplaborActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("workOrder", workOrder);
            bundle.putSerializable("wplaborList", (Serializable) wplaborList);
            intent.putExtras(bundle);
            startActivityForResult(intent, 0);
            popupWindow.dismiss();
        }
    };

    private View.OnClickListener taskOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Work_DetailsActivity.this,Work_AssignmentActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("workOrder", workOrder);
            intent.putExtras(bundle);
            startActivity(intent);
            popupWindow.dismiss();
        }
    };

    private View.OnClickListener realinfoOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Work_DetailsActivity.this,Work_LabtransActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("workOrder", workOrder);
            intent.putExtras(bundle);
            startActivity(intent);
            popupWindow.dismiss();
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) {
            case 1000:
                Bundle b=data.getExtras();
                ArrayList<Wplabor> wplabors = (ArrayList<Wplabor>) b.getSerializable("wplaborList");
                int i = wplabors.size();
                break;
            default:
                break;
        }
    }
}
