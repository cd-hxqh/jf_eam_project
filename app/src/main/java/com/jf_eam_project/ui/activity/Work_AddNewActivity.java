package com.jf_eam_project.ui.activity;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
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
import com.flyco.dialog.entity.DialogMenuItem;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.NormalDialog;
import com.flyco.dialog.widget.NormalListDialog;
import com.jf_eam_project.Dao.AssignmentDao;
import com.jf_eam_project.Dao.LocationDao;
import com.jf_eam_project.Dao.WoactivityDao;
import com.jf_eam_project.Dao.WorkOrderDao;
import com.jf_eam_project.Dao.WplaborDao;
import com.jf_eam_project.Dao.WpmeterialDao;
import com.jf_eam_project.R;
import com.jf_eam_project.api.JsonUtils;
import com.jf_eam_project.config.Constants;
import com.jf_eam_project.model.Assignment;
import com.jf_eam_project.model.Labtrans;
import com.jf_eam_project.model.Option;
import com.jf_eam_project.model.Webservice_result;
import com.jf_eam_project.model.Woactivity;
import com.jf_eam_project.model.WorkOrder;
import com.jf_eam_project.model.Wplabor;
import com.jf_eam_project.model.Wpmaterial;
import com.jf_eam_project.ui.widget.CumTimePickerDialog;
import com.jf_eam_project.utils.GetNowTime;
import com.jf_eam_project.utils.MessageUtils;
import com.jf_eam_project.utils.NetWorkHelper;

import org.apache.commons.lang3.math.NumberUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by think on 2015/11/30.
 * 新增工单详情页面
 */
public class Work_AddNewActivity extends BaseActivity {

    private WorkOrder workOrder = new WorkOrder();
    private TextView titlename;
    private ImageView menuImageView;
    private ImageView backlayout;
    private PopupWindow popupWindow;

    private TextView wonum;//工单号
    private LinearLayout wonumlayout;
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
    //    private TextView woclass; //类
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

    private Button addnew;//新增按钮
    private Button wfservice;//开始工作流

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

    private String addresult;
    private String result;
    protected static final int S = 0;
    protected static final int F = 1;
    protected static final int YUZHI_S = 2;
    protected static final int YUZHI_F = 3;


    private BaseAnimatorSet mBasIn;
    private BaseAnimatorSet mBasOut;
    private ArrayList<DialogMenuItem> mMenuItems = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addnew);

        geiIntentData();
        findViewById();
        initView();
        mBasIn = new BounceTopEnter();
        mBasOut = new SlideBottomExit();
        addLctypeData();
    }

    /**
     * 获取数据*
     */
    private void geiIntentData() {
        workOrder.setUdwotype(getIntent().getStringExtra("worktype"));
    }

    @Override
    protected void findViewById() {
        titlename = (TextView) findViewById(R.id.title_name);
        menuImageView = (ImageView) findViewById(R.id.title_add);
        backlayout = (ImageView) findViewById(R.id.title_back_id);
        wonum = (TextView) findViewById(R.id.work_wonum);
        wonumlayout = (LinearLayout) findViewById(R.id.work_wonum_layout);
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

        addnew = (Button) findViewById(R.id.work_add);
        wfservice = (Button) findViewById(R.id.wfservice);
    }

    @Override
    protected void initView() {
        titlename.setText(R.string.title_activity_work_addnew);
        menuImageView.setImageResource(R.drawable.ic_drawer);
        menuImageView.setVisibility(View.VISIBLE);
        menuImageView.setOnClickListener(menuImageViewOnClickListener);

        wonumlayout.setVisibility(View.GONE);
        workOrder.isnew = true;
        udwotype.setText(workOrder.udwotype);
        status.setText("等待核准");
        statusdate.setText(GetNowTime.getTime());
        displayname.setText(getBaseApplication().getUsername());
        createdate.setText(GetNowTime.getTime());
        reportedby.setText(getBaseApplication().getUsername());
        reportdate.setText(GetNowTime.getTime());

        lctype.setOnClickListener(lctypeOnClickListener);

        addnew.setOnClickListener(addnewlistener);
        wfservice.setOnClickListener(wfserviceOnClickListener);

        backlayout.setOnClickListener(backOnClickListener);

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

        if (workOrder.udwotype.equals(Constants.UNPLAN)) {
            wfservice.setVisibility(View.VISIBLE);
        } else {
            wfservice.setVisibility(View.GONE);
        }
    }

    private View.OnClickListener backOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (wonumlayout.getVisibility()==View.GONE) {
                final NormalDialog dialog = new NormalDialog(Work_AddNewActivity.this);
                dialog.content("确定放弃新增吗?")//
                        .showAnim(mBasIn)//
                        .dismissAnim(mBasOut)//
                        .show();

                dialog.setOnBtnClickL(
                        new OnBtnClickL() {
                            @Override
                            public void onBtnClick() {
                                dialog.dismiss();
                            }
                        },
                        new OnBtnClickL() {
                            @Override
                            public void onBtnClick() {
//                                dialog.dismiss();
                                Work_AddNewActivity.this.finish();
                            }
                        });
            }else {
                Work_AddNewActivity.this.finish();
            }
        }
    };

    private View.OnClickListener lctypeOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            NormalListDialog();
        }
    };

    private void NormalListDialog() {
        final NormalListDialog dialog = new NormalListDialog(Work_AddNewActivity.this, mMenuItems);
        dialog.title("请选择")//
                .showAnim(mBasIn)//
                .dismissAnim(mBasOut)//
                .show();
        dialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {

                lctype.setText(mMenuItems.get(position).mOperName);

                dialog.dismiss();
            }
        });
    }

    /**
     * 添加数据*
     */
    private void addLctypeData() {
        String[] lctypes = getResources().getStringArray(R.array.lctype_tab_titles);

        for (int i = 0; i < lctypes.length; i++)
            mMenuItems.add(new DialogMenuItem(lctypes[i], 0));


    }


    private View.OnClickListener addnewlistener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (targstartdate.getText().equals("") || targcompdate.getText().equals("")
                    || actstart.getText().equals("") || actfinish.getText().equals("")) {
                Toast.makeText(Work_AddNewActivity.this, "请输入日期时间", Toast.LENGTH_SHORT).show();
            } else {
                final NormalDialog dialog = new NormalDialog(Work_AddNewActivity.this);
                dialog.content("确定新增工单吗?")//
                        .showAnim(mBasIn)//
                        .dismissAnim(mBasOut)//
                        .show();
                dialog.setOnBtnClickL(
                        new OnBtnClickL() {
                            @Override
                            public void onBtnClick() {
                                dialog.dismiss();
                            }
                        },
                        new OnBtnClickL() {
                            @Override
                            public void onBtnClick() {
                                showProgressDialog("数据提交中...");
                                startAsyncTask();
                                dialog.dismiss();
                            }
                        });
            }
        }

        ;

        /**
         * 提交数据*
         */
        private void startAsyncTask() {
            if (wonumlayout.getVisibility() == View.GONE) {
                if (NetWorkHelper.isNetwork(Work_AddNewActivity.this)) {
                    MessageUtils.showMiddleToast(Work_AddNewActivity.this, "暂无网络,现离线保存数据!");
                    saveWorkOrder();
                    closeProgressDialog();
                } else {
                    final String updataInfo = JsonUtils.WorkToJson(getWorkOrder(), woactivityList, wplaborList, wpmaterialList, assignmentList, null);
                    new AsyncTask<String, String, String>() {
                        @Override
                        protected String doInBackground(String... strings) {
                            addresult = getBaseApplication().getWsService().InsertWO(updataInfo, getBaseApplication().getUsername());
                            return addresult;
                        }

                        @Override
                        protected void onPostExecute(String s) {
                            super.onPostExecute(s);
                            if (s == null || s.equals("")) {
                                Toast.makeText(Work_AddNewActivity.this, "新增工单失败", Toast.LENGTH_SHORT).show();
                            } else if (!NumberUtils.isDigits(s)) {
                                Toast.makeText(Work_AddNewActivity.this, s, Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(Work_AddNewActivity.this, "新增工单" + s + "成功", Toast.LENGTH_SHORT).show();
                                wonumlayout.setVisibility(View.VISIBLE);
                                wonum.setText(s);
                            }
                            closeProgressDialog();
                        }
                    }.execute();
                }
            } else {
                Toast.makeText(Work_AddNewActivity.this, "工单已新增", Toast.LENGTH_SHORT).show();
                closeProgressDialog();
            }
        }
    };

    private View.OnClickListener wfserviceOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (wonumlayout.getVisibility() == View.GONE && (wonum.getText().toString() == null || wonum.getText().toString().equals(""))) {
                Toast.makeText(Work_AddNewActivity.this, "请先新增工单", Toast.LENGTH_SHORT).show();
            } else {
                final NormalDialog dialog = new NormalDialog(Work_AddNewActivity.this);
                dialog.content("确定开始工作流吗?")//
                        .showAnim(mBasIn)//
                        .dismissAnim(mBasOut)//
                        .show();

                dialog.setOnBtnClickL(
                        new OnBtnClickL() {
                            @Override
                            public void onBtnClick() {
                                dialog.dismiss();
                            }
                        },
                        new OnBtnClickL() {
                            @Override
                            public void onBtnClick() {
                                showProgressDialog("数据提交中...");
                                new AsyncTask<String, String, String>() {
                                    @Override
                                    protected String doInBackground(String... strings) {
                                        result = getBaseApplication().getWfService().startwf("UDFJHWO", "WORKORDER", addresult, "WONUM");
                                        return result;
                                    }

                                    @Override
                                    protected void onPostExecute(String s) {
                                        super.onPostExecute(s);
                                        if (s == null || s.equals("")) {
                                            Toast.makeText(Work_AddNewActivity.this, "审批失败", Toast.LENGTH_SHORT).show();
                                        } else if (s.equals("工作流启动成功")){
                                            Toast.makeText(Work_AddNewActivity.this, s, Toast.LENGTH_SHORT).show();
                                            Work_AddNewActivity.this.finish();
                                        }else {
                                            Toast.makeText(Work_AddNewActivity.this, s, Toast.LENGTH_SHORT).show();
                                        }
                                        mProgressDialog.dismiss();
                                    }
                                }.execute();
                                dialog.dismiss();
                            }
                        });
            }
        }
    };

    private WorkOrder getWorkOrder() {
        workOrder.wonum = "";
        workOrder.description = description.getText().toString();
        workOrder.udwotype = udwotype.getText().toString();
        workOrder.assetnum = assetnum.getText().toString();
        workOrder.assetdesc = assetdesc.getText().toString();
        workOrder.location = location.getText().toString();
        workOrder.locationdesc = locationdesc.getText().toString();
        workOrder.status = status.getText().toString();
        workOrder.statusdate = statusdate.getText().toString();
        workOrder.lctype = lctype.getText().toString();
        workOrder.failurecode = failurecode.getText().toString();
        workOrder.problemcode = problemcode.getText().toString();
        workOrder.displayname = displayname.getText().toString();
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

    private void saveWorkOrder() {
        WorkOrder workOrder = getWorkOrder();
        workOrder.ishistory = true;
        new WorkOrderDao(Work_AddNewActivity.this).Update(workOrder);
        int id = workOrder.id;
        if (id != 0) {
            if (woactivityList.size() != 0) {
                for (Woactivity woactivity : woactivityList) {
                    woactivity.belongid = id;
                }
                new WoactivityDao(Work_AddNewActivity.this).create(woactivityList);
                ArrayList<Woactivity> list = (ArrayList<Woactivity>) new WoactivityDao(Work_AddNewActivity.this).queryForAll();
            }
            if (wplaborList.size() != 0) {
                for (Wplabor wplabor : wplaborList) {
                    wplabor.belongid = id;
                }
                new WplaborDao(Work_AddNewActivity.this).create(wplaborList);
                ArrayList<Wplabor> list = (ArrayList<Wplabor>) new WplaborDao(Work_AddNewActivity.this).queryForAll();
            }
            if (wpmaterialList.size() != 0) {
                for (Wpmaterial wpmaterial : wpmaterialList) {
                    wpmaterial.belongid = id;
                }
                new WpmeterialDao(Work_AddNewActivity.this).create(wpmaterialList);
                ArrayList<Wpmaterial> list = (ArrayList<Wpmaterial>) new WpmeterialDao(Work_AddNewActivity.this).queryForAll();
            }
            if (assignmentList.size() != 0) {
                for (Assignment assignment : assignmentList) {
                    assignment.belongid = id;
                }
                new AssignmentDao(Work_AddNewActivity.this).create(assignmentList);
                ArrayList<Assignment> list = (ArrayList<Assignment>) new AssignmentDao(Work_AddNewActivity.this).queryForAll();
            }
        }
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
    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void showPopupWindow(View view) {
        View contentView = LayoutInflater.from(Work_AddNewActivity.this).inflate(
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
        realinfoLinearLayout.setVisibility(View.GONE);
        planLinearlayout.setOnClickListener(planOnClickListener);
        taskLinearLayout.setOnClickListener(taskOnClickListener);
//        realinfoLinearLayout.setOnClickListener(realinfoOnClickListener);

    }

    private View.OnClickListener planOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Work_AddNewActivity.this, Work_PlanActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("workOrder", workOrder);
            bundle.putSerializable("woactivityList", woactivityList);
            bundle.putSerializable("wplaborList", wplaborList);
            bundle.putSerializable("wpmaterialList", wpmaterialList);
            intent.putExtras(bundle);
            startActivityForResult(intent, 1000);
            popupWindow.dismiss();
        }
    };

    private View.OnClickListener taskOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Work_AddNewActivity.this, Work_AssignmentActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("workOrder", workOrder);
//            bundle.putSerializable("woactivityList", woactivityList);
            bundle.putSerializable("assignmentList", assignmentList);
            intent.putExtras(bundle);
            startActivityForResult(intent, 2000);
            popupWindow.dismiss();
        }
    };

//    private View.OnClickListener realinfoOnClickListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//            Intent intent = new Intent(Work_AddNewActivity.this, Work_LabtransActivity.class);
//            Bundle bundle = new Bundle();
//            bundle.putSerializable("workOrder", workOrder);
//            intent.putExtras(bundle);
//            startActivity(intent);
//            popupWindow.dismiss();
//        }
//    };

    private class LayoutOnClickListener implements View.OnClickListener {
        int requestCode;

        private LayoutOnClickListener(int requestCode) {
            this.requestCode = requestCode;
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Work_AddNewActivity.this, OptionActivity.class);
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
                sb.append(year + "-" + monthOfYear + "-" + "0" + dayOfMonth);
            } else {
                sb.append(year + "-" + monthOfYear + "-" + dayOfMonth);
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
                if (new LocationDao(Work_AddNewActivity.this).queryLocation(option.getValue()) != null) {
                    locationdesc.setText(new LocationDao(Work_AddNewActivity.this).queryLocation(option.getValue()).description);
                }
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
            default:
                break;
        }
    }
}
