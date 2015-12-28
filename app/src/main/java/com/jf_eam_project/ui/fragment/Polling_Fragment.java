package com.jf_eam_project.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.jf_eam_project.R;
import com.jf_eam_project.config.Constants;
import com.jf_eam_project.ui.activity.Work_ListActivity;


/**
 * 巡检的fragment
 */
public class Polling_Fragment extends BaseFragment {

    /**
     * 巡检计划*
     */
    private LinearLayout plan_layout;
    /**
     * 巡检路线*
     */
    private LinearLayout way_layout;
    /**
     * 巡检单*
     */
    private LinearLayout work_layout;


    public Polling_Fragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_polling, container,
                false);

        findByIdView(view);
        setListener();
        return view;
    }

    /**
     * 初始化界面组件*
     */
    private void findByIdView(View view) {
        plan_layout = (LinearLayout) view.findViewById(R.id.polling_linear_pm_id);
        way_layout = (LinearLayout) view.findViewById(R.id.polling_linear_way_id);
        work_layout = (LinearLayout) view.findViewById(R.id.polling_linear_order_id);
    }

    /**
     * 设置跳转监听
     */
    private void setListener() {
    }

    private class intentOnclicklistener implements View.OnClickListener {
        private String type;

        private intentOnclicklistener(String type) {
            this.type = type;
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getActivity(), Work_ListActivity.class);
            intent.putExtra("worktype", type);
            startActivity(intent);
        }
    }
}
