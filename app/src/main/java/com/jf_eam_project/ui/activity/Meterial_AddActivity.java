package com.jf_eam_project.ui.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.flyco.animation.BaseAnimatorSet;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.NormalDialog;
import com.jf_eam_project.R;
import com.jf_eam_project.api.JsonUtils;
import com.jf_eam_project.model.WorkOrder;
import com.jf_eam_project.utils.AccountUtils;
import com.jf_eam_project.utils.GetNowTime;
import com.jf_eam_project.utils.MessageUtils;

/**
 * Created by think on 2015/11/30.
 * 新增领料单界面
 */
public class Meterial_AddActivity extends BaseActivity {

    private static final String TAG = "Meterial_AddActivity";

    public static final int WORKORDER_CODE = 1013; //关联工单请求码

    private TextView titlename; //标题
    private ImageView menuImageView; //菜单按钮
    private ImageView backlayout; //返回按钮

    private EditText description;//描述

    private TextView udwonumText; //关联工单号

    private TextView displaynameText; //申请人

    private TextView createdateText; //创建时间

    private TextView fgsText; //分公司

    private TextView uddeptdesText; //运行单位

    private Button submitBtn; //提交按钮

    private BaseAnimatorSet mBasIn;
    private BaseAnimatorSet mBasOut;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_add);

        findViewById();
        initView();
    }


    @Override
    protected void findViewById() {
        titlename = (TextView) findViewById(R.id.title_name);
        backlayout = (ImageView) findViewById(R.id.title_back_id);
        menuImageView = (ImageView) findViewById(R.id.title_add);

        description = (EditText) findViewById(R.id.description_text);
        udwonumText = (TextView) findViewById(R.id.udwonum_text_id);
        displaynameText = (TextView) findViewById(R.id.displayname_text_id);
        createdateText = (TextView) findViewById(R.id.createdate_text_id);
        fgsText = (TextView) findViewById(R.id.fgs_text_id);
        uddeptdesText = (TextView) findViewById(R.id.uddeptdes_text_id);

        submitBtn=(Button)findViewById(R.id.submit_add_id);
    }

    @Override
    protected void initView() {
        titlename.setText(R.string.meterial_title);
//        menuImageView.setImageResource(R.drawable.ic_drawer);
//        menuImageView.setVisibility(View.VISIBLE);
//        menuImageView.setOnClickListener(menuImageViewOnClickListener);
        backlayout.setOnClickListener(backlayoutOnClickListener);
        udwonumText.setOnClickListener(udwonumTextOnClickListener);
        displaynameText.setText(AccountUtils.getDisplayName(Meterial_AddActivity.this));
        createdateText.setText(GetNowTime.getTime());
        fgsText.setText(getfgsBh(AccountUtils.getDepartment(Meterial_AddActivity.this)));
        uddeptdesText.setText(AccountUtils.getDepartmentms(Meterial_AddActivity.this));

        submitBtn.setOnClickListener(submitBtnOnClickListener);



    }


    private View.OnClickListener backlayoutOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            finish();
        }
    };


    /**
     * 获取分公司编号
     **/
    private String getfgsBh(String department) {
        return department.substring(0, 5);

    }


    /**
     * 点击关联工单号
     **/
    private View.OnClickListener udwonumTextOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Meterial_AddActivity.this, WorkOrder_Choose_Activity.class);
            startActivityForResult(intent, 0);
        }
    };


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case WORKORDER_CODE:
                WorkOrder workOrder = (WorkOrder) data.getSerializableExtra("option");
                udwonumText.setText(workOrder.getWonum());
                break;

        }
    }


    //提交数据
    private View.OnClickListener submitBtnOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            final NormalDialog dialog = new NormalDialog(Meterial_AddActivity.this);
            dialog.content(getString(R.string.lyd_xj_hint))//
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

        };
    };

    /**
     * 提交数据*
     */
    private void startAsyncTask() {

        final String updataInfo = JsonUtils.potoWorlOrder(getWorkOrderInfo());
        Log.i(TAG, "updataInfo=" + updataInfo);
        new AsyncTask<String, String, String>() {
            @Override
            protected String doInBackground(String... strings) {
                String addresult = getBaseApplication().getWsService().InsertGENERAL(Meterial_AddActivity.this, updataInfo,"WORKORDER","WONUM",AccountUtils.getPersonId(Meterial_AddActivity.this));
                Log.i(TAG, "addresult=" + addresult);
                return addresult;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if (s == null || s.equals("")) {
                    MessageUtils.showMiddleToast(Meterial_AddActivity.this, "新增失败");
                } else if (!s.isEmpty()) {
                    MessageUtils.showMiddleToast(Meterial_AddActivity.this, s);
                } else {
                    Toast.makeText(Meterial_AddActivity.this, "新增" + s + "成功", Toast.LENGTH_SHORT).show();
                }
                closeProgressDialog();
            }
        }.execute();
    }

    //获取界面信息
    private WorkOrder getWorkOrderInfo() {
        String desction = description.getText().toString(); //描述
        String udwonum = udwonumText.getText().toString(); //关联工单号
        String displayname = displaynameText.getText().toString(); //申请人
        String createdate = createdateText.getText().toString(); //创建时间
        String fgs = fgsText.getText().toString(); //分公司
        String uddeptdes = uddeptdesText.getText().toString(); //运行单位
        WorkOrder workOrder=new WorkOrder();
        workOrder.setDescription(desction);
        workOrder.setUdwonum(udwonum);
        workOrder.setDisplayname(AccountUtils.getPersonId(Meterial_AddActivity.this));
        workOrder.setCreatedate(createdate);
        workOrder.setUdbelong_description(fgs);
        workOrder.setUdbelong(AccountUtils.getDepartment(Meterial_AddActivity.this));
        workOrder.setUdapptype("UDMATAPP"); //领料单类型
        workOrder.setStatus("等待核准"); //状态
        return workOrder;

    }
}
