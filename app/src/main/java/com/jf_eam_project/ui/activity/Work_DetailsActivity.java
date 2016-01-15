package com.jf_eam_project.ui.activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.flyco.animation.BaseAnimatorSet;
import com.flyco.animation.BounceEnter.BounceTopEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.listener.OnBtnEditClickL;
import com.flyco.dialog.widget.MaterialDialog;
import com.flyco.dialog.widget.NormalEditTextDialog;
import com.jf_eam_project.Dao.LocationDao;
import com.jf_eam_project.R;
import com.jf_eam_project.api.HttpManager;
import com.jf_eam_project.api.HttpRequestHandler;
import com.jf_eam_project.api.JsonUtils;
import com.jf_eam_project.api.ig.json.Ig_Json_Model;
import com.jf_eam_project.bean.Results;
import com.jf_eam_project.config.Constants;
import com.jf_eam_project.model.Assignment;
import com.jf_eam_project.model.Labtrans;
import com.jf_eam_project.model.Option;
import com.jf_eam_project.model.Woactivity;
import com.jf_eam_project.model.WorkOrder;
import com.jf_eam_project.model.Wplabor;
import com.jf_eam_project.model.Wpmaterial;
import com.jf_eam_project.ui.widget.CumTimePickerDialog;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

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
    private ArrayList<Labtrans> labtransList = new ArrayList<>();
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
        workOrder.isnew = false;
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

        if (workOrder.udwotype.equals(Constants.UNPLAN)) {
            wfservice.setVisibility(View.VISIBLE);
        } else {
            wfservice.setVisibility(View.GONE);
        }
    }

    private View.OnClickListener reviseOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (status.getText().toString().equals(Constants.WAIT_APPROVAL)) {
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

                        String updataInfo = null;
                        if (workOrder.status.equals(Constants.WAIT_APPROVAL)) {
                            updataInfo = JsonUtils.WorkToJson(getWorkOrder(), getWoactivityList(), getWplaborList(), getWpmaterialList(), getAssignmentList(), null);
                        } else if (workOrder.status.equals(Constants.APPROVALED)) {
                            updataInfo = JsonUtils.WorkToJson(getWorkOrder(), null, null, null, null, getLabtransList());
                        }
                        final String finalUpdataInfo = updataInfo;
                        new AsyncTask<String, String, String>() {
                            @Override
                            protected String doInBackground(String... strings) {
                                reviseresult = getBaseApplication().getWsService().UpdataWO(finalUpdataInfo, wonum.getText().toString());
                                return reviseresult;
                            }

                            @Override
                            protected void onPostExecute(String s) {
                                super.onPostExecute(s);
                                if (s.equals("成功")) {
                                    Toast.makeText(Work_DetailsActivity.this, "修改工单成功", Toast.LENGTH_SHORT).show();
                                } else if (s.equals("")) {
                                    Toast.makeText(Work_DetailsActivity.this, "修改工单失败", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(Work_DetailsActivity.this, s, Toast.LENGTH_SHORT).show();
                                }
                                mProgressDialog.dismiss();
                            }
                        }.execute();
                    }
                }).create().show();
            } else {
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

    private void MaterialDialogOneBtn() {//选择审核结果
        final MaterialDialog dialog = new MaterialDialog(Work_DetailsActivity.this);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.isTitleShow(false)//
                .btnNum(3)
                .content("请选择审核结果")//
                .btnText("通过", "取消", "不通过")//
                .showAnim(mBasIn)//
                .dismissAnim(mBasOut)
                .show();

        dialog.setOnBtnClickL(
                new OnBtnClickL() {//通过
                    @Override
                    public void onBtnClick() {
                        MaterialDialogOneBtn1(true);
                        dialog.dismiss();
                    }
                },
                new OnBtnClickL() {//取消
                    @Override
                    public void onBtnClick() {
                        dialog.dismiss();
                    }
                }
                ,
                new OnBtnClickL() {//不通过
                    @Override
                    public void onBtnClick() {
                        Toast.makeText(Work_DetailsActivity.this, "不通过", Toast.LENGTH_SHORT).show();
                        MaterialDialogOneBtn1(false);
                        dialog.dismiss();
                    }
                }
        );
    }

    private void MaterialDialogOneBtn1(final boolean isok) {//是否输入审核意见
        final MaterialDialog dialog = new MaterialDialog(Work_DetailsActivity.this);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.isTitleShow(false)//
                .btnNum(2)
                .content("是否填写输入意见")//
                .btnText("是", "否，直接提交")//
                .showAnim(mBasIn)//
                .dismissAnim(mBasOut)
                .show();

        dialog.setOnBtnClickL(
                new OnBtnClickL() {//是
                    @Override
                    public void onBtnClick() {
                        EditDialog(isok);
                        dialog.dismiss();
                    }
                },
                new OnBtnClickL() {//否
                    @Override
                    public void onBtnClick() {
                        getwfstatus(isok);
//                        if (isstart) {
//                            wfstart(workOrder.wonum);
//                        } else {
//                            wfgoon(workOrder.wonum, isok ? "1" : "0", isok ? "通过" : "不通过");
//                        }
                        dialog.dismiss();
                    }
                }
        );
    }

    private void EditDialog(final boolean isok) {//输入审核意见
        final NormalEditTextDialog dialog = new NormalEditTextDialog(Work_DetailsActivity.this);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.isTitleShow(false)//
                .btnNum(2)
                .content(isok ? "通过" : "不通过")//
                .btnText("提交", "取消")//
                .showAnim(mBasIn)//
                .dismissAnim(mBasOut)
                .show();

        dialog.setOnBtnClickL(
                new OnBtnEditClickL() {
                    @Override
                    public void onBtnClick(String text) {
                        getwfstatus(isok);
//                        if (isstart) {
//                            wfstart(workOrder.wonum);
//                        } else {
//                            wfgoon(workOrder.wonum, isok ? "1" : "0", text);
//                        }
                    }
                },
                new OnBtnEditClickL() {
                    @Override
                    public void onBtnClick(String text) {
                        dialog.dismiss();
                    }
                }
        );
    }

    /**
     * 判断工作流是否已启动
     *
     * @return
     */
    private void getwfstatus(final boolean isok) {
        HttpManager.getDataPagingInfo(this, HttpManager.getWfStatusUrl(1, 20, workOrder.workorderid), new HttpRequestHandler<Results>() {
            String result;

            @Override
            public void onSuccess(Results results) {
                Log.i(TAG, "data=" + results);
            }

            @Override
            public void onSuccess(Results results, int totalPages, int currentPage) {
                String result = JsonUtils.parsingwfstatusResult(results.getResultlist());
                if (result.equals("N")) {
                    wfstart(workOrder.wonum);
                } else {
                    wfgoon(workOrder.wonum, isok ? "1" : "0", isok ? "通过" : "不通过");
                }
                Log.i(TAG, "data=" + result);
            }

            @Override
            public void onFailure(String error) {
                Toast.makeText(Work_DetailsActivity.this, "查询工作流状态失败，审核中止", Toast.LENGTH_SHORT).show();
                mProgressDialog.dismiss();
            }
        });

    }

    /**
     * 开始工作流
     *
     * @param wonum
     */
    private void wfstart(final String wonum) {
        mProgressDialog = ProgressDialog.show(Work_DetailsActivity.this, null,
                getString(R.string.inputing), true, true);
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.setCancelable(false);
        new AsyncTask<String, String, String>() {
            @Override
            protected String doInBackground(String... strings) {
                String result = getBaseApplication().getWfService().startwf("UDFJHWO", "WORKORDER", wonum, "WONUM");
                return result;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if (s == null || s.equals("")) {
                    Toast.makeText(Work_DetailsActivity.this, "审批失败", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Work_DetailsActivity.this, s, Toast.LENGTH_SHORT).show();

                }
                mProgressDialog.dismiss();
            }
        }.execute();
    }

    /**
     * 审批工作流
     *
     * @param wonum
     * @param zx
     */
    private void wfgoon(final String wonum, final String zx, final String desc) {
        mProgressDialog = ProgressDialog.show(Work_DetailsActivity.this, null,
                getString(R.string.inputing), true, true);
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.setCancelable(false);
        new AsyncTask<String, String, String>() {
            @Override
            protected String doInBackground(String... strings) {
                String result = getBaseApplication().getWfService().wfGoOn("UDFJHWO", "WORKORDER", wonum, "WONUM", zx, desc);
                return result;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if (s == null || s.equals("")) {
                    Toast.makeText(Work_DetailsActivity.this, "审批失败", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Work_DetailsActivity.this, s, Toast.LENGTH_SHORT).show();
                }
                mProgressDialog.dismiss();
            }
        }.execute();
    }

    //获取界面上工单内容
    private WorkOrder getWorkOrder() {
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

    //过滤变化过的任务数据
    private ArrayList<Woactivity> getWoactivityList() {
        ArrayList<Woactivity> woactivities = new ArrayList<>();
        for (int i = 0; i < woactivityList.size(); i++) {
            if (woactivityList.get(i).type != null && !woactivityList.get(i).type.equals("")) {//如果此条数据是变动后的
                woactivities.add(woactivityList.get(i));
            }
        }
        return woactivities;
    }

    //过滤变化过的计划员工数据
    private ArrayList<Wplabor> getWplaborList() {
        ArrayList<Wplabor> wplabors = new ArrayList<>();
        for (int i = 0; i < wplaborList.size(); i++) {
            if (wplaborList.get(i).type != null && !wplaborList.get(i).equals("")) {//如果此条数据是变动后的
                wplabors.add(wplaborList.get(i));
            }
        }
        return wplabors;
    }

    //过滤变化过的计划物料数据
    private ArrayList<Wpmaterial> getWpmaterialList() {
        ArrayList<Wpmaterial> wpmaterials = new ArrayList<>();
        for (int i = 0; i < wpmaterialList.size(); i++) {
            if (wpmaterialList.get(i).type != null && !wpmaterialList.get(i).type.equals("")) {//如果此条数据是变动后的
                wpmaterials.add(wpmaterialList.get(i));
            }
        }
        return wpmaterials;
    }

    //过滤变化过的任务分配数据
    private ArrayList<Assignment> getAssignmentList() {
        ArrayList<Assignment> assignments = new ArrayList<>();
        for (int i = 0; i < assignmentList.size(); i++) {
            if (assignmentList.get(i).type != null && !assignmentList.get(i).type.equals("")) {//如果此条数据是变动后的
                assignments.add(assignmentList.get(i));
            }
        }
        return assignments;
    }

    //过滤变化过的实际数据
    private ArrayList<Labtrans> getLabtransList() {
        ArrayList<Labtrans> labtranses = new ArrayList<>();
        for (int i = 0; i < labtransList.size(); i++) {
            if (labtransList.get(i).type != null && !labtransList.get(i).type.equals("")) {//如果此条数据是变动后的
                labtranses.add(labtransList.get(i));
            }
        }
        return labtranses;
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
        View contentView = LayoutInflater.from(Work_DetailsActivity.this).inflate(
                R.layout.work_popup_window, null);

        popupWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setTouchable(true);
        popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);

        popupWindow.setBackgroundDrawable(getResources().getDrawable(
                R.drawable.abc_popup_background_mtrl_mult));
        popupWindow.showAsDropDown(view, 0, 20);

        planLinearlayout = (LinearLayout) contentView.findViewById(R.id.work_wplabor_id);
        taskLinearLayout = (LinearLayout) contentView.findViewById(R.id.work_task_id);
        realinfoLinearLayout = (LinearLayout) contentView.findViewById(R.id.work_labtrans_id);
        planLinearlayout.setOnClickListener(planOnClickListener);
        taskLinearLayout.setOnClickListener(taskOnClickListener);
        realinfoLinearLayout.setOnClickListener(realinfoOnClickListener);

//        if (workOrder.status.equals(Constants.WAIT_APPROVAL)) {
//            realinfoLinearLayout.setVisibility(View.GONE);
//        }
    }

    private View.OnClickListener planOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Work_DetailsActivity.this, Work_PlanActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("workOrder", workOrder);
            bundle.putSerializable("woactivityList", woactivityList);
            bundle.putSerializable("wplaborList", wplaborList);
            bundle.putSerializable("wpmaterialList", wpmaterialList);
            intent.putExtras(bundle);
            startActivityForResult(intent, 0);
            popupWindow.dismiss();
        }
    };

    private View.OnClickListener taskOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Work_DetailsActivity.this, Work_AssignmentActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("workOrder", workOrder);
            bundle.putSerializable("assignmentList", assignmentList);
            intent.putExtras(bundle);
            startActivityForResult(intent, 2000);
            popupWindow.dismiss();
        }
    };

    private View.OnClickListener realinfoOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Work_DetailsActivity.this, Work_LabtransActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("workOrder", workOrder);
            bundle.putSerializable("labtransList", labtransList);
            intent.putExtras(bundle);
            startActivityForResult(intent, 3000);
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
        Option option;
        switch (resultCode) {
            case Constants.ASSETCODE:
                option = (Option) data.getSerializableExtra("option");
                assetnum.setText(option.getName());
                assetdesc.setText(option.getDescription());
                location.setText(option.getValue());
                locationdesc.setText(new LocationDao(Work_DetailsActivity.this).queryLocation(option.getValue()).description);
                break;
            case Constants.LOCATIONCODE:
                option = (Option) data.getSerializableExtra("option");
                location.setText(option.getName());
                locationdesc.setText(option.getDescription());
                break;
            case Constants.FAILURECODE:
                option = (Option) data.getSerializableExtra("option");
                failurecode.setText(option.getName());
                break;
            case Constants.FAILURELIST:
                option = (Option) data.getSerializableExtra("option");
                problemcode.setText(option.getName());
                break;
            case Constants.JOBPLAN:
                option = (Option) data.getSerializableExtra("option");
                jpnum.setText(option.getName());
                break;
            case 1000:
                woactivityList = (ArrayList<Woactivity>) data.getSerializableExtra("woactivityList");
                wplaborList = (ArrayList<Wplabor>) data.getSerializableExtra("wplaborList");
                wpmaterialList = (ArrayList<Wpmaterial>) data.getSerializableExtra("wpmaterialList");
                break;
            case 2000:
                assignmentList = (ArrayList<Assignment>) data.getSerializableExtra("assignmentList");
                break;
            case 3000:
                labtransList = (ArrayList<Labtrans>) data.getSerializableExtra("labtransList");
                break;
            default:
                break;
        }
    }
}
