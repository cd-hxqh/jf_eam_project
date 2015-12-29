package com.jf_eam_project.ui.activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.MotionEvent;
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
import com.jf_eam_project.model.WorkOrder;
import com.jf_eam_project.model.Wplabor;
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
    private RelativeLayout wonumlayout;
    private TextView description;//描述
//    private TextView parent;//父工单
    private TextView udwotype; //工单类型
    private TextView assetnum;//资产编号
    private RelativeLayout assetnumlayout;
    private TextView assetdesc;//资产描述
    private TextView location; //位置
    private TextView locationdesc;//位置描述
    private TextView status; //状态
    private TextView statusdate; //状态日期
    private TextView lctype; //风机/电气
//    private TextView woclass; //类
    private TextView failurecode; //故障类
    private RelativeLayout failurecodelayout;
    private TextView problemcode; //问题代码
    private RelativeLayout problemcodelayout;
    private TextView displayname; //创建人
    private TextView createdate; //创建时间

    private TextView jpnum; //作业计划
    private RelativeLayout jpnumlayout;
    private TextView targstartdate;//计划开始时间
    private RelativeLayout targstartdatelayout;
    private TextView targcompdate;//计划完成时间
    private RelativeLayout targcompdatelayout;
    private TextView actstart;//实际开始时间
    private RelativeLayout actstartlayout;
    private TextView actfinish;//实际完成时间
    private RelativeLayout actfinishlayout;

    private TextView reportedby; //报告人
    private TextView reportdate; //汇报日期

    private RelativeLayout lctypelayout;//风机/电气

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
    private ArrayList<Wplabor> wplaborList = new ArrayList<>();
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
        wonumlayout = (RelativeLayout) findViewById(R.id.work_wonum_layout);
        description = (TextView) findViewById(R.id.work_desc);
//        parent = (TextView) findViewById(R.id.work_parent);
        udwotype = (TextView) findViewById(R.id.work_udwotype);
        assetnum = (TextView) findViewById(R.id.work_assetnum);
        assetnumlayout = (RelativeLayout) findViewById(R.id.work_assetnum_layout);
        assetdesc = (TextView) findViewById(R.id.work_assetdesc);
        location = (TextView) findViewById(R.id.work_location);
        locationdesc = (TextView) findViewById(R.id.work_locationdesc);
        status = (TextView) findViewById(R.id.work_status);
        statusdate = (TextView) findViewById(R.id.work_statusdate);
        lctype = (TextView) findViewById(R.id.work_lctype);
//        woclass = (TextView) findViewById(R.id.work_woclass);
        failurecode = (TextView) findViewById(R.id.work_failurecode);
        failurecodelayout = (RelativeLayout) findViewById(R.id.work_failurecode_layout);
        problemcode = (TextView) findViewById(R.id.work_problemcode);
        problemcodelayout = (RelativeLayout) findViewById(R.id.work_problemcode_layout);
        displayname = (TextView) findViewById(R.id.work_displayname);
        createdate = (TextView) findViewById(R.id.work_createdate);
        jpnum = (TextView) findViewById(R.id.work_jpnum);
        jpnumlayout = (RelativeLayout) findViewById(R.id.work_jpnum_layout);
        targstartdate = (TextView) findViewById(R.id.work_targstartdate);
        targstartdatelayout = (RelativeLayout) findViewById(R.id.work_targstartdate_layout);
        targcompdate = (TextView) findViewById(R.id.work_targcompdate);
        targcompdatelayout = (RelativeLayout) findViewById(R.id.work_targcompdate_layout);
        actstart = (TextView) findViewById(R.id.work_acstart);
        actstartlayout = (RelativeLayout) findViewById(R.id.work_acstart_layout);
        actfinish = (TextView) findViewById(R.id.work_actfinish);
        actfinishlayout = (RelativeLayout) findViewById(R.id.work_actfinish_layout);
        reportedby = (TextView) findViewById(R.id.work_reportedby);
        reportdate = (TextView) findViewById(R.id.work_reportdate);

        lctypelayout = (RelativeLayout) findViewById(R.id.work_lctype_layout);

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

        lctypelayout.setOnClickListener(new View.OnClickListener() {
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
        targstartdatelayout.setOnClickListener(new MydateListener());
        targcompdatelayout.setOnClickListener(new MydateListener());
        actstartlayout.setOnClickListener(new MydateListener());
        actfinishlayout.setOnClickListener(new MydateListener());

        assetnumlayout.setOnClickListener(new LayoutOnClickListener(Constants.ASSETCODE));
        failurecodelayout.setOnClickListener(new LayoutOnClickListener(Constants.FAILURECODE));
        problemcodelayout.setOnClickListener(new LayoutOnClickListener(Constants.FAILURELIST));
        jpnumlayout.setOnClickListener(new LayoutOnClickListener(Constants.JOBPLAN));
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
                            String data = "{\"woNum\":\"test2\",\"description\":\"description\",\"location\":\"DST\",\"assetnum\":\"8888\",\"UDWOTYPE\":\"PLAN\",\"actstart\":\"2015-08-01 11:11:11\",\"actfinish\":\"2015-08-10 11:11:11\",\"problem\":\"TEST\",\"remedy\":\"TEST1\",\"cause\":\"TEST2\",\"reportedby\":\"MAXADMIN\",\"lctype\":\"电气\",\"failurecode\":\"TEST\",\"failurelist1\":\"1005\",\"failurelist2\":\"1015\",\"failurelist3\":\"1011\",\"reportdate\":\"2015-08-01 11:11:11\",\"onbehalfof\":\"CHANEY\",\"jpnum\":\"JP11220\",\"wotasks\":[{\"wosequence\":\"\",\"taskid\":\"10\",\"description\":\"步骤1\",\"workorderid\":\"\"},{\"wosequence\":\"\",\"taskid\":\"20\",\"description\":\"步骤2\",\"workorderid\":\"\"}],\"wpmaterials\":[{\"itemnum\":\"TEST003\",\"itemqty\":\"1\",\"location\":\"DST\"},{\"itemnum\":\"TEST002\",\"itemqty\":\"1\",\"location\":\"DST\"}],\"labtrans\":[{\"laborcode\":\"TEST\",\"starttime\":\"2015-08-2 11:11:11\",\"finishtime\":\"2015-08-3 11:11:11\"}]}";
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
    private void showPopupWindow(View view) {

        // 一个自定义的布局，作为显示的内容
        View contentView = LayoutInflater.from(Work_AddNewActivity.this).inflate(
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


        realinfoLinearLayout.setVisibility(View.GONE);
    }

    private View.OnClickListener planOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Work_AddNewActivity.this, Work_PlanActivity.class);
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
            Intent intent = new Intent(Work_AddNewActivity.this, Work_AssignmentActivity.class);
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
            Intent intent = new Intent(Work_AddNewActivity.this, Work_LabtransActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("workOrder", workOrder);
            intent.putExtras(bundle);
            startActivity(intent);
            popupWindow.dismiss();
        }
    };

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

    public class MydateListener implements View.OnClickListener {

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
            if (layoutnum == targstartdatelayout.getId()) {
                targstartdate.setText(sb);
            } else if (layoutnum == targcompdatelayout.getId()) {
                targcompdate.setText(sb);
            } else if (layoutnum == actstartlayout.getId()) {
                actstart.setText(sb);
            } else if(layoutnum == actfinishlayout.getId()){
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
                locationdesc.setText(new LocationDao(Work_AddNewActivity.this).queryByLocation(option.getValue()).description);
                break;
            case Constants.LOCATIONCODE:
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
                Bundle b=data.getExtras();
                ArrayList<Wplabor>wplabors = (ArrayList<Wplabor>) b.getSerializable("wplaborList");
                int i = wplabors.size();
                break;
            default:
                break;
        }
    }
}
