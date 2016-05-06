package com.jf_eam_project.ui.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.flyco.animation.BaseAnimatorSet;
import com.flyco.animation.BounceEnter.BounceTopEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.ActionSheetDialog;
import com.flyco.dialog.widget.NormalDialog;
import com.jf_eam_project.Dao.CreatereportDao;
import com.jf_eam_project.Dao.UdinspojxxmDao;
import com.jf_eam_project.R;
import com.jf_eam_project.config.Constants;
import com.jf_eam_project.model.Createreport;
import com.jf_eam_project.model.Udinspojxxm;
import com.jf_eam_project.ui.adapter.GridAdapter;
import com.jf_eam_project.utils.Bimp;
import com.jf_eam_project.utils.DataUtils;
import com.jf_eam_project.utils.MessageUtils;
import com.jf_eam_project.utils.NetWorkHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 检修项目标准
 */
public class Udinspojxxm_Details_Activity extends BaseActivity {

    private static final String TAG = "Udinspojxxm_Details_Activity";
    private static final int ADD_REPORT = 1; //提报单新增
    private static final int DETAILS_REPORT = 2; //提报单详情

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
    private RadioGroup mRadioGroup1;
    private RadioButton normalRadioButton; //正常
    private RadioButton abnormalRadioButton; //异常常

//    private TextView udinspojxxm1Text;


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


    private GridView noScrollgridview;
    private GridAdapter adapter; //相片选择


    private Udinspojxxm udinspojxxm; //设备备件

    private BaseAnimatorSet mBasIn;
    private BaseAnimatorSet mBasOut;
    /**
     * 保存按钮*
     */
    private Button submitBtn;
    /**
     * 删除按钮*
     */
    private Button deleteBtn;


    /**提报单**/
    private Button reportBtn;


    private ProgressDialog mProgressDialog;

    /**
     * 填写方式*
     */
    private String writemethod = "";

    private String udinspojxxmvalue = "";

    public static int max = 0;
    public static List<Bitmap> bmp = new ArrayList<Bitmap>();
    public static HashMap<String, Boolean> mHashMap = new HashMap<String, Boolean>();
    //
    //图片sd地址  上传服务器时把图片调用下面方法压缩后 保存到临时文件夹 图片压缩后小于100KB，失真度不明显
    public static List<String> drr = new ArrayList<String>();


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
        mRadioGroup1 = (RadioGroup) findViewById(R.id.gendergroup);
        normalRadioButton = (RadioButton) findViewById(R.id.udinspojxxm_normal_text);
        abnormalRadioButton = (RadioButton) findViewById(R.id.udinspojxxm_abnormal_text);


        udinspojxxm2View = (View) findViewById(R.id.udinspojxxm2_view);
        udinspojxxm2LinearLayout = (LinearLayout) findViewById(R.id.udinspojxxm2_linearlayout);
        udinspojxxm2Text = (EditText) findViewById(R.id.udinspojxxm2_text);

        udinspojxxm3View = (View) findViewById(R.id.udinspojxxm3_view);
        udinspojxxm3LinearLayout = (LinearLayout) findViewById(R.id.udinspojxxm3_linearlayout);
        udinspojxxm3Text = (EditText) findViewById(R.id.udinspojxxm3_text);

        udinspojxxm4View = (View) findViewById(R.id.udinspojxxm4_view);
        udinspojxxm4LinearLayout = (LinearLayout) findViewById(R.id.udinspojxxm4_linearlayout);
        udinspojxxm4Text = (EditText) findViewById(R.id.udinspojxxm4_text);

        noScrollgridview = (GridView) findViewById(R.id.noScrollgridview);

        submitBtn = (Button) findViewById(R.id.submit_btn_id);
        reportBtn = (Button) findViewById(R.id.report_btn);
//        deleteBtn = (Button) findViewById(R.id.submit_btn_id);

    }


    @Override
    protected void initView() {
        titleView.setText(getString(R.string.udinspojxxm_detail_title));
        backImageView.setOnClickListener(backImageViewOnClickListenrer);
        editImageView.setVisibility(View.VISIBLE);
        editImageView.setImageResource(R.drawable.ic_report);
        editImageView.setOnClickListener(editImageViewOnClickListener);


        submitBtn.setVisibility(View.VISIBLE);

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


//        executionText.setFocusable(false);
//        executionText.setFocusableInTouchMode(false);
//        udinspojxxm1Text.setFocusable(false);
//        udinspojxxm1Text.setFocusableInTouchMode(false);
//        udinspojxxm2Text.setFocusable(false);
//        udinspojxxm2Text.setFocusableInTouchMode(false);
//        udinspojxxm3Text.setFocusable(false);
//        udinspojxxm3Text.setFocusableInTouchMode(false);
//        udinspojxxm4Text.setFocusable(false);
//        udinspojxxm4Text.setFocusableInTouchMode(false);
        normalRadioButton.setChecked(true);
        mRadioGroup1.setOnCheckedChangeListener(radiogpchange);
        submitBtn.setOnClickListener(submitBtnOnClickListener);


        noScrollgridview.setSelector(new ColorDrawable(Color.TRANSPARENT));
        adapter = new GridAdapter(this);
        adapter.update();
        noScrollgridview.setAdapter(adapter);
        noScrollgridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                if (arg2 == Udinspojxxm_Details_Activity.bmp.size()) {
                    ActionSheetDialog();
                } else {
                    Intent intent = new Intent(Udinspojxxm_Details_Activity.this,
                            PhotoActivity.class);
                    intent.putExtra("ID", arg2);
                    startActivity(intent);
                }
            }
        });


        mBasIn = new BounceTopEnter();
        mBasOut = new SlideBottomExit();

        reportBtn.setOnClickListener(editImageViewOnClickListener);

    }


    private RadioGroup.OnCheckedChangeListener radiogpchange = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
            if (checkedId == normalRadioButton.getId()) {
                udinspojxxmvalue = "正常";

            } else if (checkedId == abnormalRadioButton.getId()) {
                udinspojxxmvalue = "异常";
            }
        }
    };


    private View.OnClickListener editImageViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
//            submitBtn.setVisibility(View.VISIBLE);
//            //设置编辑状态
//            statusEdit();
            if (udinspojxxm.reportnum.equals("")) {
                Createreport createreport = isData();
                if (null == createreport) {
                    Intent intent = new Intent(Udinspojxxm_Details_Activity.this, Createreport_Activity.class);
                    intent.putExtra("udinspojxxmid", udinspojxxm.udinspojxxmid + "");
                    intent.putExtra("mark", ADD_REPORT);
                    startActivityForResult(intent, 0);
                } else {
                    NormalDialogStyleTwo(createreport);
                }
            } else {
                MessageUtils.showMiddleToast(Udinspojxxm_Details_Activity.this, "已生成缺陷或故障提报单！");
            }


        }
    };


    private void NormalDialogStyleTwo(final Createreport createreport) {
        final NormalDialog dialog = new NormalDialog(Udinspojxxm_Details_Activity.this);
        dialog.content("有尚未提交的提报单信息,是否查看")//
                .style(NormalDialog.STYLE_TWO)//
                .titleTextSize(23)//
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
                        Intent intent = new Intent(Udinspojxxm_Details_Activity.this, Createreport_Activity.class);
                        intent.putExtra("createreport", createreport);
                        intent.putExtra("mark", DETAILS_REPORT);
                        startActivityForResult(intent, 0);
                        dialog.dismiss();
                    }
                });

    }


    private void statusEdit() {

        executionText.setFocusable(true);
        executionText.setFocusableInTouchMode(true);
        normalRadioButton.setFocusable(true);
        abnormalRadioButton.setFocusableInTouchMode(true);
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

                            String result = getBaseApplication().getWsService().UpdatePO(Udinspojxxm_Details_Activity.this, data, "");

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
            if (!udinspojxxm2.equals(udinspojxxm.udinspojxxm2) && !TextUtils.isEmpty(udinspojxxm2)) {
                udinspojxxm1.setUdinspojxxm2(udinspojxxm2);
                json.put("UDINSPOJXXM2", udinspojxxm2);
            }

        } else if (writemethod.equals("02")) {
            String udinspojxxm2 = udinspojxxm2Text.getText().toString();
            if (!udinspojxxm2.equals(udinspojxxm.udinspojxxm2) && !TextUtils.isEmpty(udinspojxxm2)) {
                udinspojxxm1.setUdinspojxxm2(udinspojxxm2);
                json.put("UDINSPOJXXM2", udinspojxxm2);
            }
            String udinspojxxm3 = udinspojxxm3Text.getText().toString();
            if (!udinspojxxm3.equals(udinspojxxm.udinspojxxm3) && !TextUtils.isEmpty(udinspojxxm3)) {
                udinspojxxm1.setUdinspojxxm3(udinspojxxm3);
                json.put("UDINSPOJXXM3", udinspojxxm3);
            }
        } else if (writemethod.equals("03")) {
            String udinspojxxm2 = udinspojxxm2Text.getText().toString();
            if (!udinspojxxm2.equals(udinspojxxm.udinspojxxm2) && !TextUtils.isEmpty(udinspojxxm2)) {
                udinspojxxm1.setUdinspojxxm2(udinspojxxm2);
                json.put("UDINSPOJXXM2", udinspojxxm2);
            }
            String udinspojxxm3 = udinspojxxm3Text.getText().toString();
            if (!udinspojxxm3.equals(udinspojxxm.udinspojxxm3) && !TextUtils.isEmpty(udinspojxxm3)) {
                udinspojxxm1.setUdinspojxxm3(udinspojxxm3);
                json.put("UDINSPOJXXM3", udinspojxxm3);
            }
            String udinspojxxm4 = udinspojxxm3Text.getText().toString();
            if (!udinspojxxm4.equals(udinspojxxm.udinspojxxm4) && !TextUtils.isEmpty(udinspojxxm4)) {
                udinspojxxm1.setUdinspojxxm4(udinspojxxm4);
                json.put("UDINSPOJXXM4", udinspojxxm4);
            }
        } else if (writemethod.equals("04")) {


            if (!udinspojxxmvalue.equals(udinspojxxm1.udinspojxxm1) && !TextUtils.isEmpty(udinspojxxmvalue)) {
                udinspojxxm1.setUdinspojxxm1(udinspojxxmvalue);
                json.put("UDINSPOJXXM1", udinspojxxm);
            }

        } else if (writemethod.equals("05")) {

            String execution = executionText.getText().toString();
            if (!execution.equals(udinspojxxm1.execution) && !TextUtils.isEmpty(execution)) {
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
            if (!udinspojxxm2.equals(udinspojxxm.udinspojxxm2) && !TextUtils.isEmpty(udinspojxxm2)) {
                udinspojxxm1.setUdinspojxxm2(udinspojxxm2);
            }

        } else if (writemethod.equals("02")) {
            String udinspojxxm2 = udinspojxxm2Text.getText().toString();
            if (!udinspojxxm2.equals(udinspojxxm.udinspojxxm2) && !TextUtils.isEmpty(udinspojxxm2)) {
                udinspojxxm1.setUdinspojxxm2(udinspojxxm2);
            }
            String udinspojxxm3 = udinspojxxm3Text.getText().toString();
            if (!udinspojxxm3.equals(udinspojxxm.udinspojxxm3) && !TextUtils.isEmpty(udinspojxxm3)) {
                udinspojxxm1.setUdinspojxxm3(udinspojxxm3);
            }
        } else if (writemethod.equals("03")) {
            String udinspojxxm2 = udinspojxxm2Text.getText().toString();
            if (!udinspojxxm2.equals(udinspojxxm.udinspojxxm2) && !TextUtils.isEmpty(udinspojxxm2)) {
                udinspojxxm1.setUdinspojxxm2(udinspojxxm2);
            }
            String udinspojxxm3 = udinspojxxm3Text.getText().toString();
            if (!udinspojxxm3.equals(udinspojxxm.udinspojxxm3) && !TextUtils.isEmpty(udinspojxxm3)) {
                udinspojxxm1.setUdinspojxxm3(udinspojxxm3);
            }
            String udinspojxxm4 = udinspojxxm3Text.getText().toString();
            if (!udinspojxxm4.equals(udinspojxxm.udinspojxxm4) && !TextUtils.isEmpty(udinspojxxm4)) {
                udinspojxxm1.setUdinspojxxm4(udinspojxxm4);
            }
        } else if (writemethod.equals("04")) {

            if (!udinspojxxmvalue.equals(udinspojxxm1.udinspojxxm1) && !TextUtils.isEmpty(udinspojxxmvalue)) {
                udinspojxxm1.setUdinspojxxm1(udinspojxxmvalue);
            }

        } else if (writemethod.equals("05")) {
            String execution = executionText.getText().toString();
            if (!execution.equals(udinspojxxm1.execution) && !TextUtils.isEmpty(execution)) {
                udinspojxxm1.setExecution(execution);
            }

        }

        udinspojxxm1.setUdinspojxxmid(udinspojxxm.udinspojxxmid);
        udinspojxxm1.setUdinspojxxmlinenum(udinspojxxm.udinspojxxmlinenum);
        udinspojxxm1.setUdinspoassetnum(udinspojxxm.udinspoassetnum);
        udinspojxxm1.setDescription(udinspojxxm.description);
        udinspojxxm1.setType(Constants.UPDATE);
        udinspojxxm1.setWritemethod(writemethod);
        udinspojxxm1.setReportnum(udinspojxxm.reportnum);
        udinspojxxm1.setUdinspojxxm7(udinspojxxm.udinspojxxm7);
        udinspojxxm1.setLocal(1);
        new UdinspojxxmDao(Udinspojxxm_Details_Activity.this).insert(udinspojxxm1);
    }


    private void ActionSheetDialog() {
        final String[] stringItems = {"拍照"};
        final ActionSheetDialog dialog = new ActionSheetDialog(Udinspojxxm_Details_Activity.this, stringItems, null);

        dialog.titleTextColor(getResources().getColor(R.color.light_blue_color_1));
        dialog.show();
        dialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) { //拍照
                    photo();
                }
//                else if (position == 1) { //相册
//                    PhotoAlbum();
//                }
                dialog.dismiss();
            }
        });

    }

    private static final int TAKE_PICTURE = 0x000000;

    private String path = "";

    public void photo() {
        String fileName = DataUtils.getFileImagePath(Udinspojxxm_Details_Activity.this, udinspojxxm.udinspojxxmid + "");
        Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File file = new File(fileName, String.valueOf(System.currentTimeMillis())
                + ".JPEG");
        path = file.getPath();
        Uri imageUri = Uri.fromFile(file);
        openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(openCameraIntent, TAKE_PICTURE);
    }

    /**
     * 相册选择*
     */
    private void PhotoAlbum() {
        Intent intent = new Intent(Udinspojxxm_Details_Activity.this, ShowImageActivity.class);
        startActivityForResult(intent, 0);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode) {
            case TAKE_PICTURE:
                if (Udinspojxxm_Details_Activity.drr.size() < 9 && resultCode == -1) {
                    Udinspojxxm_Details_Activity.drr.add(path);
                }
//                adapter.update();
                break;
        }
    }


    @Override
    protected void onRestart() {

        adapter.update();
        super.onRestart();
    }

    /**
     * 判断该项目巡检是否存在故障或缺陷提报单*
     */
    private Createreport isData() {
        Createreport createreport = new CreatereportDao(Udinspojxxm_Details_Activity.this).findByUdinspojxxmid(udinspojxxm.udinspojxxmid + "");

        return createreport;
    }
}
