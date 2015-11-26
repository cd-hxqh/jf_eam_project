package com.jf_eam_project.ui.fragment;

import android.app.Fragment;
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
 * 工单的fragment1
 */
public class WorkFragment extends BaseFragment {

    private LinearLayout item_layout,plan_layout,not_plan_layout;

    public WorkFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_work, container,
                false);

        findByIdView(view);
        setlistener();
        return view;
    }
    /**
     * 初始化界面组件*
     */
    private void findByIdView(View view) {
        item_layout = (LinearLayout) view.findViewById(R.id.work_linear_item_id);
        plan_layout = (LinearLayout) view.findViewById(R.id.work_linear_plan_id);
        not_plan_layout = (LinearLayout) view.findViewById(R.id.work_linear_not_plan_id);
    }

    /**
     * 设置跳转监听
     */
    private void setlistener(){
        item_layout.setOnClickListener(new intentOnclicklistener(Constants.PROJECT));
        plan_layout.setOnClickListener(new intentOnclicklistener(Constants.PLAN));
        not_plan_layout.setOnClickListener(new intentOnclicklistener(Constants.UNPLAN));
    }

    private class intentOnclicklistener implements View.OnClickListener{
        private String type;
        private intentOnclicklistener(String type){
            this.type = type;
        }
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getActivity(), Work_ListActivity.class);
            intent.putExtra("worktype",type);
            startActivity(intent);
        }
    }
}
