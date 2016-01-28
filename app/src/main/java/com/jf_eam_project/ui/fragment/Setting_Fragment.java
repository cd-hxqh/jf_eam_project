package com.jf_eam_project.ui.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.jf_eam_project.Dao.AssetDao;
import com.jf_eam_project.Dao.AssignmentDao;
import com.jf_eam_project.Dao.CraftrateDao;
import com.jf_eam_project.Dao.FailurecodeDao;
import com.jf_eam_project.Dao.FailurelistDao;
import com.jf_eam_project.Dao.ItemDao;
import com.jf_eam_project.Dao.JobplanDao;
import com.jf_eam_project.Dao.LaborDao;
import com.jf_eam_project.Dao.LaborcraftrateDao;
import com.jf_eam_project.Dao.LabtransDao;
import com.jf_eam_project.Dao.LocationDao;
import com.jf_eam_project.Dao.PersonDao;
import com.jf_eam_project.Dao.UdinspoDao;
import com.jf_eam_project.Dao.WoactivityDao;
import com.jf_eam_project.Dao.WorkOrderDao;
import com.jf_eam_project.Dao.WplaborDao;
import com.jf_eam_project.Dao.WpmeterialDao;
import com.jf_eam_project.R;
import com.jf_eam_project.model.Assignment;
import com.jf_eam_project.ui.activity.About_us_Activity;
import com.jf_eam_project.ui.activity.DownloadActivity;
import com.jf_eam_project.ui.activity.Invoice_Activity;
import com.jf_eam_project.ui.activity.Po_order_Activity;
import com.jf_eam_project.ui.activity.Pr_Activity;
import com.umeng.update.UmengUpdateAgent;
import com.umeng.update.UmengUpdateListener;
import com.umeng.update.UpdateResponse;
import com.umeng.update.UpdateStatus;


/**
 * 设置的fragment
 */
public class Setting_Fragment extends BaseFragment {

    private RelativeLayout downlayout;
    private RelativeLayout clearlayout;
    private RelativeLayout about;
    private RelativeLayout update;
    private ProgressDialog mProgressDialog;
    Intent intent;
    public Setting_Fragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container,
                false);

        findByIdView(view);
        setListener();
        return view;
    }

    /**
     * 初始化界面组件*
     */
    private void findByIdView(View view) {
        downlayout = (RelativeLayout) view.findViewById(R.id.setting_download);
        clearlayout = (RelativeLayout) view.findViewById(R.id.setting_data_clear);
        about = (RelativeLayout) view.findViewById(R.id.about);
        update = (RelativeLayout) view.findViewById(R.id.update);
    }

    /**
     * 设置跳转监听
     */
    private void setListener() {
        downlayout.setOnClickListener(onClickListener);
        clearlayout.setOnClickListener(onClickListener);
        about.setOnClickListener(onClickListener);
        update.setOnClickListener(onClickListener);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.setting_download: //数据下载
                    intent = new Intent(getActivity(), DownloadActivity.class);
                    startActivity(intent);
                    break;
                case R.id.setting_data_clear: //清除缓存
                    clearData();
                    break;
                case R.id.about: //关于
                    intent = new Intent(getActivity(), About_us_Activity.class);
                    startActivity(intent);
                    break;
                case R.id.update://检查更新
                    mProgressDialog = ProgressDialog.show(getActivity(), null,
                            "正在检测更新，请耐心等候...", true, true);
                    mProgressDialog.setCanceledOnTouchOutside(false);
                    mProgressDialog.setCancelable(false);
                    setForceUpdate();
                    break;
            }
        }
    };

    //清除基础数据
    private void clearData(){
        mProgressDialog = ProgressDialog.show(getActivity(), null,
                getString(R.string.clearing), true, true);
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.setCancelable(false);
        new WorkOrderDao(getActivity()).deleteall();
        new WoactivityDao(getActivity()).deleteall();
        new WplaborDao(getActivity()).deleteall();
        new WpmeterialDao(getActivity()).deleteall();
        new AssignmentDao(getActivity()).deleteall();
        new LabtransDao(getActivity()).deleteall();
        mProgressDialog.dismiss();
    }

    /**
     * 手动强制更新
     */
    private void setForceUpdate() {
        UmengUpdateAgent.setUpdateAutoPopup(false);
        UmengUpdateAgent.setUpdateListener(new UmengUpdateListener() {

            @Override
            public void onUpdateReturned(int updateStatus,
                                         UpdateResponse updateInfo) {
                mProgressDialog.dismiss();
                switch (updateStatus) {
                    case UpdateStatus.Yes: // has update
                        UmengUpdateAgent
                                .showUpdateDialog(getActivity(), updateInfo);
                        break;
                    case UpdateStatus.No: // has no update
                        Toast.makeText(getActivity(), "未发现新版本，当前安装的已是最新版本",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case UpdateStatus.NoneWifi: // none wifi
                        Toast.makeText(getActivity(), "没有wifi连接， 只在wifi下更新",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case UpdateStatus.Timeout: // time out
                        Toast.makeText(getActivity(), "更新超时,请检查网络",
                                Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        UmengUpdateAgent.forceUpdate(getActivity());
    }
}
