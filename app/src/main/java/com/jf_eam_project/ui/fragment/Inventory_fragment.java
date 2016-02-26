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
import com.jf_eam_project.ui.activity.Po_order_Activity;
import com.jf_eam_project.ui.activity.Pr_Activity;


/**
 * 库存管理
 */
public class Inventory_fragment extends BaseFragment {

    /**
     * 库存
     */
    private LinearLayout inventory_layout;
    /**
     * 入库
     */
    private LinearLayout matrectrans_layout;
    /**
     * 出库
     */
    private LinearLayout matusetrans_layout;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inventory, container,
                false);

        findByIdView(view);
        setListener();
        return view;
    }

    /**
     * 初始化界面组件*
     */
    private void findByIdView(View view) {
        inventory_layout = (LinearLayout) view.findViewById(R.id.inventory_linear_id);
        matrectrans_layout = (LinearLayout) view.findViewById(R.id.inventory_linear_inbound_id);
        matusetrans_layout = (LinearLayout) view.findViewById(R.id.inventory_linear_outbound_id);
    }

    /**
     * 设置跳转监听
     */
    private void setListener() {
        inventory_layout.setOnClickListener(onClickListener);
        matrectrans_layout.setOnClickListener(onClickListener);
        matusetrans_layout.setOnClickListener(onClickListener);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {


            switch (v.getId()) {
                case R.id.inventory_linear_id: //库存

                    Intent intent = new Intent(getActivity(), Inventory_Activity.class);
                    startActivityForResult(intent, 0);

                    break;

//                case R.id.inventory_linear_inbound_id: //入库
//                    Intent intent1 = new Intent(getActivity(), Po_order_Activity.class);
//                    startActivityForResult(intent1, 0);
//                    break;
//                case R.id.inventory_linear_outbound_id: //出库
//                    Intent intent2 = new Intent(getActivity(), Invoice_Activity.class);
//                    startActivityForResult(intent2, 0);
//                    break;

            }
        }
    };
}
