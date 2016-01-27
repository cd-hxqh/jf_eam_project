package com.jf_eam_project.ui.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.flyco.animation.BaseAnimatorSet;
import com.flyco.animation.BounceEnter.BounceTopEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.listener.OnBtnEditClickL;
import com.flyco.dialog.widget.MaterialDialog;
import com.flyco.dialog.widget.NormalEditTextDialog;
import com.jf_eam_project.R;
import com.jf_eam_project.api.HttpManager;
import com.jf_eam_project.api.HttpRequestHandler;
import com.jf_eam_project.api.JsonUtils;
import com.jf_eam_project.bean.Results;
import com.jf_eam_project.model.Po;
import com.jf_eam_project.model.Wfassignment;
import com.jf_eam_project.utils.MessageUtils;

/**
 * 流程审批详情
 */
public class Wfm_Details_Activity extends BaseActivity {

    private static final String TAG = "Wfm_Details_Activity";

    /**
     * 标题*
     */
    private TextView titleView;
    /**
     * 返回按钮*
     */
    private ImageView backImageView;

    private BaseAnimatorSet mBasIn;
    private BaseAnimatorSet mBasOut;

    /**
     * 界面信息显示*
     */
    private TextView ownertableText; //应用程序名
    private TextView descriptionText; //描述
    private TextView assigncodeText; //任务分配人
    private TextView duedateText; //到期日期
    private TextView processnameText; //过程名称
    private TextView roleidText; //任务角色
    private TextView startdateText; //当前日期

    private Button approve;

    private ProgressDialog mProgressDialog;
    private String result;
    private Wfassignment wfm; //流程审批


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wfm_details);
        initData();
        findViewById();
        initView();
    }

    /**
     * 获取初始话数据*
     */
    private void initData() {
        wfm = (Wfassignment) getIntent().getSerializableExtra("wfassignment");
    }

    @Override
    protected void findViewById() {
        titleView = (TextView) findViewById(R.id.title_name);
        backImageView = (ImageView) findViewById(R.id.title_back_id);

        ownertableText = (TextView) findViewById(R.id.wfm_ownertable_text);
        descriptionText = (TextView) findViewById(R.id.wfm_description_text);
        assigncodeText = (TextView) findViewById(R.id.wfm_assigncode_text);
        duedateText = (TextView) findViewById(R.id.wfm_duedate_text);
        processnameText = (TextView) findViewById(R.id.wfm_processname_text);
        roleidText = (TextView) findViewById(R.id.wfm_roleid_text);
        startdateText = (TextView) findViewById(R.id.wfm_startdate_text);
        approve = (Button) findViewById(R.id.wfm_approve);
    }

    @Override
    protected void initView() {
        titleView.setText(getString(R.string.wfm_detail_title));
        backImageView.setOnClickListener(backImageViewOnClickListenrer);



        if (wfm != null) {
            ownertableText.setText(wfm.getOwnertable() == null ? "" : wfm.getOwnertable());
            descriptionText.setText(wfm.getDescription() == null ? "" : wfm.getDescription());
            assigncodeText.setText(wfm.getAssigncode() == null ? "" : wfm.getAssigncode());
            duedateText.setText(wfm.getDuedate() == null ? "" : wfm.getDuedate());
            processnameText.setText(wfm.getProcessname() == null ? "" : wfm.getProcessname());
            roleidText.setText(wfm.getRoleid() == null ? "" : wfm.getRoleid());
            startdateText.setText(wfm.getStartdate() == null ? "" : wfm.getStartdate());
        }
        mBasIn = new BounceTopEnter();
        mBasOut = new SlideBottomExit();
        approve.setOnClickListener(approveOnClickListener);
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

    private View.OnClickListener approveOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            MaterialDialogOneBtn();
        }
    };

    private void MaterialDialogOneBtn() {//选择审核结果
        final MaterialDialog dialog = new MaterialDialog(Wfm_Details_Activity.this);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.isTitleShow(false)//
                .btnNum(3)
                .content("请选择审核结果")//
                .btnText("通过", "取消", "不通过")//
                .showAnim(mBasIn)//
                .dismissAnim(mBasOut)
                .show();

        dialog.setOnBtnClickL(
                new OnBtnClickL() {//通过
                    @Override
                    public void onBtnClick() {
                        MaterialDialogOneBtn1(true);
                        dialog.dismiss();
                    }
                },
                new OnBtnClickL() {//取消
                    @Override
                    public void onBtnClick() {
                        dialog.dismiss();
                    }
                }
                ,
                new OnBtnClickL() {//不通过
                    @Override
                    public void onBtnClick() {
                        MessageUtils.showMiddleToast(Wfm_Details_Activity.this, "不通过");
                        MaterialDialogOneBtn1(false);
                        dialog.dismiss();
                    }
                }
        );
    }

    private void MaterialDialogOneBtn1(final boolean isok) {//是否输入审核意见
        final MaterialDialog dialog = new MaterialDialog(Wfm_Details_Activity.this);
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
                        EditDialog(isok);
                        dialog.dismiss();
                    }
                },
                new OnBtnClickL() {//否
                    @Override
                    public void onBtnClick() {
                        getwfstatus(isok,"不通过");
                        dialog.dismiss();
                    }
                }
        );
    }

    private void EditDialog(final boolean isok) {//输入审核意见
        final NormalEditTextDialog dialog = new NormalEditTextDialog(Wfm_Details_Activity.this);
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
                        getwfstatus(isok,text);
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
     * 判断工作流是否已启动
     *
     * @return
     */
    private void getwfstatus(final boolean isok, final String desc) {
        HttpManager.getDataPagingInfo(this, HttpManager.getWfStatusUrl(1, 20, ""), new HttpRequestHandler<Results>() {
            @Override
            public void onSuccess(Results results) {
                Log.i(TAG, "data=" + results);
            }

            @Override
            public void onSuccess(Results results, int totalPages, int currentPage) {
                Log.i(TAG,"results.getResultlist()="+results.getResultlist());
                String result = JsonUtils.parsingwfstatusResult(results.getResultlist());
                Log.i(TAG,"result="+result);
                if (result != null && result.equals("Y")) {
//                    wfstart(workOrder.wonum);
                    MessageUtils.showMiddleToast(Wfm_Details_Activity.this,"流程审批成功");
                    finish();
                } else if (result != null && result.equals("N")) {
//                    wfgoon(workOrder.wonum, isok ? "1" : "0", desc);
                    MessageUtils.showMiddleToast(Wfm_Details_Activity.this,"流程审批成功");
                    finish();
                }

            }

            @Override
            public void onFailure(String error) {
                Toast.makeText(Wfm_Details_Activity.this, "查询工作流状态失败，审核中止", Toast.LENGTH_SHORT).show();
                mProgressDialog.dismiss();
            }
        });

    }

    /**
     * 开始工作流
     *
     * @param wonum
     */
    private void wfstart(final String wonum) {
        mProgressDialog = ProgressDialog.show(Wfm_Details_Activity.this, null,
                getString(R.string.inputing), true, true);
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.setCancelable(false);
        new AsyncTask<String, String, String>() {
            @Override
            protected String doInBackground(String... strings) {
                String result = getBaseApplication().getWfService().startwf("UDFJHWO", "WORKORDER", wonum, "WONUM");
                return result;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if (s == null || s.equals("")) {
                    Toast.makeText(Wfm_Details_Activity.this, "审批失败", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Wfm_Details_Activity.this, s, Toast.LENGTH_SHORT).show();

                }
                mProgressDialog.dismiss();
            }
        }.execute();
    }

    /**
     * 审批工作流
     *
     * @param wonum
     * @param zx
     */
    private void wfgoon(final String wonum, final String zx, final String desc) {
        mProgressDialog = ProgressDialog.show(Wfm_Details_Activity.this, null,
                getString(R.string.inputing), true, true);
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.setCancelable(false);
        new AsyncTask<String, String, String>() {
            @Override
            protected String doInBackground(String... strings) {
                String result = getBaseApplication().getWfService().wfGoOn("UDFJHWO", "WORKORDER", wonum, "WONUM", zx, desc);
                return result;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if (s == null || s.equals("")) {
                    Toast.makeText(Wfm_Details_Activity.this, "审批失败", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Wfm_Details_Activity.this, s, Toast.LENGTH_SHORT).show();
                    Wfm_Details_Activity.this.finish();
                }
                mProgressDialog.dismiss();
            }
        }.execute();
    }

}
