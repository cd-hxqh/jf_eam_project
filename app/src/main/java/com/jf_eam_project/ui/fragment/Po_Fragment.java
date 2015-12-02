package com.jf_eam_project.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.jf_eam_project.R;
import com.jf_eam_project.ui.activity.Po_order_Activity;
import com.jf_eam_project.ui.activity.Work_ListActivity;


/**
 * 采购管理的fragment
 */
public class Po_Fragment extends BaseFragment {

    /**
     * 采购计划*
     */
    private LinearLayout po_plan_layout;
    /**
     * 采购订单*
     */
    private LinearLayout po_order_layout;
    /**
     * 发票*
     */
    private LinearLayout po_invoice_layout;


    public Po_Fragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_po, container,
                false);

        findByIdView(view);
        setListener();
        return view;
    }

    /**
     * 初始化界面组件*
     */
    private void findByIdView(View view) {
        po_plan_layout = (LinearLayout) view.findViewById(R.id.po_linear_plan_id);
        po_order_layout = (LinearLayout) view.findViewById(R.id.po_linear_order_id);
        po_invoice_layout = (LinearLayout) view.findViewById(R.id.po_linear_invoice_id);
    }

    /**
     * 设置跳转监听
     */
    private void setListener() {
        po_plan_layout.setOnClickListener(onClickListener);
        po_order_layout.setOnClickListener(onClickListener);
        po_invoice_layout.setOnClickListener(onClickListener);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {


            switch (v.getId()) {
                case R.id.po_linear_plan_id: //采购计划

                    Intent intent = new Intent(getActivity(), Po_order_Activity.class);
                    startActivityForResult(intent, 0);

                    break;

                case R.id.po_linear_order_id: //采购订单
                    Intent intent1 = new Intent(getActivity(), Po_order_Activity.class);
                    startActivityForResult(intent1, 0);
                    break;
                case R.id.po_linear_invoice_id: //发票
                    Intent intent2 = new Intent(getActivity(), Po_order_Activity.class);
                    startActivityForResult(intent2, 0);
                    break;

            }
        }
    };
}
