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


/**
 * 库存管理
 */
public class Inventory_fragment extends BaseFragment {

    /**
     * 库存
     */
    private LinearLayout inventory_layout;

    /**
     * 领料单
     */
    private LinearLayout material_layout;


    /**
     * 调出单
     */
    private LinearLayout material_up_layout;

    /**
     * 调出单
     */
    private LinearLayout material_into_layout;
    /**
     * 物资借用归还
     */
    private LinearLayout udbr_layout;



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
        material_layout = (LinearLayout) view.findViewById(R.id.material_layout_id);
        material_up_layout = (LinearLayout) view.findViewById(R.id.material_up_layout_id);
        material_into_layout = (LinearLayout) view.findViewById(R.id.material_in_layout_id);
        udbr_layout = (LinearLayout) view.findViewById(R.id.udbr_linearlayout_id);
    }

    /**
     * 设置跳转监听
     */
    private void setListener() {
        inventory_layout.setOnClickListener(onClickListener);
        material_layout.setOnClickListener(onClickListener);
        material_up_layout.setOnClickListener(onClickListener);
        material_into_layout.setOnClickListener(onClickListener);
        udbr_layout.setOnClickListener(onClickListener);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {


            switch (v.getId()) {
                case R.id.inventory_linear_id: //库存

                    Intent intent = new Intent(getActivity(), Inventory_Activity.class);
                    startActivityForResult(intent, 0);

                    break;

                case R.id.material_layout_id: //领料单
                    Intent intent1 = new Intent(getActivity(), Material_ListActivity.class);
                    startActivityForResult(intent1, 0);
                    break;
                case R.id.material_up_layout_id: //调出单
                    Intent intent2 = new Intent(getActivity(), Materials_up_ListActivity.class);
                    startActivityForResult(intent2, 0);
                    break;
                case R.id.material_in_layout_id: //调入单
                    Intent intent3 = new Intent(getActivity(), Materials_Into_ListActivity.class);
                    startActivityForResult(intent3, 0);
                    break;
                case R.id.udbr_linearlayout_id: //物资借用归还
                    Intent intent4 = new Intent(getActivity(), Udbr_ListActivity.class);
                    startActivityForResult(intent4, 0);
                    break;

            }
        }
    };
}
