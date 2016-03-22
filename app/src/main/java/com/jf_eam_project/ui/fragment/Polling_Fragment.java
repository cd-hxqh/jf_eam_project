package com.jf_eam_project.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.jf_eam_project.R;
import com.jf_eam_project.ui.activity.Inventory_Activity;
import com.jf_eam_project.ui.activity.Invoice_Activity;
import com.jf_eam_project.ui.activity.Location_Activity;
import com.jf_eam_project.ui.activity.Material_ListActivity;
import com.jf_eam_project.ui.activity.Materials_Into_ListActivity;
import com.jf_eam_project.ui.activity.Materials_up_ListActivity;
import com.jf_eam_project.ui.activity.Po_order_Activity;
import com.jf_eam_project.ui.activity.Pr_Activity;
import com.jf_eam_project.ui.activity.Udbr_ListActivity;
import com.jf_eam_project.ui.activity.Udinspo_Activity;
import com.jf_eam_project.ui.activity.Uditemreq_listactivity;


/**
 * 巡检管理
 */
public class Polling_Fragment extends BaseFragment {

    /**
     * 电气定检单
     */
    private LinearLayout dqdjd_layout;



    /**
     * 风机定检单
     */
    private LinearLayout fjdjd_layout;


    /**
     * 风机巡检单
     */
    private LinearLayout fjxjd_layout;

    /**
     * 集电线路
     */
    private LinearLayout jdxl_layout;
    /**
     * 箱台变
     */
    private LinearLayout xtb_layout;
    /**
     * 其他设备
     */
    private LinearLayout qtsb_layout;
    /**
     * 日常巡检
     */
    private LinearLayout rcxj_layout;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inspection, container,
                false);

        findByIdView(view);
        setListener();
        return view;
    }

    /**
     * 初始化界面组件*
     */
    private void findByIdView(View view) {
        dqdjd_layout = (LinearLayout) view.findViewById(R.id.udinspo_dqdjd_id);
        fjdjd_layout = (LinearLayout) view.findViewById(R.id.udinspo_fjdjd_id);
        fjxjd_layout = (LinearLayout) view.findViewById(R.id.udinspo_fjxjd_id);
        jdxl_layout = (LinearLayout) view.findViewById(R.id.udinspo_jdxl_id);
        xtb_layout = (LinearLayout) view.findViewById(R.id.udinspo_xtb_id);
        qtsb_layout = (LinearLayout) view.findViewById(R.id.udinspo_qtsb_id);
        rcxj_layout = (LinearLayout) view.findViewById(R.id.udinspo_rcxj_id);
    }

    /**
     * 设置跳转监听
     */
    private void setListener() {
        dqdjd_layout.setOnClickListener(onClickListener);
        fjdjd_layout.setOnClickListener(onClickListener);
        fjxjd_layout.setOnClickListener(onClickListener);
        jdxl_layout.setOnClickListener(onClickListener);
        xtb_layout.setOnClickListener(onClickListener);
        qtsb_layout.setOnClickListener(onClickListener);
        rcxj_layout.setOnClickListener(onClickListener);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {


            switch (v.getId()) {
                case R.id.udinspo_dqdjd_id: //电气定检单

                    Intent intent = new Intent(getActivity(), Udinspo_Activity.class);
                    intent.putExtra("title", getActivity().getString(R.string.dqdjd_text));
                    intent.putExtra("assettype", "电气");
                    intent.putExtra("checktype", "定检");
                    intent.putExtra("inspotype", "05");
                    startActivityForResult(intent, 0);

                    break;
                case R.id.udinspo_fjdjd_id: //风机定检单

                    Intent intent1 = new Intent(getActivity(), Udinspo_Activity.class);
                    intent1.putExtra("title", getActivity().getString(R.string.fjdjd_text));
                    intent1.putExtra("assettype", "风机");
                    intent1.putExtra("checktype", "定检");
                    intent1.putExtra("inspotype", "05");
                    startActivityForResult(intent1, 0);

                    break;

                case R.id.udinspo_fjxjd_id: //风机巡检单
                    Intent intent2 = new Intent(getActivity(), Udinspo_Activity.class);
                    intent2.putExtra("inspotype", "05");
                    intent2.putExtra("assettype", "风机");
                    intent2.putExtra("checktype", "巡检");
                    intent2.putExtra("title", getActivity().getString(R.string.fjxjd_text));
                    startActivityForResult(intent2, 0);
                    break;
                case R.id.udinspo_jdxl_id: //集电线路
                    Intent intent3 = new Intent(getActivity(), Udinspo_Activity.class);
                    intent3.putExtra("title", getActivity().getString(R.string.jdxl_text));
                    intent3.putExtra("inspotype", "02");
                    startActivityForResult(intent3, 0);
                    break;
                case R.id.udinspo_xtb_id: //箱台变
                    Intent intent4 = new Intent(getActivity(), Udinspo_Activity.class);
                    intent4.putExtra("title", getActivity().getString(R.string.jdxl_text));
                    intent4.putExtra("inspotype", "01");
                    startActivityForResult(intent4, 0);
                    break;
                case R.id.udinspo_qtsb_id: //其他设备
                    Intent intent5 = new Intent(getActivity(), Udinspo_Activity.class);
                    intent5.putExtra("title", getActivity().getString(R.string.qtsb_text));
                    intent5.putExtra("inspotype", "03");
                    startActivityForResult(intent5, 0);
                    break;
                case R.id.udinspo_rcxj_id: //日常巡检
                    Intent intent6 = new Intent(getActivity(), Udinspo_Activity.class);
                    intent6.putExtra("title", getActivity().getString(R.string.rcxj_text));
                    intent6.putExtra("inspotype", "04");
                    startActivityForResult(intent6, 0);
                    break;

            }
        }
    };
}
