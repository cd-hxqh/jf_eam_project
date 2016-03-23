package com.jf_eam_project.ui.activity;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.jf_eam_project.Dao.UdinspoDao;
import com.jf_eam_project.Dao.UdinspojxxmDao;
import com.jf_eam_project.R;
import com.jf_eam_project.config.Constants;
import com.jf_eam_project.model.Udinspo;
import com.jf_eam_project.model.Udinspoasset;
import com.jf_eam_project.model.Udinspojxxm;
import com.jf_eam_project.utils.MessageUtils;
import com.jf_eam_project.utils.NetWorkHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 检修项目标准
 */
public class Udinspojxxm_Details_Activity extends BaseActivity {

    private static final String TAG = "Udinspojxxm_Details_Activity";

    /**
     * 标题*
     */
    private TextView titleView;
    /**
     * 返回按钮*
     */
    private ImageView backImageView;

    /**
     * 编辑*
     */
    private ImageView editImageView;


    /**
     * 界面信息显示*
     */
    private TextView udinspoassetlinenumText; //序号

    private TextView descriptionText; //检查项目

    private TextView udinspojxxm7Text; //检查内容

    private TextView udinspojxxm9Text; //检查标准

    private TextView udinspojxxm8Text; //检查方法


    /**
     * 描述*
     */
    private View executionView;
    private LinearLayout executionLinearLayout;
    private EditText executionText;

    /**
     * 正常异常*
     */
    private View udinspojxxm1View;
    private LinearLayout udinspojxxm1LinearLayout;
    private TextView udinspojxxm1Text;


    /**
     * 数值A*
     */
    private View udinspojxxm2View;
    private LinearLayout udinspojxxm2LinearLayout;
    private TextView udinspojxxm2Text;

    /**
     * 数值B*
     */
    private View udinspojxxm3View;
    private LinearLayout udinspojxxm3LinearLayout;
    private TextView udinspojxxm3Text;

    /**
     * 数值C*
     */
    private View udinspojxxm4View;
    private LinearLayout udinspojxxm4LinearLayout;
    private TextView udinspojxxm4Text; //数值C


    private Udinspojxxm udinspojxxm; //设备备件


    /**
     * 保存按钮*
     */
    private Button submitBtn;
    /**
     * 删除按钮*
     */
    private Button deleteBtn;


    private ProgressDialog mProgressDialog;

    /**
     * 填写方式*
     */
    private String writemethod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_udinspojxxm_details);
        initData();
        findViewById();
        initView();
    }

    /**
     * 获取初始话数据*
     */
    private void initData() {
        udinspojxxm = (Udinspojxxm) getIntent().getSerializableExtra("Udinspojxxm");
        writemethod = udinspojxxm.writemethod;
        Log.i(TAG, "writemethod=" + writemethod);
    }

    @Override
    protected void findViewById() {
        titleView = (TextView) findViewById(R.id.title_name);
        backImageView = (ImageView) findViewById(R.id.title_back_id);
        editImageView = (ImageView) findViewById(R.id.title_add);


        udinspoassetlinenumText = (TextView) findViewById(R.id.udinspoasset_udinspoassetlinenum_text);
        descriptionText = (TextView) findViewById(R.id.udinspojxxm_description_text);
        udinspojxxm7Text = (TextView) findViewById(R.id.udinspojxxm7_text);
        udinspojxxm9Text = (TextView) findViewById(R.id.udinspojxxm9_text);
        udinspojxxm8Text = (TextView) findViewById(R.id.udinspojxxm8_text);

        executionView = (View) findViewById(R.id.execution_view);
        executionLinearLayout = (LinearLayout) findViewById(R.id.execution_linearlayout);
        executionText = (EditText) findViewById(R.id.execution_text);

        udinspojxxm1View = (View) findViewById(R.id.udinspojxxm1_view);
        udinspojxxm1LinearLayout = (LinearLayout) findViewById(R.id.udinspojxxm1_linearlayout);
        udinspojxxm1Text = (EditText) findViewById(R.id.udinspojxxm_udinspojxxm1_text);


        udinspojxxm2View = (View) findViewById(R.id.udinspojxxm2_view);
        udinspojxxm2LinearLayout = (LinearLayout) findViewById(R.id.udinspojxxm2_linearlayout);
        udinspojxxm2Text = (EditText) findViewById(R.id.udinspojxxm2_text);

        udinspojxxm3View = (View) findViewById(R.id.udinspojxxm3_view);
        udinspojxxm3LinearLayout = (LinearLayout) findViewById(R.id.udinspojxxm3_linearlayout);
        udinspojxxm3Text = (EditText) findViewById(R.id.udinspojxxm3_text);

        udinspojxxm4View = (View) findViewById(R.id.udinspojxxm4_view);
        udinspojxxm4LinearLayout = (LinearLayout) findViewById(R.id.udinspojxxm4_linearlayout);
        udinspojxxm4Text = (EditText) findViewById(R.id.udinspojxxm4_text);

        submitBtn = (Button) findViewById(R.id.submit_btn_id);
//        deleteBtn = (Button) findViewById(R.id.submit_btn_id);

    }


    @Override
    protected void initView() {
        titleView.setText(getString(R.string.udinspojxxm_detail_title));
        backImageView.setOnClickListener(backImageViewOnClickListenrer);
        editImageView.setVisibility(View.VISIBLE);
        editImageView.setImageResource(R.drawable.edit_query);
        editImageView.setOnClickListener(editImageViewOnClickListener);


        if (writemethod.equals("01")) {
            udinspojxxm2View.setVisibility(View.VISIBLE);
            udinspojxxm2LinearLayout.setVisibility(View.VISIBLE);
        } else if (writemethod.equals("02")) {
            udinspojxxm2View.setVisibility(View.VISIBLE);
            udinspojxxm2LinearLayout.setVisibility(View.VISIBLE);
            udinspojxxm3View.setVisibility(View.VISIBLE);
            udinspojxxm3LinearLayout.setVisibility(View.VISIBLE);
        } else if (writemethod.equals("03")) {
            udinspojxxm2View.setVisibility(View.VISIBLE);
            udinspojxxm2LinearLayout.setVisibility(View.VISIBLE);
            udinspojxxm3View.setVisibility(View.VISIBLE);
            udinspojxxm3LinearLayout.setVisibility(View.VISIBLE);
            udinspojxxm4View.setVisibility(View.VISIBLE);
            udinspojxxm4LinearLayout.setVisibility(View.VISIBLE);
        } else if (writemethod.equals("04")) {
            udinspojxxm1View.setVisibility(View.VISIBLE);
            udinspojxxm1LinearLayout.setVisibility(View.VISIBLE);
        } else if (writemethod.equals("05")) {
            executionView.setVisibility(View.VISIBLE);
            executionLinearLayout.setVisibility(View.VISIBLE);
        }


        if (udinspojxxm != null) {
            udinspoassetlinenumText.setText(udinspojxxm.getUdinspojxxmlinenum() == null ? "" : udinspojxxm.getUdinspojxxmlinenum());
            descriptionText.setText(udinspojxxm.getDescription() == null ? "" : udinspojxxm.getDescription());
            udinspojxxm7Text.setText(udinspojxxm.getUdinspojxxm7() == null ? "" : udinspojxxm.getUdinspojxxm7());
            udinspojxxm8Text.setText(udinspojxxm.getUdinspojxxm8() == null ? "" : udinspojxxm.getUdinspojxxm8());
            udinspojxxm9Text.setText(udinspojxxm.getUdinspojxxm9() == null ? "" : udinspojxxm.getUdinspojxxm9());
            executionText.setText(udinspojxxm.getExecution() == null ? "" : udinspojxxm.getExecution());


//            udinspojxxm2Text.setText(udinspojxxm.getUdinspojxxm2() == null ? "" : udinspojxxm.getUdinspojxxm2());
//            udinspojxxm3Text.setText(udinspojxxm.getUdinspojxxm3() == null ? "" : udinspojxxm.getUdinspojxxm3());
//            udinspojxxm4Text.setText(udinspojxxm.getUdinspojxxm4() == null ? "" : udinspojxxm.getUdinspojxxm4());
        }


        executionText.setFocusable(false);
        executionText.setFocusableInTouchMode(false);
        udinspojxxm1Text.setFocusable(false);
        udinspojxxm1Text.setFocusableInTouchMode(false);
        udinspojxxm2Text.setFocusable(false);
        udinspojxxm2Text.setFocusableInTouchMode(false);
        udinspojxxm3Text.setFocusable(false);
        udinspojxxm3Text.setFocusableInTouchMode(false);
        udinspojxxm4Text.setFocusable(false);
        udinspojxxm4Text.setFocusableInTouchMode(false);

        submitBtn.setOnClickListener(submitBtnOnClickListener);

    }


    private View.OnClickListener editImageViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            submitBtn.setVisibility(View.VISIBLE);
            //设置编辑状态
            statusEdit();
        }
    };

    private void statusEdit() {

        executionText.setFocusable(true);
        executionText.setFocusableInTouchMode(true);
        udinspojxxm1Text.setFocusable(true);
        udinspojxxm1Text.setFocusableInTouchMode(true);
        udinspojxxm2Text.setFocusable(true);
        udinspojxxm2Text.setFocusableInTouchMode(true);
        udinspojxxm3Text.setFocusable(true);
        udinspojxxm3Text.setFocusableInTouchMode(true);
        udinspojxxm4Text.setFocusable(true);
        udinspojxxm4Text.setFocusableInTouchMode(true);
    }


    /**
     * 返回按钮*
     */
    private View.OnClickListener backImageViewOnClickListenrer = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };

    /**
     * 保存按钮*
     */
    private View.OnClickListener submitBtnOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            encapsulationData();
        }
    };


    /**
     * 数据封装*
     */
    private void encapsulationData() {

        AlertDialog.Builder builder = new AlertDialog.Builder(Udinspojxxm_Details_Activity.this);
        builder.setMessage("确定更新检修项目标准吗？").setTitle("提示")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                mProgressDialog = ProgressDialog.show(Udinspojxxm_Details_Activity.this, null,
                        getString(R.string.inputing), true, true);
                mProgressDialog.setCanceledOnTouchOutside(false);
                mProgressDialog.setCancelable(false);

                if (NetWorkHelper.isNetwork(Udinspojxxm_Details_Activity.this)) {
                    MessageUtils.showMiddleToast(Udinspojxxm_Details_Activity.this, "暂无网络,现离线保存数据!");
                    mProgressDialog.dismiss();
                    saveUdinspo();
                    setResult(Constants.REFRESH);
                    finish();
                } else {


                    new AsyncTask<String, String, String>() {
                        @Override
                        protected String doInBackground(String... strings) {
                            String data = null;
                            try {
                                data = submitBtn();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            String result = getBaseApplication().getWsService().UpdatePO(data, "");

                            return result;
                        }

                        @Override
                        protected void onPostExecute(String s) {

                            Log.i(TAG,"s="+s);
                            super.onPostExecute(s);
                            mProgressDialog.dismiss();
                            try {
                                JSONObject jsonObject = new JSONObject(s);
                                String success = jsonObject.getString("status");
                                String errorNo = jsonObject.getString("errorNo");
                                if (success.equals("数据更新成功") && errorNo.equals("0")) {
                                    MessageUtils.showMiddleToast(Udinspojxxm_Details_Activity.this, "数据更新成功");

                                } else {
                                    MessageUtils.showMiddleToast(Udinspojxxm_Details_Activity.this, "数据更新失败");
                                }
                                setResult(Constants.REFRESH);
                                finish();
                            } catch (JSONException e) {
                                e.printStackTrace();
                                MessageUtils.showMiddleToast(Udinspojxxm_Details_Activity.this, "数据更新失败");
                                setResult(Constants.REFRESH);
                                finish();
                            }


                        }
                    }.execute();
                }
            }
        }).create().show();


    }


    /**
     * 保存数据*
     */
    private String submitBtn() throws JSONException {

        Udinspojxxm udinspojxxm1 = new Udinspojxxm();
        JSONObject json = new JSONObject();

        udinspojxxm1.setUdinspojxxmid(udinspojxxm.udinspojxxmid);
        json.put("UDINSPOJXXMID", udinspojxxm.udinspojxxmid + "");
        udinspojxxm1.setUdinspoassetnum(udinspojxxm.udinspoassetnum);
        json.put("UDINSPOASSETNUM", udinspojxxm.udinspoassetnum);
        udinspojxxm1.setType(Constants.UPDATE);
        json.put("TYPE", Constants.UPDATE);


        if (writemethod.equals("01")) {
            String udinspojxxm2 = udinspojxxm2Text.getText().toString();
            if (!udinspojxxm2.equals(udinspojxxm.udinspojxxm2) && TextUtils.isEmpty(udinspojxxm2)) {
                udinspojxxm1.setUdinspojxxm2(udinspojxxm2);
                json.put("UDINSPOJXXM2", udinspojxxm2);
            }

        }else if (writemethod.equals("02")) {
            String udinspojxxm2 = udinspojxxm2Text.getText().toString();
            if (!udinspojxxm2.equals(udinspojxxm.udinspojxxm2) && TextUtils.isEmpty(udinspojxxm2)) {
                udinspojxxm1.setUdinspojxxm2(udinspojxxm2);
                json.put("UDINSPOJXXM2", udinspojxxm2);
            }
            String udinspojxxm3 = udinspojxxm3Text.getText().toString();
            if (!udinspojxxm3.equals(udinspojxxm.udinspojxxm3) && TextUtils.isEmpty(udinspojxxm3)) {
                udinspojxxm1.setUdinspojxxm3(udinspojxxm3);
                json.put("UDINSPOJXXM3", udinspojxxm3);
            }
        }
        else if (writemethod.equals("03")) {
            String udinspojxxm2 = udinspojxxm2Text.getText().toString();
            if (!udinspojxxm2.equals(udinspojxxm.udinspojxxm2) && TextUtils.isEmpty(udinspojxxm2)) {
                udinspojxxm1.setUdinspojxxm2(udinspojxxm2);
                json.put("UDINSPOJXXM2", udinspojxxm2);
            }
            String udinspojxxm3 = udinspojxxm3Text.getText().toString();
            if (!udinspojxxm3.equals(udinspojxxm.udinspojxxm3) && TextUtils.isEmpty(udinspojxxm3)) {
                udinspojxxm1.setUdinspojxxm3(udinspojxxm3);
                json.put("UDINSPOJXXM3", udinspojxxm3);
            }
            String udinspojxxm4 = udinspojxxm3Text.getText().toString();
            if (!udinspojxxm4.equals(udinspojxxm.udinspojxxm4) && TextUtils.isEmpty(udinspojxxm4)) {
                udinspojxxm1.setUdinspojxxm4(udinspojxxm4);
                json.put("UDINSPOJXXM4", udinspojxxm4);
            }
        }

        else if (writemethod.equals("04")) {
            String udinspojxxm = udinspojxxm1Text.getText().toString();
            if (!udinspojxxm.equals(udinspojxxm1.udinspojxxm1) && TextUtils.isEmpty(udinspojxxm)) {
                udinspojxxm1.setUdinspojxxm1(udinspojxxm);
                json.put("UDINSPOJXXM1", udinspojxxm);
            }

        }
        else if (writemethod.equals("05")) {
            String execution = executionText.getText().toString();
            if (!execution.equals(udinspojxxm1.execution) && TextUtils.isEmpty(execution)) {
                udinspojxxm1.setExecution(execution);
                json.put("EXECUTION", execution);
            }

        }


        JSONObject jsonObject = new JSONObject();
        jsonObject.put("GRADESON", jsonUdinspojxxmInfo(json.toString()));


        return jsonObject.toString();
    }


    /**
     * 封装udinspojxxm信息*
     */
    private JSONArray jsonUdinspojxxmInfo(String str) {
        JSONArray jsonArray = null;

        String json3 = "";
        try {
            json3 = "[" + str + "]";

            jsonArray = new JSONArray(json3);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return jsonArray;
    }


    /**
     * 将巡检信息保存至本地*
     */
    private void saveUdinspo() {
        Udinspojxxm udinspojxxm1 = new Udinspojxxm();
        if (writemethod.equals("01")) {
            String udinspojxxm2 = udinspojxxm2Text.getText().toString();
            if (!udinspojxxm2.equals(udinspojxxm.udinspojxxm2) && TextUtils.isEmpty(udinspojxxm2)) {
                udinspojxxm1.setUdinspojxxm2(udinspojxxm2);
            }

        }else if (writemethod.equals("02")) {
            String udinspojxxm2 = udinspojxxm2Text.getText().toString();
            if (!udinspojxxm2.equals(udinspojxxm.udinspojxxm2) && TextUtils.isEmpty(udinspojxxm2)) {
                udinspojxxm1.setUdinspojxxm2(udinspojxxm2);
            }
            String udinspojxxm3 = udinspojxxm3Text.getText().toString();
            if (!udinspojxxm3.equals(udinspojxxm.udinspojxxm3) && TextUtils.isEmpty(udinspojxxm3)) {
                udinspojxxm1.setUdinspojxxm3(udinspojxxm3);
            }
        }
        else if (writemethod.equals("03")) {
            String udinspojxxm2 = udinspojxxm2Text.getText().toString();
            if (!udinspojxxm2.equals(udinspojxxm.udinspojxxm2) && TextUtils.isEmpty(udinspojxxm2)) {
                udinspojxxm1.setUdinspojxxm2(udinspojxxm2);
            }
            String udinspojxxm3 = udinspojxxm3Text.getText().toString();
            if (!udinspojxxm3.equals(udinspojxxm.udinspojxxm3) && TextUtils.isEmpty(udinspojxxm3)) {
                udinspojxxm1.setUdinspojxxm3(udinspojxxm3);
            }
            String udinspojxxm4 = udinspojxxm3Text.getText().toString();
            if (!udinspojxxm4.equals(udinspojxxm.udinspojxxm4) && TextUtils.isEmpty(udinspojxxm4)) {
                udinspojxxm1.setUdinspojxxm4(udinspojxxm4);
            }
        }

        else if (writemethod.equals("04")) {
            String udinspojxxm = udinspojxxm1Text.getText().toString();
            if (!udinspojxxm.equals(udinspojxxm1.udinspojxxm1) && TextUtils.isEmpty(udinspojxxm)) {
                udinspojxxm1.setUdinspojxxm1(udinspojxxm);
            }

        }
        else if (writemethod.equals("05")) {
            String execution = executionText.getText().toString();
            if (!execution.equals(udinspojxxm1.execution) && TextUtils.isEmpty(execution)) {
                udinspojxxm1.setExecution(execution);
            }

        }

        udinspojxxm1.setUdinspojxxmid(udinspojxxm.udinspojxxmid);
        udinspojxxm1.setUdinspojxxmlinenum(udinspojxxm.udinspojxxmlinenum);
        udinspojxxm1.setUdinspoassetnum(udinspojxxm.udinspoassetnum);
        udinspojxxm1.setType(Constants.UPDATE);
        new UdinspojxxmDao(Udinspojxxm_Details_Activity.this).insert(udinspojxxm1);
    }

}
