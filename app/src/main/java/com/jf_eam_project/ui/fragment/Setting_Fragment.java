package com.jf_eam_project.ui.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.jf_eam_project.Dao.AssetDao;
import com.jf_eam_project.Dao.CraftrateDao;
import com.jf_eam_project.Dao.FailurecodeDao;
import com.jf_eam_project.Dao.FailurelistDao;
import com.jf_eam_project.Dao.ItemDao;
import com.jf_eam_project.Dao.JobplanDao;
import com.jf_eam_project.Dao.LaborDao;
import com.jf_eam_project.Dao.LaborcraftrateDao;
import com.jf_eam_project.Dao.LocationDao;
import com.jf_eam_project.Dao.PersonDao;
import com.jf_eam_project.R;
import com.jf_eam_project.ui.activity.DownloadActivity;
import com.jf_eam_project.ui.activity.Invoice_Activity;
import com.jf_eam_project.ui.activity.Po_order_Activity;
import com.jf_eam_project.ui.activity.Pr_Activity;


/**
 * 设置的fragment
 */
public class Setting_Fragment extends BaseFragment {

    private RelativeLayout downlayout;
    private RelativeLayout clearlayout;
    private RelativeLayout about;
    private ProgressDialog mProgressDialog;
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
    }

    /**
     * 设置跳转监听
     */
    private void setListener() {
        downlayout.setOnClickListener(onClickListener);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {


            switch (v.getId()) {
                case R.id.setting_download: //数据下载

                    Intent intent = new Intent(getActivity(), DownloadActivity.class);
                    startActivity(intent);

                    break;

                case R.id.setting_data_clear: //清除缓存
                    clearData();
                    break;
                case R.id.about: //关于手机
                    break;
//
            }
        }
    };

    //清除基础数据
    private void clearData(){
        mProgressDialog = ProgressDialog.show(getActivity(), null,
                getString(R.string.clearing), true, true);
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.setCancelable(false);
        new LocationDao(getActivity()).deleteall();
        new AssetDao(getActivity()).deleteall();
        new FailurecodeDao(getActivity()).deleteall();
        new FailurelistDao(getActivity()).deleteall();
        new JobplanDao(getActivity()).deleteall();
        new PersonDao(getActivity()).deleteall();
        new LaborDao(getActivity()).deleteall();
        new CraftrateDao(getActivity()).deleteall();
        new ItemDao(getActivity()).deleteall();
        new LaborcraftrateDao(getActivity()).deleteall();
        mProgressDialog.dismiss();
    }
}
