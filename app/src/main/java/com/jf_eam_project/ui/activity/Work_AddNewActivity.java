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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.jf_eam_project.Dao.LocationDao;
import com.jf_eam_project.R;
import com.jf_eam_project.config.Constants;
import com.jf_eam_project.model.Labtrans;
import com.jf_eam_project.model.Option;
import com.jf_eam_project.model.Webservice_result;
import com.jf_eam_project.model.Woactivity;
import com.jf_eam_project.model.WorkOrder;
import com.jf_eam_project.model.Wplabor;
import com.jf_eam_project.model.Wpmaterial;
import com.jf_eam_project.ui.widget.CumTimePickerDialog;
import com.jf_eam_project.utils.GetNowTime;

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
 *
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

    private Button addnew;

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
    /**
     * 任务分配*
     */
    private LinearLayout taskLinearLayout;
    /**
     * 实际情况
     */
    private LinearLayout realinfoLinearLayout;

    private Webservice_result result;
    protected static final int S = 0;
    protected static final int F = 1;
    protected static final int YUZHI_S = 2;
    protected static final int YUZHI_F = 3;
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case S:
                    mProgressDialog.dismiss();
                    break;
                case F:
                    mProgressDialog.dismiss();
                    break;
                case YUZHI_S:
                    mProgressDialog.dismiss();
                    break;
                case YUZHI_F:
                    mProgressDialog.dismiss();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addnew);

        geiIntentData();
        findViewById();
        initView();
    }

    /**
     * 获取数据*
     */
    private void geiIntentData() {
        workOrder.setWorktype(getIntent().getStringExtra("worktype"));
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
    }

    @Override
    protected void initView() {
        titlename.setText(R.string.title_activity_work_addnew);
        menuImageView.setImageResource(R.drawable.ic_drawer);
        menuImageView.setVisibility(View.VISIBLE);
        menuImageView.setOnClickListener(menuImageViewOnClickListener);

        wonumlayout.setVisibility(View.GONE);
        udwotype.setText(workOrder.worktype);
        status.setText("等待核准");
        statusdate.setText(GetNowTime.getTime());
        displayname.setText(getBaseApplication().getUsername());
        createdate.setText(GetNowTime.getTime());
        reportedby.setText(getBaseApplication().getUsername());
        reportdate.setText(GetNowTime.getTime());

        lctype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int checked = 0;
                if (lctype.getText().equals("电气")) {
                    checked = 1;
                }
                new AlertDialog.Builder(Work_AddNewActivity.this).setTitle("单选框").setIcon(
                        android.R.drawable.ic_dialog_info).setSingleChoiceItems(
                        new String[]{"风机", "电气"}, checked,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                if (which == 0) {
                                    lctype.setText("风机");
                                } else {
                                    lctype.setText("电气");
                                }
                                dialog.dismiss();
                            }
                        }).setNegativeButton("取消", null).show();
            }
        });

        addnew.setOnClickListener(addnewlistener);

        backlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

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
    }

    private View.OnClickListener addnewlistener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            AlertDialog.Builder builder = new AlertDialog.Builder(Work_AddNewActivity.this);
            builder.setMessage("确定新增工单吗").setTitle("提示")
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    mProgressDialog = ProgressDialog.show(Work_AddNewActivity.this, null,
                            getString(R.string.inputing), true, true);
                    mProgressDialog.setCanceledOnTouchOutside(false);
                    mProgressDialog.setCancelable(false);
                    new AsyncTask<String, Webservice_result, Webservice_result>() {
                        @Override
                        protected Webservice_result doInBackground(String... strings) {
                            String data = "{\"wonum\":\"1255\",\n" +
                                    "\"DESCRIPTION\":\"测试1#风机检修\",\n" +
                                    "\"UDAPPTYPE\":\"UDWOTRACK\",\n" +
                                    "\"assetnum\":\"TEST001\",\n" +
                                    "\"status\":\"已核准\",\n" +
                                    "\"location\":\"TRXNSITE\",\n" +
                                    "\"lctype\":\"电气\",\n" +
                                    "\"relationShip\":[{\"wpmaterial\":\"\",\"WOACTIVITY\":\"\"}],\n" +
                                    "\"wpmaterial\":\n" +
                                    "[{\n" +
                                    "\"wonum\":\"1255\",\n" +
                                    "\"itemnum\":\"TEST003\",\n" +
                                    "\"itemqty\":\"1\",\n" +
                                    "\"location\":\"DST\"\n" +
                                    "},\n" +
                                    "{\"wonum\":\"1255\",\n" +
                                    "\"itemnum\":\"TEST002\",\n" +
                                    "\"itemqty\":\"1\",\n" +
                                    "\"location\":\"DST\"}\n" +
                                    "],\n" +
                                    "\"WOACTIVITY\": \n" +
                                    "[{\n" +
                                    "\"wosequence\":\"\",\n" +
                                    "\"taskid\":\"10\",\n" +
                                    "\"description\":\"步骤1\",\n" +
                                    "\"parent\":\"1255\"\n" +
                                    "},\n" +
                                    "{\"wosequence\":\"\",\n" +
                                    "\"taskid\":\"20\",\n" +
                                    "\"description\":\"步骤2\",\n" +
                                    "\"parent\":\"1255\"}\n" +
                                    "]}";
                            result = getBaseApplication().getWsService().InsertWO(data);
                            return result;
                        }

                        @Override
                        protected void onPostExecute(Webservice_result s) {
                            super.onPostExecute(s);
                            if (s.getErrorMsg().equals("成功")) {
                                Toast.makeText(Work_AddNewActivity.this, "新增工单成功", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(Work_AddNewActivity.this, s.getErrorMsg(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }.execute();
                }
            }).create().show();
        }
    };

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
            bundle.putSerializable("woactivityList",woactivityList);
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
            intent.putExtras(bundle);
            startActivity(intent);
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

    private class LayoutOnClickListener implements View.OnClickListener{
        int requestCode;
        private LayoutOnClickListener(int requestCode){
            this.requestCode = requestCode;
        }
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Work_AddNewActivity.this,OptionActivity.class);
            intent.putExtra("requestCode",requestCode);
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
                sb.append(year%100 + "-" + monthOfYear + "-" + "0" + dayOfMonth);
            } else {
                sb.append(year%100 + "-" + monthOfYear + "-" + dayOfMonth);
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
                locationdesc.setText(new LocationDao(Work_AddNewActivity.this).queryLocation(option.getValue()).description);
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
            default:
                break;
        }
    }
}
