package com.jf_eam_project.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

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

//                case R.id.po_linear_order_id: //采购订单
//                    Intent intent1 = new Intent(getActivity(), Po_order_Activity.class);
//                    startActivityForResult(intent1, 0);
//                    break;
//                case R.id.po_linear_invoice_id: //发票
//                    Intent intent2 = new Intent(getActivity(), Invoice_Activity.class);
//                    startActivityForResult(intent2, 0);
//                    break;
//
            }
        }
    };
}
