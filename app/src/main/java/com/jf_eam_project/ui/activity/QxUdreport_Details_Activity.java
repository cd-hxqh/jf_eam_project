package com.jf_eam_project.ui.activity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.flyco.animation.BaseAnimatorSet;
import com.flyco.animation.BounceEnter.BounceTopEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.listener.OnBtnEditClickL;
import com.flyco.dialog.widget.MaterialDialog;
import com.flyco.dialog.widget.NormalEditTextDialog;
import com.jf_eam_project.Dao.UdinspoDao;
import com.jf_eam_project.R;
import com.jf_eam_project.api.HttpManager;
import com.jf_eam_project.api.HttpRequestHandler;
import com.jf_eam_project.api.JsonUtils;
import com.jf_eam_project.api.ig.json.Ig_Json_Model;
import com.jf_eam_project.bean.Results;
import com.jf_eam_project.model.Udinspo;
import com.jf_eam_project.model.Udreport;
import com.jf_eam_project.utils.MessageUtils;

import java.io.IOException;
import java.util.ArrayList;

/**
 * 提报单详情
 */
public class QxUdreport_Details_Activity extends BaseActivity {

    private static final String TAG = "QxUdreport_Details_Activity";

    /**
     * 标题*
     */
    private TextView titleView;
    /**
     * 返回按钮*
     */
    private ImageView backImageView;
    /**
     * 缺陷界面信息显示
     */
    private TextView reportnumText; //编号
    private TextView descriptionText; //描述
    private TextView assettypeText; //设备专业
    private TextView qxtypeText; //缺陷类型
    private TextView qxsjlyText; //缺陷数据来源
    private TextView culevelText; //缺陷等级
    private TextView udworktypeText; //工单类型
    private TextView branch_descriptionText; //分公司
    private TextView udbelong_descriptionText; //运行单位
    private TextView statustypeText; //缺陷状态
    private TextView createby_displaynameText; //提报人
    private TextView createdateText; //提报日期
    private TextView xcdateText; //消除时间
    private TextView assetnum_descriptionText; //设备
    private TextView location_descriptionText; //位置
    private TextView failurecodeText; //故障类
    private TextView cudescribeText; //故障、隐患描述


    /**
     * 工作流*
     */
    private Button approvalBtn;
    /**
     * 缺陷提报单id*
     */
    private String udreportid;


    private ProgressDialog mProgressDialog;


    private BaseAnimatorSet mBasIn;
    private BaseAnimatorSet mBasOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_qxtbd_details);
        initData();
        findViewById();
        initView();
    }

    /**
     * 获取初始话数据*
     */
    private void initData() {
        udreportid = getIntent().getExtras().getString("udreportid");
        Log.i(TAG, "udreportid=" + udreportid);

    }

    @Override
    protected void findViewById() {
        titleView = (TextView) findViewById(R.id.title_name);
        backImageView = (ImageView) findViewById(R.id.title_back_id);
        approvalBtn = (Button) findViewById(R.id.approval_btn);


        reportnumText = (TextView) findViewById(R.id.reportnum_text_id);
        descriptionText = (TextView) findViewById(R.id.description_text_id);

        qxtypeText = (TextView) findViewById(R.id.qxtype_text_id);
        qxsjlyText = (TextView) findViewById(R.id.qxsjly_text_id);

        udworktypeText = (TextView) findViewById(R.id.udworktype_text_id);
        assettypeText = (TextView) findViewById(R.id.assettype_text_id);
        culevelText = (TextView) findViewById(R.id.culevel_text_id);
        branch_descriptionText = (TextView) findViewById(R.id.branch_description_text_id);
        udbelong_descriptionText = (TextView) findViewById(R.id.udbelong_description_text);
        statustypeText = (TextView) findViewById(R.id.status_text_id);
        createby_displaynameText = (TextView) findViewById(R.id.createby_displayname_text_id);
        createdateText = (TextView) findViewById(R.id.createdate_text_id);
        xcdateText = (TextView) findViewById(R.id.xcdate_text_id);
        assetnum_descriptionText = (TextView) findViewById(R.id.assetnum_description_text_id);
        location_descriptionText = (TextView) findViewById(R.id.location_description_text_id);
        cudescribeText = (TextView) findViewById(R.id.cudescribe_text_id);

    }

    @Override
    protected void initView() {
        titleView.setText(getString(R.string.qxtbd_title));
        backImageView.setOnClickListener(backImageViewOnClickListenrer);


        getQxUderport(udreportid);

        mBasIn = new BounceTopEnter();
        mBasOut = new SlideBottomExit();

        approvalBtn.setOnClickListener(approvalBtnOnClickListener);
    }


    /**
     * 设置显示的值*
     */
    private void setShowInfo(Udreport udreport) {
        if (udreport != null) {
            reportnumText.setText(udreport.reportnum == null ? "" : udreport.reportnum);
            descriptionText.setText(udreport.description == null ? "" : udreport.description);
            qxtypeText.setText(udreport.qxtype == null ? "" : udreport.qxtype);
            qxsjlyText.setText(udreport.qxsjly == null ? "" : udreport.qxsjly);

            udworktypeText.setText(udreport.udworktype == null ? "" : udreport.udworktype);
            assettypeText.setText(udreport.assettype == null ? "" : udreport.assettype);
            culevelText.setText(udreport.culevel == null ? "" : udreport.culevel);
            branch_descriptionText.setText(udreport.branch_description == null ? "" : udreport.branch_description);
            udbelong_descriptionText.setText(udreport.udbelong_description == null ? "" : udreport.udbelong_description);
            statustypeText.setText(udreport.statustype == null ? "" : udreport.statustype);
            createby_displaynameText.setText(udreport.createby_displayname == null ? "" : udreport.createby_displayname);
            createdateText.setText(udreport.createdate == null ? "" : udreport.createdate);
            xcdateText.setText(udreport.xcdate == null ? "" : udreport.xcdate);
            assetnum_descriptionText.setText(udreport.assetnum_description == null ? "" : udreport.assetnum_description);
            location_descriptionText.setText(udreport.location_description == null ? "" : udreport.location_description);

            cudescribeText.setText(udreport.cudescribe == null ? "" : udreport.cudescribe);
        }
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
     * 审批工作流*
     */
    private View.OnClickListener approvalBtnOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            MaterialDialogOneBtn();
        }
    };


    /**
     * 获取缺陷提报单信息*
     */
    private void getQxUderport(String udreportid) {
        HttpManager.getDataPagingInfo(this, HttpManager.getUdreportId(udreportid), new HttpRequestHandler<Results>() {
            @Override
            public void onSuccess(Results results) {

            }

            @Override
            public void onSuccess(Results results, int totalPages, int currentPage) {


                try {
                    ArrayList<Udreport> list1 = Ig_Json_Model.parsingUdreport(results.getResultlist());
                    if (list1 == null || list1.isEmpty()) {
                    } else {
                        //展示信息
                        setShowInfo(list1.get(0));
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(String error) {
            }
        });
    }


    private void MaterialDialogOneBtn() {//审批工作流
        final MaterialDialog dialog = new MaterialDialog(QxUdreport_Details_Activity.this);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.isTitleShow(false)//
                .btnNum(2)
                .content("是否填写输入意见")//
                .btnText("是", "否，直接提交")//
                .showAnim(mBasIn)//
                .dismissAnim(mBasOut)
                .show();

        dialog.setOnBtnClickL(
                new OnBtnClickL() {//是
                    @Override
                    public void onBtnClick() {
                        EditDialog(true);
                        dialog.dismiss();
                    }
                },
                new OnBtnClickL() {//否
                    @Override
                    public void onBtnClick() {
                        wfgoon("1", "");
                        dialog.dismiss();
                    }
                }
        );
    }

    private void EditDialog(final boolean isok) {//输入审核意见
        final NormalEditTextDialog dialog = new NormalEditTextDialog(QxUdreport_Details_Activity.this);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.isTitleShow(false)//
                .btnNum(2)
                .content(isok ? "通过" : "不通过")//
                .btnText("提交", "取消")//
                .showAnim(mBasIn)//
                .dismissAnim(mBasOut)
                .show();

        dialog.setOnBtnClickL(
                new OnBtnEditClickL() {
                    @Override
                    public void onBtnClick(String text) {
                        wfgoon("1", text);

                        dialog.dismiss();
                    }
                },
                new OnBtnEditClickL() {
                    @Override
                    public void onBtnClick(String text) {

                        dialog.dismiss();
                    }
                }
        );
    }


    /**
     * 审批工作流
     *
     * @param zx
     */
    private void wfgoon(final String zx, final String desc) {
        Log.i(TAG, "zx=" + zx + ",desc=" + desc);
        mProgressDialog = ProgressDialog.show(QxUdreport_Details_Activity.this, null,
                getString(R.string.inputing), true, true);
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.setCancelable(false);
        new AsyncTask<String, String, String>() {
            @Override
            protected String doInBackground(String... strings) {
                String result = null;
                result = getBaseApplication().getWfService().wfGoOn(QxUdreport_Details_Activity.this, "UDQXTB", "UDREPORT", udreportid, "UDREPORTID", zx, desc);

                Log.i(TAG, "result=" + result);
                return result;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if (s == null || s.equals("")) {
                    Toast.makeText(QxUdreport_Details_Activity.this, "审批失败", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(QxUdreport_Details_Activity.this, "审批成功", Toast.LENGTH_SHORT).show();
                }
                mProgressDialog.dismiss();
            }
        }.execute();
    }

}
