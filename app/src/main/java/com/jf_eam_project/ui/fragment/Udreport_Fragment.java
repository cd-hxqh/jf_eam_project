package com.jf_eam_project.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.jf_eam_project.R;
import com.jf_eam_project.ui.activity.UdinspoNew_Activity;
import com.jf_eam_project.ui.activity.Udreport_Activity;


/**
 * 故障、缺陷
 */
public class Udreport_Fragment extends BaseFragment {

    /**
     * 故障提报单
     */
    private LinearLayout gztbd_layout;


    /**
     * 缺陷提报单
     */
    private LinearLayout qxtbd_layout;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_udreport, container,
                false);

        findByIdView(view);
        setListener();
        return view;
    }

    /**
     * 初始化界面组件*
     */
    private void findByIdView(View view) {
        gztbd_layout = (LinearLayout) view.findViewById(R.id.udinspo_dqdjd_id);
        qxtbd_layout = (LinearLayout) view.findViewById(R.id.udinspo_fjdjd_id);
    }

    /**
     * 设置跳转监听
     */
    private void setListener() {
        gztbd_layout.setOnClickListener(onClickListener);
        qxtbd_layout.setOnClickListener(onClickListener);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {


            switch (v.getId()) {
                case R.id.udinspo_dqdjd_id: //故障提报单

                    Intent intent = new Intent(getActivity(), Udreport_Activity.class);
                    intent.putExtra("title", getActivity().getString(R.string.gztbd_text));
                    intent.putExtra("apptype", "FAULT");
                    startActivityForResult(intent, 0);

                    break;
                case R.id.udinspo_fjdjd_id: //缺陷提报单

                    Intent intent1 = new Intent(getActivity(), Udreport_Activity.class);
                    intent1.putExtra("title", getActivity().getString(R.string.qxtbd_text));
                    intent1.putExtra("apptype", "HIDDEN");
                    startActivityForResult(intent1, 0);

                    break;


            }
        }
    };
}
