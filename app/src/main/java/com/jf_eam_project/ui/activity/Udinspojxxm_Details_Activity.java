package com.jf_eam_project.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
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
import com.jf_eam_project.Dao.UdreportDao;
import com.jf_eam_project.R;
import com.jf_eam_project.api.HttpManager;
import com.jf_eam_project.api.HttpRequestHandler;
import com.jf_eam_project.api.ig.json.Ig_Json_Model;
import com.jf_eam_project.bean.Results;
import com.jf_eam_project.config.Constants;
import com.jf_eam_project.model.Createreport;
import com.jf_eam_project.model.Udinspojxxm;
import com.jf_eam_project.model.Udreport;
import com.jf_eam_project.ui.adapter.GridAdapter;
import com.jf_eam_project.utils.DataUtils;
import com.jf_eam_project.utils.MessageUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
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
    private RadioButton abnormalRadioButton; //异常

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


    /**
     * 提报单*
     */
    private Button reportBtn;


    private ProgressDialog mProgressDialog;

    /**
     * 填写方式*
     */
    private String writemethod = "";

    private String udinspojxxmvalue = "";


    /**
     * 照片*
     */
    public static int max = 0;
    public static List<Bitmap> bmp = new ArrayList<Bitmap>();
    public static HashMap<String, Boolean> mHashMap = new HashMap<String, Boolean>();

    //图片sd地址  上传服务器时把图片调用下面方法压缩后 保存到临时文件夹 图片压缩后小于100KB，失真度不明显
    private List<String> drr = new ArrayList<String>();


    /**
     * 分公司*
     */
    private String branch;
    /**
     * 运行单位*
     */
    private String udbelong;
    /**
     * 类型*
     */
    private String assettype;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
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
        branch = getIntent().getExtras().getString("branch");
        udbelong = getIntent().getExtras().getString("udbelong");
        assettype = getIntent().getExtras().getString("assettype");

        Log.i(TAG,"branch="+branch+",udbelong="+udbelong+",assettype="+assettype);
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
//        editImageView.setVisibility(View.VISIBLE);
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
            String udinspojxxm1 = udinspojxxm.getUdinspojxxm1();
            if (udinspojxxm1.equals("") || udinspojxxm1.equals("正常")) {
                normalRadioButton.setChecked(true);
                abnormalRadioButton.setChecked(false);
                executionText.setText("正常");
            } else {
                normalRadioButton.setChecked(false);
                abnormalRadioButton.setChecked(true);
                executionText.setText(udinspojxxm1);
            }

            udinspojxxm2Text.setText(udinspojxxm.getUdinspojxxm2() == null ? "" : udinspojxxm.getUdinspojxxm2());
            udinspojxxm3Text.setText(udinspojxxm.getUdinspojxxm3() == null ? "" : udinspojxxm.getUdinspojxxm3());
            udinspojxxm4Text.setText(udinspojxxm.getUdinspojxxm4() == null ? "" : udinspojxxm.getUdinspojxxm4());
        }


        mRadioGroup1.setOnCheckedChangeListener(radiogpchange);
        submitBtn.setOnClickListener(submitBtnOnClickListener);


        noScrollgridview.setSelector(new ColorDrawable(Color.TRANSPARENT));

        adapter = new GridAdapter(this, readFile());
//        adapter.update();
        noScrollgridview.setAdapter(adapter);
        noScrollgridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                if (arg2 == drr.size()) {
                    ActionSheetDialog();
                } else {
                    Intent intent = new Intent(Udinspojxxm_Details_Activity.this,
                            PhotoActivity.class);
                    intent.putExtra("ID", arg2);
                    intent.putStringArrayListExtra("drr", (ArrayList<String>) drr);
                    startActivityForResult(intent, 1000);
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
                executionText.setText(udinspojxxmvalue);

            } else if (checkedId == abnormalRadioButton.getId()) {
                udinspojxxmvalue = "异常";
                executionText.setText(udinspojxxmvalue);
            }
        }
    };


    private View.OnClickListener editImageViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(udinspojxxmvalue.equals("异常")&&udinspojxxm.reportnum==null){
                    Intent intent = new Intent(Udinspojxxm_Details_Activity.this, Createreport_Activity.class);
                    intent.putExtra("udinspojxxmid", udinspojxxm.udinspojxxmid + "");
                    intent.putExtra("mark", ADD_REPORT);
                    intent.putExtra("branch", branch);
                    intent.putExtra("udbelong", udbelong);
                    intent.putExtra("assettype", assettype);
                    startActivityForResult(intent, 0);
            } else {
                NormalDialogReport(udinspojxxm.reportnum);
            }


        }
    };


    private void NormalDialogReport(final String reportnum) {
        final NormalDialog dialog = new NormalDialog(Udinspojxxm_Details_Activity.this);
        dialog.content("已生成故障或缺陷提报单,是否查看!")//
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
                        //根据reportnum查询Udreport

//                        getUdreport(reportnum, dialog);
                        getUdreportList(reportnum);
                        dialog.dismiss();

                    }
                });

    }


    private void getUdreportList(String reportnum) {
        List<Udreport> list = new UdreportDao(Udinspojxxm_Details_Activity.this).queryByNum(reportnum);
        Intent intent = new Intent(Udinspojxxm_Details_Activity.this, Udreport_Details_Activity.class);
        intent.putExtra("udreport", list.get(0));
        intent.putExtra("mark", 1);
        startActivityForResult(intent, 0);
    }


    /**
     * 根据编号获取提报单信息*
     */
    private void getUdreport(String reportnum, final NormalDialog dialog) {
        HttpManager.getDataPagingInfo(this, HttpManager.getUdreport(reportnum), new HttpRequestHandler<Results>() {
            @Override
            public void onSuccess(Results results) {
            }

            @Override
            public void onSuccess(Results results, int totalPages, int currentPage) {
                dialog.dismiss();
                ArrayList<Udreport> items = null;
                try {
                    items = Ig_Json_Model.parsingUdreport(results.getResultlist());
                    if (items == null || items.isEmpty()) {
                    } else {

                        Intent intent = new Intent(Udinspojxxm_Details_Activity.this, Udreport_Details_Activity.class);
                        intent.putExtra("udreport", items.get(0));
                        intent.putExtra("mark", 1);
                        startActivityForResult(intent, 0);


                    }

                } catch (IOException e) {
                    dialog.dismiss();
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(String error) {
                dialog.dismiss();
            }
        });
    }


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

//        AlertDialog.Builder builder = new AlertDialog.Builder(Udinspojxxm_Details_Activity.this);
//        builder.setMessage("确定更新检修项目标准吗？").setTitle("提示")
//                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        dialogInterface.dismiss();
//                    }
//                }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                dialogInterface.dismiss();
//
//                saveUdinspo();
//                setResult(Constants.REFRESH);
//                finish();
//            }
//        }).create().show();

        if (saveUdinspo()) {
            setResult(Constants.REFRESH);

            MessageUtils.showMiddleToast(Udinspojxxm_Details_Activity.this, "数据保存成功");
            finish();
        }


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
            String udinspojxxm4 = udinspojxxm4Text.getText().toString();
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
    private boolean saveUdinspo() {
        if (writemethod.equals("01")) {
            String udinspojxxm2 = udinspojxxm2Text.getText().toString();
            if (udinspojxxm2.length() == 0) {
                udinspojxxm2Text.setError("数值A不能为空");
                return false;
            }
            udinspojxxm.setUdinspojxxm2(udinspojxxm2);


        } else if (writemethod.equals("02")) {
            String udinspojxxm2 = udinspojxxm2Text.getText().toString();

            if (udinspojxxm2.length() == 0) {
                udinspojxxm2Text.setError("数值A不能为空");
                return false;
            }

            udinspojxxm.setUdinspojxxm2(udinspojxxm2);
            String udinspojxxm3 = udinspojxxm3Text.getText().toString();

            if (udinspojxxm3.length() == 0) {
                udinspojxxm3Text.setError("数值B不能为空");
                return false;
            }
            udinspojxxm.setUdinspojxxm3(udinspojxxm3);
        } else if (writemethod.equals("03")) {
            String udinspojxxm2 = udinspojxxm2Text.getText().toString();
            if (udinspojxxm2.length() == 0) {
                udinspojxxm2Text.setError("数值A不能为空");
                return false;
            }

            if (!udinspojxxm2.equals(udinspojxxm.udinspojxxm2) && !udinspojxxm2.isEmpty()) {
                udinspojxxm.setUdinspojxxm2(udinspojxxm2);
            }
            String udinspojxxm3 = udinspojxxm3Text.getText().toString();
            if (udinspojxxm3.length() == 0) {
                udinspojxxm3Text.setError("数值B不能为空");
                return false;
            }

            udinspojxxm.setUdinspojxxm3(udinspojxxm3);
            String udinspojxxm4 = udinspojxxm4Text.getText().toString();
            if (udinspojxxm4.length() == 0) {
                udinspojxxm4Text.setError("数值C不能为空");
                return false;
            }


            udinspojxxm.setUdinspojxxm4(udinspojxxm4);
        }
//        else if (writemethod.equals("04")) {

//            if (!udinspojxxmvalue.equals(udinspojxxm1.udinspojxxm1) && !TextUtils.isEmpty(udinspojxxmvalue)) {
//                udinspojxxm1.setUdinspojxxm1(udinspojxxmvalue);
//            }

//        }
        else if (writemethod.equals("05")) {
            String execution = executionText.getText().toString();
            if (!execution.equals(udinspojxxm.execution) && !TextUtils.isEmpty(execution)) {
                udinspojxxm.setExecution(execution);
            }

        }
        udinspojxxm.setId(udinspojxxm.id);
        udinspojxxm.setUdinspojxxmid(udinspojxxm.udinspojxxmid);
        udinspojxxm.setUdinspojxxmlinenum(udinspojxxm.udinspojxxmlinenum);
        udinspojxxm.setUdinspoassetnum(udinspojxxm.udinspoassetnum);
        udinspojxxm.setDescription(udinspojxxm.description);
        udinspojxxm.setType(Constants.UPDATE);
        udinspojxxm.setWritemethod(writemethod);
        udinspojxxm.setReportnum(udinspojxxm.reportnum);
        udinspojxxm.setUdinspojxxm7(udinspojxxm.udinspojxxm7);
        udinspojxxm.setLocal(1); //已操作
        udinspojxxm.setCompletion(1);

        udinspojxxm.setUdinspojxxm1(udinspojxxmvalue.equals("") ? "正常" : udinspojxxmvalue);
        new UdinspojxxmDao(Udinspojxxm_Details_Activity.this).update(udinspojxxm);
        return true;
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


    /**
     * 拍照*
     */
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
                if (drr.size() < 9 && resultCode == -1) {
                    drr.add(path);
                }
            case 1000:
                adapter = new GridAdapter(this, readFile());
                noScrollgridview.setAdapter(adapter);
//                adapter.update();
                break;
        }
    }


    @Override
    protected void onRestart() {

//        adapter.update();
        adapter = new GridAdapter(this, readFile());
        noScrollgridview.setAdapter(adapter);
        super.onRestart();
    }

    /**
     * 判断该项目巡检是否存在故障或缺陷提报单*
     */
    private Createreport isData() {
        Createreport createreport = new CreatereportDao(Udinspojxxm_Details_Activity.this).findByUdinspojxxmid(udinspojxxm.udinspojxxmid + "");

        return createreport;
    }

    /**
     * 读取文件*
     */
    private List<String> readFile() {
        String fileName = DataUtils.getFileImagePath(Udinspojxxm_Details_Activity.this, udinspojxxm.udinspojxxmid + "");
        File file = new File(fileName);
        drr = new ArrayList<String>();
        if (file.exists()) {

            if (file.isDirectory()) {
                for (String name : file.list()) {

                    File file1 = new File(fileName, name);
                    if (file1.exists()) {
                        drr.add(file1.getPath());
                    }

                }
            }


        }
        return drr;
    }
}
