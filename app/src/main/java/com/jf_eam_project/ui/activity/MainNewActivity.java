package com.jf_eam_project.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flyco.animation.BaseAnimatorSet;
import com.flyco.animation.BounceEnter.BounceTopEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.NormalDialog;
import com.jf_eam_project.R;
import com.jf_eam_project.manager.AppManager;

/**
 * 新的主菜单信息
 */

public class MainNewActivity extends BaseActivity {

    private static final String TAG = "MainActivity";
    /**
     * 标题*
     */
    private TextView titleText;
    /**
     * 返回按钮
     **/
    private ImageView backImageView;
    private BaseAnimatorSet mBasIn;
    private BaseAnimatorSet mBasOut;

    /**
     * 流程审批
     **/
    private LinearLayout lcspLayout;
    /**
     * 工单管理
     **/
    private LinearLayout gdglLayout;
    /**
     * 巡检管理
     **/
    private LinearLayout xjglLayout;
    /**
     * 库存管理
     **/
    private LinearLayout kcglLayout;
    /**
     * 采购管理
     **/
    private LinearLayout cgglLayout;
    /**
     * 故障缺陷
     **/
    private LinearLayout gzqxLayout;
    /**
     * 本地历史
     **/
    private LinearLayout bdlsLayout;
    /**
     * 二维码/条码
     **/
    private LinearLayout tmsmLayout;
    /**
     * KPI统计
     **/
    private LinearLayout kpiLayout;
    /**
     * 设置
     **/
    private LinearLayout szLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_main);
        findViewById();

        initView();

    }

    @Override
    protected void findViewById() {
        titleText = (TextView) findViewById(R.id.title_name);
        backImageView = (ImageView) findViewById(R.id.title_back_id);

        lcspLayout = (LinearLayout) findViewById(R.id.lcsp_layout_id);
        gdglLayout = (LinearLayout) findViewById(R.id.gdgl_layout_id);
        xjglLayout = (LinearLayout) findViewById(R.id.xjgl_layout_id);
        kcglLayout = (LinearLayout) findViewById(R.id.kcgl_layout_id);
        cgglLayout = (LinearLayout) findViewById(R.id.cggl_layout_id);
        gzqxLayout = (LinearLayout) findViewById(R.id.gzqx_layout_id);
        bdlsLayout = (LinearLayout) findViewById(R.id.bdls_layout_id);
        tmsmLayout = (LinearLayout) findViewById(R.id.tmsm_layout_id);
        kpiLayout = (LinearLayout) findViewById(R.id.kpitj_layout_id);
        szLayout = (LinearLayout) findViewById(R.id.sz_layout_id);
    }

    @Override
    protected void initView() {
        titleText.setText(getString(R.string.title_activity_main));
        backImageView.setVisibility(View.GONE);
        mBasIn = new BounceTopEnter();
        mBasOut = new SlideBottomExit();

        lcspLayout.setOnClickListener(lcspLayoutOnClickListener);
        gdglLayout.setOnClickListener(gdglLayoutOnClickListener);
        xjglLayout.setOnClickListener(xjglLayoutOnClickListener);
        kcglLayout.setOnClickListener(kcglLayoutOnClickListener);
        cgglLayout.setOnClickListener(cgglLayoutOnClickListener);
        gzqxLayout.setOnClickListener(gzqxLayoutOnClickListener);
        bdlsLayout.setOnClickListener(bdlsLayoutOnClickListener);
        tmsmLayout.setOnClickListener(tmsmLayoutOnClickListener);
        kpiLayout.setOnClickListener(kpiLayoutOnClickListener);
        szLayout.setOnClickListener(szLayoutOnClickListener);
    }

    //流程审批
    private View.OnClickListener lcspLayoutOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainNewActivity.this, Wfment_Activity.class);
            startActivityForResult(intent, 0);
        }
    };
    //工单管理
    private View.OnClickListener gdglLayoutOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainNewActivity.this, WorkOrderActivity.class);
            startActivityForResult(intent, 0);
        }
    };
    //巡检管理
    private View.OnClickListener xjglLayoutOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainNewActivity.this, XunJan_Activity.class);
            startActivityForResult(intent, 0);
        }
    };
    //库存管理
    private View.OnClickListener kcglLayoutOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainNewActivity.this, KuCun_Activity.class);
            startActivityForResult(intent, 0);
        }
    };
    //采购管理
    private View.OnClickListener cgglLayoutOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainNewActivity.this, CaiGou_Activity.class);
            startActivityForResult(intent, 0);
        }
    };
    //故障缺陷管理
    private View.OnClickListener gzqxLayoutOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainNewActivity.this, GuZhang_Activity.class);
            startActivityForResult(intent, 0);
        }
    };
    //本地历史
    private View.OnClickListener bdlsLayoutOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainNewActivity.this, Lishi_Activity.class);
            startActivityForResult(intent, 0);
        }
    };
    //二维码扫描
    private View.OnClickListener tmsmLayoutOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent();
            intent.setClass(MainNewActivity.this, MipcaActivityCapture.class);
            startActivityForResult(intent, 0);
        }
    };
    //Kpi统计
    private View.OnClickListener kpiLayoutOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent();
            intent.setClass(MainNewActivity.this, ElectricityActivity.class);
            startActivityForResult(intent, 0);
        }
    };
    //Kpi统计
    private View.OnClickListener szLayoutOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent();
            intent.setClass(MainNewActivity.this, SheZhi_Activity.class);
            startActivityForResult(intent, 0);
        }
    };


    /**
     * 退出程序
     */
    public void showAlertDialog() {
        final NormalDialog dialog = new NormalDialog(MainNewActivity.this);
        dialog.content("确定退出程序吗")//
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
                        AppManager.AppExit(MainNewActivity.this);
                    }
                });

    }


    private long exitTime = 0;

    @Override
    public void onBackPressed() {
        showAlertDialog();
    }


}
