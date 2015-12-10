package com.jf_eam_project.ui.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jf_eam_project.R;
import com.jf_eam_project.model.Webservice_result;
import com.jf_eam_project.model.WorkOrder;

import org.json.JSONException;
import org.json.JSONObject;

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
    private ProgressDialog mProgressDialog;

    private Button addnew;
    /**
     * 工作计划*
     */
    private LinearLayout planLinearlayout;
    /**
     * 任务分配*
     */
    private LinearLayout taskLinearLayout;
    /**
     * 实际情况
     */
    private LinearLayout realinfoLinearLayout;
    /**
     * 故障汇报*
     */
    private LinearLayout reportLinearLayout;

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

        addnew = (Button) findViewById(R.id.work_add);
    }

    @Override
    protected void initView() {
        titlename.setText(R.string.title_activity_work_addnew);
        menuImageView.setImageResource(R.drawable.ic_drawer);
        menuImageView.setVisibility(View.VISIBLE);
        menuImageView.setOnClickListener(menuImageViewOnClickListener);

        addnew.setOnClickListener(addnewlistener);

        backlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
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

        planLinearlayout = (LinearLayout) contentView.findViewById(R.id.work_plan_id);
        taskLinearLayout = (LinearLayout) contentView.findViewById(R.id.work_task_id);
        realinfoLinearLayout = (LinearLayout) contentView.findViewById(R.id.work_realinfo_id);
        reportLinearLayout = (LinearLayout) contentView.findViewById(R.id.work_report_id);
        planLinearlayout.setOnClickListener(planOnClickListener);
        taskLinearLayout.setOnClickListener(taskOnClickListener);
        realinfoLinearLayout.setOnClickListener(realinfoOnClickListener);
        reportLinearLayout.setOnClickListener(reportOnClickListener);

    }

    private View.OnClickListener planOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Work_AddNewActivity.this, Work_PlanActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("workOrder", workOrder);
            intent.putExtras(bundle);
            startActivity(intent);
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
            Intent intent = new Intent(Work_AddNewActivity.this, Work_RealInfoActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("workOrder", workOrder);
            intent.putExtras(bundle);
            startActivity(intent);
            popupWindow.dismiss();
        }
    };

    private View.OnClickListener reportOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Work_AddNewActivity.this,Work_FailurereportActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("workOrder", workOrder);
            intent.putExtras(bundle);
            startActivity(intent);
            popupWindow.dismiss();
        }
    };
}
