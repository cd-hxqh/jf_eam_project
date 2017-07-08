package com.jf_eam_project.ui.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jf_eam_project.Dao.AssignmentDao;
import com.jf_eam_project.Dao.LabtransDao;
import com.jf_eam_project.Dao.WoactivityDao;
import com.jf_eam_project.Dao.WorkOrderDao;
import com.jf_eam_project.Dao.WplaborDao;
import com.jf_eam_project.Dao.WpmeterialDao;
import com.jf_eam_project.R;
import com.jf_eam_project.utils.MessageUtils;
import com.pgyersdk.javabean.AppBean;
import com.pgyersdk.update.PgyUpdateManager;
import com.pgyersdk.update.UpdateManagerListener;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 设置界面
 */

public class SheZhi_Activity extends BaseActivity {

    private static final String TAG = "SheZhi_Activity";
    @Bind(R.id.title_name)
    TextView titleText;//标题
    @Bind(R.id.title_back_id)
    ImageView backImageView;//返回按钮

    @Bind(R.id.down_text_id)
    TextView downlayout;//数据下载
    @Bind(R.id.setting_data_clear)
    RelativeLayout clearlayout;//清楚缓存
    @Bind(R.id.about)
    TextView about;//关于我们
    @Bind(R.id.update)
    TextView update;//检查更新

    @Bind(R.id.version_text_id)
    TextView versionName;//版本名称
    private ProgressDialog mProgressDialog;
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        findViewById();

        initView();

    }

    @Override
    protected void findViewById() {
    }

    @Override
    protected void initView() {
        titleText.setText(getString(R.string.sz_text));
        downlayout.setOnClickListener(onClickListener);
        clearlayout.setOnClickListener(onClickListener);
        about.setOnClickListener(onClickListener);
        update.setOnClickListener(onClickListener);
//        getVerseronNameInfo();
    }

    @OnClick(R.id.title_back_id)
    void setBackImageViewOnClickListener() {
        finish();
    }


    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.down_text_id: //数据下载
                    intent = new Intent(SheZhi_Activity.this, DownloadActivity.class);
                    startActivity(intent);
                    break;
                case R.id.setting_data_clear: //清除缓存
                    clearData();
                    break;
                case R.id.about: //关于
                    intent = new Intent(SheZhi_Activity.this, About_us_Activity.class);
                    startActivityForResult(intent, 0);
                    break;
                case R.id.update://检查更新
//                    mProgressDialog = ProgressDialog.show(SheZhi_Activity.this, null,
//                            "正在检测更新，请耐心等候...", true, true);
//                    mProgressDialog.setCanceledOnTouchOutside(false);
//                    mProgressDialog.setCancelable(false);
                    getVerseronInfo();

                    break;
            }
        }
    };

    //清除基础数据
    private void clearData() {
        mProgressDialog = ProgressDialog.show(SheZhi_Activity.this, null,
                getString(R.string.clearing), true, true);
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.setCancelable(false);
        new WorkOrderDao(SheZhi_Activity.this).deleteall();
        new WoactivityDao(SheZhi_Activity.this).deleteall();
        new WplaborDao(SheZhi_Activity.this).deleteall();
        new WpmeterialDao(SheZhi_Activity.this).deleteall();
        new AssignmentDao(SheZhi_Activity.this).deleteall();
        new LabtransDao(SheZhi_Activity.this).deleteall();
        mProgressDialog.dismiss();
    }


    //版本更新检查
    private void getVerseronInfo() {

        // 版本检测方式2：带更新回调监听
        PgyUpdateManager.register(SheZhi_Activity.this, getString(R.string.file_provider),
                new UpdateManagerListener() {
                    @Override
                    public void onUpdateAvailable(final String result) {
                        Log.i(TAG, "result=" + result);
                        // 将新版本信息封装到AppBean中
                        final AppBean appBean = getAppBeanFromString(result);
                        String newcode = appBean.getVersionCode();//新版本
                        int dqcode = 0;
                        try {
                            dqcode = getPackageManager().getPackageInfo(getPackageName(), 0).versionCode;
                        } catch (PackageManager.NameNotFoundException e) {
                            e.printStackTrace();
                        }

                        if (Integer.valueOf(newcode) > dqcode) {
                            new AlertDialog.Builder(SheZhi_Activity.this)
                                    .setTitle("新版本为" + appBean.getVersionName())
                                    .setMessage(appBean.getReleaseNote())
                                    .setNegativeButton(
                                            "确定",
                                            new DialogInterface.OnClickListener() {

                                                @Override
                                                public void onClick(
                                                        DialogInterface dialog,
                                                        int which) {
                                                    startDownloadTask(
                                                            SheZhi_Activity.this,
                                                            appBean.getDownloadURL());
                                                }
                                            }).show();
                        } else {
                            MessageUtils.showMiddleToast(SheZhi_Activity.this, "当前版本为最新版本");
                        }
                    }

                    @Override
                    public void onNoUpdateAvailable() {
                    }
                });

    }

//    //版本更新检查
//    private void getVerseronNameInfo() {
//        // 版本检测方式2：带更新回调监听
//        PgyUpdateManager.register(SheZhi_Activity.this, getString(R.string.file_provider),
//                new UpdateManagerListener() {
//                    @Override
//                    public void onUpdateAvailable(final String result) {
//                        Log.i(TAG, "result=" + result);
//                        // 将新版本信息封装到AppBean中
//                        final AppBean appBean = getAppBeanFromString(result);
//                        versionName.setText(appBean.getVersionName());
//                    }
//
//                    @Override
//                    public void onNoUpdateAvailable() {
//                    }
//                });
//    }
}
