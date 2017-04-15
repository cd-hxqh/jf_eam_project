package com.jf_eam_project.ui.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.flyco.animation.BaseAnimatorSet;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.NormalDialog;
import com.jf_eam_project.R;
import com.jf_eam_project.api.JsonUtils;
import com.jf_eam_project.bean.Wlh;
import com.jf_eam_project.model.WorkOrder;
import com.jf_eam_project.utils.AccountUtils;
import com.jf_eam_project.utils.GetNowTime;
import com.jf_eam_project.utils.MessageUtils;

import java.util.ArrayList;
import java.util.List;

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

    private PopupWindow popupWindow;

    private TextView wlTextView;//物料
    private TextView gjTextView;//工具

    private List<Wlh> wlhs = new ArrayList<>();


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

        submitBtn = (Button) findViewById(R.id.submit_add_id);
    }

    @Override
    protected void initView() {
        titlename.setText(R.string.meterial_title);
        menuImageView.setImageResource(R.drawable.ic_drawer);
        menuImageView.setVisibility(View.VISIBLE);
        menuImageView.setOnClickListener(menuImageViewOnClickListener);
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

    private View.OnClickListener menuImageViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (isshow()) {
                showPopupWindow(menuImageView);
            } else {
                MessageUtils.showMiddleToast(Meterial_AddActivity.this, getString(R.string.llxz_bt_hint_text));
            }


        }
    };


    /**
     * 判断描述与关联工单号不能为空
     */

    private boolean isshow() {
        if (description.getText().toString().equals("") || udwonumText.getText().toString().equals("")) {
            return false;
        }
        return true;
    }


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
            case WORKORDER_CODE: //管理工单号
                WorkOrder workOrder = (WorkOrder) data.getSerializableExtra("option");
                udwonumText.setText(workOrder.getWonum());
                break;
            case Wlh_ListActivity.WLH_CODE: //物料行
                wlhs = (List<Wlh>) data.getSerializableExtra("wlhs");


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

        }

        ;
    };

    /**
     * 提交数据*
     */
    private void startAsyncTask() {

        final String updataInfo = JsonUtils.potoWorlOrder(getWorkOrderInfo(), wlhs);
        new AsyncTask<String, String, String>() {
            @Override
            protected String doInBackground(String... strings) {
                String addresult = getBaseApplication().getWsService().InsertGENERAL(Meterial_AddActivity.this, updataInfo, "WORKORDER", "WONUM", AccountUtils.getPersonId(Meterial_AddActivity.this));
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
        WorkOrder workOrder = new WorkOrder();
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


    /**
     * 初始化showPopupWindow*
     */
    private void showPopupWindow(View view) {

        // 一个自定义的布局，作为显示的内容
        View contentView = LayoutInflater.from(Meterial_AddActivity.this).inflate(
                R.layout.lld_popup_window, null);


        popupWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setTouchable(true);
        popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setTouchInterceptor(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });

        // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
        // 我觉得这里是API的一个bug
        popupWindow.setBackgroundDrawable(getResources().getDrawable(
                R.drawable.popup_background_mtrl_mult));

        // 设置好参数之后再show
        popupWindow.showAsDropDown(view);

        wlTextView = (TextView) contentView.findViewById(R.id.wpmaterial_text_id);
        gjTextView = (TextView) contentView.findViewById(R.id.gj_text_id);

        wlTextView.setOnClickListener(wlTextViewOnClickListener);

    }

    private View.OnClickListener wlTextViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Meterial_AddActivity.this, Wlh_ListActivity.class);
            startActivityForResult(intent, 0);
            popupWindow.dismiss();
        }
    };


}
