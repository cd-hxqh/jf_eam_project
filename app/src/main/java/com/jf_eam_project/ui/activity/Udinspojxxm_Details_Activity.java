package com.jf_eam_project.ui.activity;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.jf_eam_project.R;
import com.jf_eam_project.config.Constants;
import com.jf_eam_project.model.Udinspoasset;
import com.jf_eam_project.model.Udinspojxxm;
import com.jf_eam_project.utils.MessageUtils;

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
    private EditText descriptionText; //巡检项目标准
    private TextView udinspojxxm2Text; //数值A
    private TextView udinspojxxm3Text; //数值B
    private TextView udinspojxxm4Text; //数值C
    private TextView fillmethodText; //计量单位
    private EditText executionText; //巡检情况描述
    private EditText checkbyText; //巡检人员


    private Udinspojxxm udinspojxxm; //设备备件


    /**
     * 保存按钮*
     */
    private Button submitBtn;


    private ProgressDialog mProgressDialog;

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
    }

    @Override
    protected void findViewById() {
        titleView = (TextView) findViewById(R.id.title_name);
        backImageView = (ImageView) findViewById(R.id.title_back_id);
        editImageView = (ImageView) findViewById(R.id.title_add);


        udinspoassetlinenumText = (TextView) findViewById(R.id.udinspoasset_udinspoassetlinenum_text);
        descriptionText = (EditText) findViewById(R.id.udinspojxxm_description_text);
        udinspojxxm2Text = (TextView) findViewById(R.id.udinspojxxm_udinspojxxm2_text);
        udinspojxxm3Text = (TextView) findViewById(R.id.udinspojxxm_udinspojxxm3_text);
        udinspojxxm4Text = (TextView) findViewById(R.id.udinspojxxm_udinspojxxm4_text);
        fillmethodText = (TextView) findViewById(R.id.udinspojxxm_fillmethod_text);
        executionText = (EditText) findViewById(R.id.udinspojxxm_execution_text);
        checkbyText = (EditText) findViewById(R.id.ud_udinspojxxm4_text);

        submitBtn = (Button) findViewById(R.id.submit_btn_id);

    }


    @Override
    protected void initView() {
        titleView.setText(getString(R.string.udinspojxxm_detail_title));
        backImageView.setOnClickListener(backImageViewOnClickListenrer);
        editImageView.setVisibility(View.VISIBLE);
        editImageView.setImageResource(R.drawable.edit_query);
        editImageView.setOnClickListener(editImageViewOnClickListener);


        if (udinspojxxm != null) {
            udinspoassetlinenumText.setText(udinspojxxm.getUdinspojxxmlinenum() == null ? "" : udinspojxxm.getUdinspojxxmlinenum());
            descriptionText.setText(udinspojxxm.getDescription() == null ? "" : udinspojxxm.getDescription());
            udinspojxxm2Text.setText(udinspojxxm.getUdinspojxxm2() == null ? "" : udinspojxxm.getUdinspojxxm2());
            udinspojxxm3Text.setText(udinspojxxm.getUdinspojxxm3() == null ? "" : udinspojxxm.getUdinspojxxm3());
            udinspojxxm4Text.setText(udinspojxxm.getUdinspojxxm4() == null ? "" : udinspojxxm.getUdinspojxxm4());
            fillmethodText.setText(udinspojxxm.getFillmethod() == null ? "" : udinspojxxm.getFillmethod());
            executionText.setText(udinspojxxm.getExecution()== null ? "" : udinspojxxm.getExecution());
            checkbyText.setText(udinspojxxm.getCheckby()== null ? "" : udinspojxxm.getCheckby());
        }



        descriptionText.setFocusable(false);
        descriptionText.setFocusableInTouchMode(false);
        executionText.setFocusable(false);
        executionText.setFocusableInTouchMode(false);
        checkbyText.setFocusable(false);
        checkbyText.setFocusableInTouchMode(false);

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

        descriptionText.setFocusable(true);
        descriptionText.setFocusableInTouchMode(true);
        executionText.setFocusable(true);
        executionText.setFocusableInTouchMode(true);
        checkbyText.setFocusable(true);
        checkbyText.setFocusableInTouchMode(true);
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
                new AsyncTask<String, String, String>() {
                    @Override
                    protected String doInBackground(String... strings) {
                        String data = null;
                        try {
                            data = submitBtn();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        String result = getBaseApplication().getWsService().UpdatePO(data,"");

                        return result;
                    }

                    @Override
                    protected void onPostExecute(String s) {
                        super.onPostExecute(s);
                        mProgressDialog.dismiss();

                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            String success = jsonObject.getString("status");
                            String errorNo = jsonObject.getString("errorNo");
                            Log.i(TAG, "success=" + success + ",errorNo=" + errorNo);
                            if (success.equals("数据更新成功") && errorNo.equals("0")) {
                                MessageUtils.showMiddleToast(Udinspojxxm_Details_Activity.this, "数据更新成功");
                            } else {
                                MessageUtils.showMiddleToast(Udinspojxxm_Details_Activity.this, "数据更新失败");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }.execute();
            }
        }).create().show();


    }




    /**
     * 保存数据*
     */
    private String submitBtn() throws JSONException {
        String description = descriptionText.getText().toString();
        String execution = executionText.getText().toString();
        String checkby = checkbyText.getText().toString();

        Udinspojxxm udinspojxxm1 = new Udinspojxxm();
        JSONObject json = new JSONObject();

        udinspojxxm1.setUdinspojxxmid(udinspojxxm.udinspojxxmid);
        json.put("UDINSPOJXXMID", udinspojxxm.udinspojxxmid+"");
        udinspojxxm1.setUdinspoassetnum(udinspojxxm.udinspoassetnum);
        json.put("UDINSPOASSETNUM",udinspojxxm.udinspoassetnum);
        udinspojxxm1.setType(Constants.UPDATE);
        json.put("TYPE", Constants.UPDATE);
        if (!description.equals(udinspojxxm.description)) {
            udinspojxxm1.setDescription(description);
            json.put("DESCRIPTION", description);
        }
        if (!execution.equals(udinspojxxm.execution)) {
            udinspojxxm1.setExecution(execution);
            json.put("EXECUTION", execution);
        }
        if (!checkby.equals(udinspojxxm.checkby)) {
            udinspojxxm1.setCheckby(checkby);
            json.put("CHECKBY", checkby);
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









}
