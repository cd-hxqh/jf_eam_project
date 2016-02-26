package com.jf_eam_project.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.jf_eam_project.R;
import com.jf_eam_project.ui.activity.MipcaActivityCapture;
import com.jf_eam_project.ui.activity.Udinspo_History_Activity;
import com.jf_eam_project.ui.activity.Work_History_ListActivity;


/**
 * 二维码/条码扫描
 */
public class QrCode_Fragment extends BaseFragment {

    private Button qrcodeBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_qrcode, container,
                false);

        findByIdView(view);
        setlistener();
        return view;
    }

    /**
     * 初始化界面组件*
     */
    private void findByIdView(View view) {
        qrcodeBtn = (Button) view.findViewById(R.id.qr_button);
    }

    /**
     * 设置跳转监听
     */
    private void setlistener() {
        qrcodeBtn.setOnClickListener(qrcodeBtnOnClickListener);
    }


    private View.OnClickListener qrcodeBtnOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent();
            intent.setClass(getActivity(), MipcaActivityCapture.class);
            startActivityForResult(intent, 0);
        }
    };

}
