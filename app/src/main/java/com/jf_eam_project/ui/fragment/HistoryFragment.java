package com.jf_eam_project.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.jf_eam_project.R;
import com.jf_eam_project.config.Constants;
import com.jf_eam_project.ui.activity.Udinspo_History_Activity;
import com.jf_eam_project.ui.activity.Work_History_ListActivity;
import com.jf_eam_project.ui.activity.Work_ListActivity;


/**
 * 本地历史记录的fragment1
 */
public class HistoryFragment extends BaseFragment {

    /**
     * 工单*
     */
    private LinearLayout workorder_layout;
    /**
     * 巡检单*
     */
    private LinearLayout udinspo_layout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container,
                false);

        findByIdView(view);
        setlistener();
        return view;
    }

    /**
     * 初始化界面组件*
     */
    private void findByIdView(View view) {
//        item_layout = (LinearLayout) view.findViewById(R.id.work_linear_item_id);
        workorder_layout = (LinearLayout) view.findViewById(R.id.work_order_linearlayout_id);
        udinspo_layout = (LinearLayout) view.findViewById(R.id.udinspo_linearlayout_id);
    }

    /**
     * 设置跳转监听
     */
    private void setlistener() {
        workorder_layout.setOnClickListener(workorderOnClickListener);
        udinspo_layout.setOnClickListener(udinspoOnClickListener);
    }

    private View.OnClickListener workorderOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getActivity(), Work_History_ListActivity.class);
            startActivityForResult(intent, 1);
        }
    };
    private View.OnClickListener udinspoOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getActivity(), Udinspo_History_Activity.class);
            startActivityForResult(intent, 0);
        }
    };



}
