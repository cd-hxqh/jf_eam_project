package com.jf_eam_project.ui.activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.flyco.animation.BaseAnimatorSet;
import com.flyco.animation.BounceEnter.BounceTopEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.MaterialDialog;
import com.jf_eam_project.R;
import com.jf_eam_project.api.JsonUtils;
import com.jf_eam_project.config.Constants;
import com.jf_eam_project.model.Assignment;
import com.jf_eam_project.model.Woactivity;
import com.jf_eam_project.model.WorkOrder;
import com.jf_eam_project.model.Wplabor;
import com.jf_eam_project.model.Wpmaterial;
import com.jf_eam_project.ui.widget.CumTimePickerDialog;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
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

    private DatePickerDialog datePickerDialog;
    private CumTimePickerDialog timePickerDialog;
    StringBuffer sb;
    private int layoutnum;
    private ProgressDialog mProgressDialog;
    /**
     * 计划员工*
     */
    private LinearLayout planLinearlayout;
    private ArrayList<Woactivity> woactivityList = new ArrayList<>();
    private ArrayList<Wplabor> wplaborList = new ArrayList<>();
    private ArrayList<Wpmaterial> wpmaterialList = new ArrayList<>();
    private ArrayList<Assignment> assignmentList = new ArrayList<>();
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
//    private TextView parent;//父工单
    private TextView udwotype; //工单类型
    private TextView assetnum;//资产编号
    private TextView assetdesc;//资产描述
    private TextView location; //位置
    private TextView locationdesc;//位置描述
    private TextView status; //状态
    private TextView statusdate; //状态日期
    private TextView lctype; //风机/电气
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

    private Button revise;//修改
    private Button wfservice;//工作流
    private String reviseresult;

    private BaseAnimatorSet mBasIn;
    private BaseAnimatorSet mBasOut;

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
//        parent = (TextView) findViewById(R.id.work_parent);
        udwotype = (TextView) findViewById(R.id.work_udwotype);
        assetnum = (TextView) findViewById(R.id.work_assetnum);
        assetdesc = (TextView) findViewById(R.id.work_assetdesc);
        location = (TextView) findViewById(R.id.work_location);
        locationdesc = (TextView) findViewById(R.id.work_locationdesc);
        status = (TextView) findViewById(R.id.work_status);
        statusdate = (TextView) findViewById(R.id.work_statusdate);
        lctype = (TextView) findViewById(R.id.work_lctype);
//        woclass = (TextView) findViewById(R.id.work_woclass);
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

        revise = (Button) findViewById(R.id.work_revise);
        wfservice = (Button) findViewById(R.id.wfservice);
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
//        parent.setText(workOrder.parent);
        udwotype.setText(workOrder.udwotype);
        assetnum.setText(workOrder.assetnum);
        assetdesc.setText(workOrder.assetdesc);
        location.setText(workOrder.location);
        locationdesc.setText(workOrder.locationdesc);
        status.setText(workOrder.status);
        statusdate.setText(workOrder.statusdate);
        lctype.setText(workOrder.lctype);
//        woclass.setText(workOrder.woclass);
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

        mBasIn = new BounceTopEnter();
        mBasOut = new SlideBottomExit();
        setDataListener();
        targstartdate.setOnClickListener(new MydateListener());
        targcompdate.setOnClickListener(new MydateListener());
        actstart.setOnClickListener(new MydateListener());
        actfinish.setOnClickListener(new MydateListener());

        assetnum.setOnClickListener(new LayoutOnClickListener(Constants.ASSETCODE));
        location.setOnClickListener(new LayoutOnClickListener(Constants.LOCATIONCODE));
        failurecode.setOnClickListener(new LayoutOnClickListener(Constants.FAILURECODE));
        problemcode.setOnClickListener(new LayoutOnClickListener(Constants.FAILURELIST));
        jpnum.setOnClickListener(new LayoutOnClickListener(Constants.JOBPLAN));

        revise.setOnClickListener(reviseOnClickListener);
        wfservice.setOnClickListener(wfserviceOnClickListener);
    }

    private View.OnClickListener reviseOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (status.getText().toString().equals(Constants.WAIT_APPROVAL)){
                AlertDialog.Builder builder = new AlertDialog.Builder(Work_DetailsActivity.this);
                builder.setMessage("确定修改工单吗").setTitle("提示")
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        mProgressDialog = ProgressDialog.show(Work_DetailsActivity.this, null,
                                getString(R.string.inputing), true, true);
                        mProgressDialog.setCanceledOnTouchOutside(false);
                        mProgressDialog.setCancelable(false);
                        final String updataInfo = JsonUtils.WorkToJson(getWorkOrder(), woactivityList, wplaborList, wpmaterialList, assignmentList, null);
                        new AsyncTask<String, String, String>() {
                            @Override
                            protected String doInBackground(String... strings) {
                                reviseresult = getBaseApplication().getWsService().UpdataWO(updataInfo);
                                return reviseresult;
                            }

                            @Override
                            protected void onPostExecute(String s) {
                                super.onPostExecute(s);
                                if (s == null || s.equals("")) {
                                    Toast.makeText(Work_DetailsActivity.this, "修改工单失败", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(Work_DetailsActivity.this, "修改工单成功", Toast.LENGTH_SHORT).show();
                                }
                                mProgressDialog.dismiss();
                            }
                        }.execute();
                    }
                }).create().show();
            }else {
                Toast.makeText(Work_DetailsActivity.this, "工单状态不允许修改", Toast.LENGTH_SHORT).show();
            }
        }
    };

    private View.OnClickListener wfserviceOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            MaterialDialogOneBtn();
        }
    };

    private void MaterialDialogOneBtn() {
        final MaterialDialog dialog = new MaterialDialog(Work_DetailsActivity.this);
        dialog.isTitleShow(false)//
                .btnNum(3)
                .content("为保证咖啡豆的新鲜度和咖啡的香味，并配以特有的传统烘焙和手工冲。")//
                .btnText("通过", "不通过", "取消")//
                .showAnim(mBasIn)//
                .dismissAnim(mBasOut)//
                .show();

        dialog.setOnBtnClickL(
                new OnBtnClickL() {//left btn click listener
                    @Override
                    public void onBtnClick() {
//                        T.showShort(Work_DetailsActivity.this, "left");
                        dialog.dismiss();
                    }
                },
                new OnBtnClickL() {//right btn click listener
                    @Override
                    public void onBtnClick() {
//                        T.showShort(Work_DetailsActivity.this, "right");
                        dialog.dismiss();
                    }
                }
                ,
                new OnBtnClickL() {//middle btn click listener
                    @Override
                    public void onBtnClick() {
//                        T.showShort(Work_DetailsActivity.this, "middle");
                        dialog.dismiss();
                    }
                }
        );
    }

    private WorkOrder getWorkOrder() {
        WorkOrder workOrder = new WorkOrder();
        workOrder.wonum = wonum.getText().toString();
        workOrder.description = description.getText().toString();
        workOrder.udwotype = udwotype.getText().toString();
        workOrder.assetnum = assetnum.getText().toString();
        workOrder.location = location.getText().toString();
        workOrder.status = status.getText().toString();
        workOrder.statusdate = statusdate.getText().toString();
        workOrder.lctype = lctype.getText().toString();
        workOrder.failurecode = failurecode.getText().toString();
        workOrder.problemcode = problemcode.getText().toString();
        workOrder.createdate = createdate.getText().toString();
        workOrder.jpnum = jpnum.getText().toString();
        workOrder.targstartdate = targstartdate.getText().toString();
        workOrder.targcompdate = targcompdate.getText().toString();
        workOrder.actstart = actstart.getText().toString();
        workOrder.actfinish = actfinish.getText().toString();
        workOrder.reportedby = reportedby.getText().toString();
        workOrder.reportdate = reportdate.getText().toString();
        return workOrder;
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
        realinfoLinearLayout.setVisibility(View.GONE);
        planLinearlayout.setOnClickListener(planOnClickListener);
        taskLinearLayout.setOnClickListener(taskOnClickListener);
        realinfoLinearLayout.setOnClickListener(realinfoOnClickListener);

        if(workOrder.status.equals(Constants.WAIT_APPROVAL)){
            realinfoLinearLayout.setVisibility(View.GONE);
        }
    }

    private View.OnClickListener planOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Work_DetailsActivity.this,Work_PlanActivity.class);
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

    private class LayoutOnClickListener implements View.OnClickListener {
        int requestCode;

        private LayoutOnClickListener(int requestCode) {
            this.requestCode = requestCode;
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Work_DetailsActivity.this, OptionActivity.class);
            intent.putExtra("requestCode", requestCode);
            startActivityForResult(intent, requestCode);
        }
    }

    /**
     * 设置时间选择器*
     */
    private void setDataListener() {

        final Calendar objTime = Calendar.getInstance();
        int iYear = objTime.get(Calendar.YEAR);
        int iMonth = objTime.get(Calendar.MONTH);
        int iDay = objTime.get(Calendar.DAY_OF_MONTH);
        int hour = objTime.get(Calendar.HOUR_OF_DAY);

        int minute = objTime.get(Calendar.MINUTE);


        datePickerDialog = new DatePickerDialog(this, new datelistener(), iYear, iMonth, iDay);
        timePickerDialog = new CumTimePickerDialog(this, new timelistener(), hour, minute, true);
    }

    private class MydateListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            layoutnum = 0;
            sb = new StringBuffer();
            layoutnum = view.getId();
            datePickerDialog.show();
        }
    }

    private class datelistener implements DatePickerDialog.OnDateSetListener {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            sb = new StringBuffer();
            monthOfYear = monthOfYear + 1;
            if (dayOfMonth < 10) {
                sb.append(year % 100 + "-" + monthOfYear + "-" + "0" + dayOfMonth);
            } else {
                sb.append(year % 100 + "-" + monthOfYear + "-" + dayOfMonth);
            }
            timePickerDialog.show();
        }
    }

    private class timelistener implements TimePickerDialog.OnTimeSetListener {
        @Override
        public void onTimeSet(TimePicker timePicker, int i, int i1) {
            sb.append(" ");
            if (i1 < 10) {
                sb.append(i + ":" + "0" + i1 + ":00");
            } else {
                sb.append(i + ":" + i1 + ":00");
            }

//            Log.i(TAG,"sb="+sb);
            if (layoutnum == targstartdate.getId()) {
                targstartdate.setText(sb);
            } else if (layoutnum == targcompdate.getId()) {
                targcompdate.setText(sb);
            } else if (layoutnum == actstart.getId()) {
                actstart.setText(sb);
            } else if (layoutnum == actfinish.getId()) {
                actfinish.setText(sb);
            }

        }
    }

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
