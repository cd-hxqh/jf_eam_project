package com.jf_eam_project.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.autoupdatesdk.AppUpdateInfo;
import com.baidu.autoupdatesdk.AppUpdateInfoForInstall;
import com.baidu.autoupdatesdk.BDAutoUpdateSDK;
import com.baidu.autoupdatesdk.CPCheckUpdateCallback;
import com.baidu.autoupdatesdk.UICheckUpdateCallback;
import com.jf_eam_project.Dao.AssignmentDao;
import com.jf_eam_project.Dao.LabtransDao;
import com.jf_eam_project.Dao.WoactivityDao;
import com.jf_eam_project.Dao.WorkOrderDao;
import com.jf_eam_project.Dao.WplaborDao;
import com.jf_eam_project.Dao.WpmeterialDao;
import com.jf_eam_project.R;
import com.jf_eam_project.utils.MessageUtils;

/**
 * 设置界面
 */

public class SheZhi_Activity extends BaseActivity {

    private static final String TAG = "SheZhi_Activity";
    /**
     * 标题*
     */
    private TextView titleText;
    /**
     * 返回按钮
     **/
    private ImageView backImageView;

    /**
     * 数据下载
     **/
    private TextView downlayout;
    /**
     * 清楚缓存
     **/
    private RelativeLayout clearlayout;
    /**
     * 关于我们
     **/
    private TextView about;
    /**
     * 检查更新
     **/
    private TextView update;
    private ProgressDialog mProgressDialog;
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        findViewById();

        initView();

    }

    @Override
    protected void findViewById() {
        titleText = (TextView) findViewById(R.id.title_name);
        backImageView = (ImageView) findViewById(R.id.title_back_id);

        downlayout = (TextView) findViewById(R.id.down_text_id);
        clearlayout = (RelativeLayout) findViewById(R.id.setting_data_clear);
        about = (TextView) findViewById(R.id.about);
        update = (TextView) findViewById(R.id.update);
    }

    @Override
    protected void initView() {
        titleText.setText(getString(R.string.sz_text));
        backImageView.setOnClickListener(backImageViewOnClickListener);
        downlayout.setOnClickListener(onClickListener);
        clearlayout.setOnClickListener(onClickListener);
        about.setOnClickListener(onClickListener);
        update.setOnClickListener(onClickListener);
    }


    private View.OnClickListener backImageViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            finish();
        }
    };


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
                    startActivity(intent);
                    break;
                case R.id.update://检查更新
                    mProgressDialog = ProgressDialog.show(SheZhi_Activity.this, null,
                            "正在检测更新，请耐心等候...", true, true);
                    mProgressDialog.setCanceledOnTouchOutside(false);
                    mProgressDialog.setCancelable(false);
                    updateVersion();
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

    /**
     * 手动更新*
     */
    private void updateVersion() {
        BDAutoUpdateSDK.cpUpdateCheck(SheZhi_Activity.this, new MyCPCheckUpdateCallback());

    }


    private class MyCPCheckUpdateCallback implements CPCheckUpdateCallback {

        @Override
        public void onCheckUpdateCallback(AppUpdateInfo info, AppUpdateInfoForInstall infoForInstall) {
            if (infoForInstall != null && !TextUtils.isEmpty(infoForInstall.getInstallPath())) {
                mProgressDialog.dismiss();
                BDAutoUpdateSDK.uiUpdateAction(SheZhi_Activity.this, new MyUICheckUpdateCallback());
            } else if (info != null) {
                mProgressDialog.dismiss();
                BDAutoUpdateSDK.uiUpdateAction(SheZhi_Activity.this, new MyUICheckUpdateCallback());

            } else {
                MessageUtils.showMiddleToast(SheZhi_Activity.this, "已是最新版本");
            }

            mProgressDialog.dismiss();
        }

    }

    private class MyUICheckUpdateCallback implements UICheckUpdateCallback {
        @Override
        public void onCheckComplete() {
        }

    }


}
